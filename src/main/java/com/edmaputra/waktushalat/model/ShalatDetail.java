package com.edmaputra.waktushalat.model;

import io.swagger.annotations.ApiModelProperty;

public class ShalatDetail {

    @ApiModelProperty(notes = "Nama Waktu Shalat")
    private String shalat;

    @ApiModelProperty(notes = "Waktu/Jam Shalat")
    private String waktu;

    public String getShalat() {
        return shalat;
    }

    public void setShalat(String shalat) {
        this.shalat = shalat;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
