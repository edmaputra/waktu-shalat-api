package com.edmaputra.waktushalat.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class Shalat {

    @ApiModelProperty(notes = "Title")
    private String title;

    @ApiModelProperty(notes = "Wilayah yang diinput")
    private String wilayah;

    @ApiModelProperty(notes = "Tanggal dalam tipe String")
    private String tanggalString;

    @ApiModelProperty(notes = "Tanggal dalam type Date")
    private Date tanggal;

    @ApiModelProperty(notes = "Zona Waktu/Timezone")
    private double timezone;

    @ApiModelProperty(notes = "Metode Kalkulasi dengan pilihan 0 : Ithna Ashari; 1 : University of Islamic Sciences, Karachi; 2 : Islamic Society of North America (ISNA); " +
            "3 : Muslim World League (MWL); 4 : Umm al-Qura, Makkah; " +
            "5 : Egyptian General Authority of Survey; 6 : Institute of Geophysics, University of Tehran")
    private int metode;

    @ApiModelProperty(notes = "Nama Metode Kalkulasi")
    private String namaMetode;

    @ApiModelProperty(notes = "Shalat Detail Entity")
    private List<ShalatDetail> shalats;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getTanggalString() {
        return tanggalString;
    }

    public void setTanggalString(String tanggalString) {
        this.tanggalString = tanggalString;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public double getTimezone() {
        return timezone;
    }

    public void setTimezone(double timezone) {
        this.timezone = timezone;
    }

    public int getMetode() {
        return metode;
    }

    public void setMetode(int metode) {
        this.metode = metode;
    }

    public String getNamaMetode() {
        return namaMetode;
    }

    public void setNamaMetode(String namaMetode) {
        this.namaMetode = namaMetode;
    }

    public List<ShalatDetail> getShalats() {
        return shalats;
    }

    public void setShalats(List<ShalatDetail> shalats) {
        this.shalats = shalats;
    }
}
