package com.kogentix.rtm.controller;

import com.kogentix.rtm.beans.NTBResponse;
import com.kogentix.rtm.beans.NewToBank;
import com.kogentix.rtm.service.RTMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class RTMController {

    private final Logger logger = LoggerFactory.getLogger(RTMController.class);

    @Autowired
    private RTMService rtmService;


    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<NTBResponse> postCustomer(@RequestBody  NewToBank newToBank){

        logger.info("Event Received : " + newToBank.toString());
        NewToBank ntb = rtmService.saveAndSendCustomer(newToBank);
        return new ResponseEntity<NTBResponse>(new NTBResponse(ntb.getChannelId(), ntb.getBinNo(), ntb.getUserRefNo(), LocalDate.now(), ntb.getServiceCode(), "0", "Success"), HttpStatus.CREATED);
    }


}
