package com.kogentix.rtm.repo.impl;

import com.kogentix.rtm.beans.NewToBank;
import com.kogentix.rtm.repo.RTMDao;
import com.kogentix.rtm.utils.PlatformUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.rest.client.RemoteHTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Repository("rtmDao")
public class RTMDaoImpl implements RTMDao {
    private Logger logger = LoggerFactory.getLogger(RTMDaoImpl.class);


    @Autowired
    private RemoteHTable hTable;
    private List<String> columns;
    private final String COLUMN_FAMILY = "cf1";

    @PostConstruct
    public void init(){
        logger.info("Getting the column list from Avro POJO");
        columns = NewToBank.getColumnsAsList();
        logger.info(columns.toString());
    }

    @Override
    public NewToBank save(NewToBank ntb) {

        try {
            logger.info("Constructing Hbase Record (Put Record) from NewTOBank POJO, with row key: " + ntb.getACodAcctNo());

            Put put = new Put(Bytes.toBytes(String.valueOf(ntb.getACodAcctNo())));

            for(String column : columns){
                Object value = ntb.get(column);
                logger.info(column + " : " + value);

                if( null != value){
                    put.addColumn(Bytes.toBytes(COLUMN_FAMILY), Bytes.toBytes(column), Bytes.toBytes(String.valueOf(value)));
                }
                else{
                    logger.info(column + " has null value.");
                }
            }

            long accountOpenDate = ntb.getADatAcctOpen();
            if(accountOpenDate == Long.MIN_VALUE) {
                logger.info("Account Open date is " + ntb.getADatAcctOpen() + ". Providing current timestamp as account open date.");
                put.addColumn(Bytes.toBytes(COLUMN_FAMILY), Bytes.toBytes("a_dat_acct_open"), Bytes.toBytes(String.valueOf(PlatformUtils.getDateInEpochMillis())));
            }
            else{
                logger.info("Account Open date is " + ntb.getADatAcctOpen() +". Parsing date into milli seconds");
                put.addColumn(Bytes.toBytes(COLUMN_FAMILY), Bytes.toBytes("a_dat_acct_open"), Bytes.toBytes(String.valueOf(PlatformUtils.getDateInEpochMillis(accountOpenDate))));
            }

            logger.info(put.toJSON());
            hTable.put(put);
            logger.info("Successfully ingested record into HBase Table.");

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Got Exception : " + e.getLocalizedMessage());
        }

        return ntb;
    }
}
