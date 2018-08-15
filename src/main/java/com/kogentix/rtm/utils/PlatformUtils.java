package com.kogentix.rtm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PlatformUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformUtils.class);

    public static long getDateInEpochMillis(long date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LOGGER.info("Parsing the input date from \"yyyyMMdd\" format, and converting to Epoch Millis");
        return LocalDate.parse(String.valueOf(date), formatter).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long getDateInEpochMillis(){
        LOGGER.info("Converting current timestamp to Epoch Millis");
        return Instant.now().toEpochMilli();
    }
}
