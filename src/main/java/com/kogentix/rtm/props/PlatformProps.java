package com.kogentix.rtm.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring")
public class PlatformProps {

    private String kafkaTopic;
    private String kafkaBrokerList;
    private String kafkaZookeeperQuorum;
    private String kafkaSecurityProtocal;
    private String kafkaSaslKerberosServiceName;
    private int kafkaLingerMs;
    private String hbaseRestServer;
    private int hbaseRestPort;
    private String hbaseTableName;

    public String getHbaseTableName() {
        return hbaseTableName;
    }

    public void setHbaseTableName(String hbaseTableName) {
        this.hbaseTableName = hbaseTableName;
    }

    public String getHbaseRestServer() {
        return hbaseRestServer;
    }

    public void setHbaseRestServer(String hbaseRestServer) {
        this.hbaseRestServer = hbaseRestServer;
    }

    public int getHbaseRestPort() {
        return hbaseRestPort;
    }

    public void setHbaseRestPort(int hbaseRestPort) {
        this.hbaseRestPort = hbaseRestPort;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    public String getKafkaBrokerList() {
        return kafkaBrokerList;
    }

    public void setKafkaBrokerList(String kafkaBrokerList) {
        this.kafkaBrokerList = kafkaBrokerList;
    }

    public String getKafkaZookeeperQuorum() {
        return kafkaZookeeperQuorum;
    }

    public void setKafkaZookeeperQuorum(String kafkaZookeeperQuorum) {
        this.kafkaZookeeperQuorum = kafkaZookeeperQuorum;
    }

    public String getKafkaSecurityProtocal() {
        return kafkaSecurityProtocal;
    }

    public void setKafkaSecurityProtocal(String kafkaSecurityProtocal) {
        this.kafkaSecurityProtocal = kafkaSecurityProtocal;
    }

    public String getKafkaSaslKerberosServiceName() {
        return kafkaSaslKerberosServiceName;
    }

    public void setKafkaSaslKerberosServiceName(String kafkaSaslKerberosServiceName) {
        this.kafkaSaslKerberosServiceName = kafkaSaslKerberosServiceName;
    }

    public int getKafkaLingerMs() {
        return kafkaLingerMs;
    }

    public void setKafkaLingerMs(int kafkaLingerMs) {
        this.kafkaLingerMs = kafkaLingerMs;
    }

    @Override
    public String toString() {
        return "PlatformProps{" +
                "kafkaTopic='" + kafkaTopic + '\'' +
                ", kafkaBrokerList='" + kafkaBrokerList + '\'' +
                ", kafkaZookeeperQuorum='" + kafkaZookeeperQuorum + '\'' +
                ", kafkaSecurityProtocal='" + kafkaSecurityProtocal + '\'' +
                ", kafkaSaslKerberosServiceName='" + kafkaSaslKerberosServiceName + '\'' +
                ", kafkaLingerMs=" + kafkaLingerMs +
                ", hbaseRestServer='" + hbaseRestServer + '\'' +
                ", hbaseRestPort=" + hbaseRestPort +
                ", hbaseTableName='" + hbaseTableName + '\'' +
                '}';
    }
}
