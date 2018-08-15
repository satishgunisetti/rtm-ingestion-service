package com.kogentix.rtm.service;

import com.kogentix.rtm.beans.NewToBank;
import com.kogentix.rtm.props.PlatformProps;
import com.kogentix.rtm.repo.RTMDao;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Future;

@Service("rtmService")
public class RTMService {
    private final Logger logger = LoggerFactory.getLogger(RTMService.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private PlatformProps platformProps;

    @Autowired
    private RTMDao rtmDao;


    public NewToBank saveAndSendCustomer(NewToBank ntb){

        logger.info("Converting NewTOBank Pojo to Avro Bytes");

        DatumWriter writer =  new SpecificDatumWriter(NewToBank.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        try {
            writer.write(ntb, encoder);
            encoder.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = out.toByteArray();

        logger.info("Sending Avro Bytes to Kafka, Topic Name:  " + platformProps.getKafkaTopic());
        kafkaTemplate.send(platformProps.getKafkaTopic(), bytes);
        logger.info("Successfully sent avro message to kafka");
        logger.info("Invoking Dao's save() method");
        return rtmDao.save(ntb);
    }



}
