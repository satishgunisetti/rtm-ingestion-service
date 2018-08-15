package com.kogentix.rtm.conf;

import com.kogentix.rtm.props.PlatformProps;
import org.apache.hadoop.hbase.rest.client.Client;
import org.apache.hadoop.hbase.rest.client.Cluster;
import org.apache.hadoop.hbase.rest.client.RemoteHTable;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PlatformConfig {
    private final Logger logger = LoggerFactory.getLogger(PlatformConfig.class);
    private final String SECURITY_PROTOCOL = "security.protocol";
    private final String SASL_KERBEROS_SERVICE_NAME = "sasl.kerberos.service.name";

    @Bean
    public PlatformProps platformProps() {
        logger.info("Initiated PlaformProps Bean.");
        PlatformProps props =  new PlatformProps();
        logger.info(props.toString());
        return props;
    }

    @Bean
    public ProducerFactory<String, byte[]> producerFactory(PlatformProps platformProps) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, platformProps.getKafkaBrokerList());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        props.put(SECURITY_PROTOCOL, platformProps.getKafkaSecurityProtocal());
        props.put(SASL_KERBEROS_SERVICE_NAME, platformProps.getKafkaSaslKerberosServiceName());
        props.put(ProducerConfig.LINGER_MS_CONFIG, platformProps.getKafkaLingerMs());
        logger.info("Initiated ProducerFactory Bean.");
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, byte[]> kafkaTemplate(ProducerFactory producerFactory) {
        logger.info("Initiated KafkaTemplate Bean.");
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean("hTable")
    public RemoteHTable connection(PlatformProps platformProps) {
        Cluster hbaseCluster = new Cluster();
        hbaseCluster.add(platformProps.getHbaseRestServer(), platformProps.getHbaseRestPort());
        Client restClient = new Client(hbaseCluster);
        logger.info("Initiated RemoteHTable Bean with table : " + platformProps.getHbaseTableName());
        return new RemoteHTable(restClient, platformProps.getHbaseTableName());
    }

}
