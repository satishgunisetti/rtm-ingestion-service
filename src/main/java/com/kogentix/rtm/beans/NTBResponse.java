package com.kogentix.rtm.beans;

//import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class NTBResponse {
    private java.lang.CharSequence channel_id;
    private java.lang.CharSequence bin_no;
    private java.lang.CharSequence user_ref_no;

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyyMMddHHmmss", timezone="Asia/Jakarta")
    private LocalDate response_time;
    private java.lang.CharSequence service_code;
    private java.lang.CharSequence code_status;
    private java.lang.CharSequence desc_status;

    public NTBResponse(){}

    public NTBResponse(java.lang.CharSequence channel_id, java.lang.CharSequence bin_no, java.lang.CharSequence user_ref_no,
                       LocalDate response_time, java.lang.CharSequence service_code, java.lang.CharSequence code_status,
                       java.lang.CharSequence desc_status) {
        super();
        this.channel_id = channel_id;
        this.bin_no = bin_no;
        this.user_ref_no = user_ref_no;
        this.response_time = response_time;
        this.service_code = service_code;
        this.code_status = code_status;
        this.desc_status = desc_status;
    }



    public java.lang.CharSequence getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(java.lang.CharSequence channel_id) {
        this.channel_id = channel_id;
    }

    public java.lang.CharSequence getBin_no() {
        return bin_no;
    }

    public void setBin_no(java.lang.CharSequence bin_no) {
        this.bin_no = bin_no;
    }

    public java.lang.CharSequence getUser_ref_no() {
        return user_ref_no;
    }

    public void setUser_ref_no(java.lang.CharSequence user_ref_no) {
        this.user_ref_no = user_ref_no;
    }

    public LocalDate getResponse_time() {
        return response_time;
    }

    public void setResponse_time(LocalDate response_time) {
        this.response_time = response_time;
    }

    public java.lang.CharSequence getService_code() {
        return service_code;
    }

    public void setService_code(java.lang.CharSequence service_code) {
        this.service_code = service_code;
    }

    public java.lang.CharSequence getCode_status() {
        return code_status;
    }

    public void setCode_status(java.lang.CharSequence code_status) {
        this.code_status = code_status;
    }

    public java.lang.CharSequence getDesc_status() {
        return desc_status;
    }

    public void setDesc_status(java.lang.CharSequence desc_status) {
        this.desc_status = desc_status;
    }


}
