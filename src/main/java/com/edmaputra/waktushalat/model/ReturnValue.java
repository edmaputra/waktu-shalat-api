package com.edmaputra.waktushalat.model;

import io.swagger.annotations.ApiModelProperty;

public class ReturnValue {

    @ApiModelProperty(notes = "Hasil dengan Shalat Model")
    private Shalat result;

    @ApiModelProperty(notes = "Pesan Hasil Pencarian")
    private String pesan;

    @ApiModelProperty(notes = "Pesan Status Response")
    private String status;

    public Shalat getResult() {
        return result;
    }

    public void setResult(Shalat result) {
        this.result = result;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
