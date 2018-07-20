package com.edmaputra.waktushalat.model;

import java.util.Date;
import java.util.List;

public class Shalat {

    private String title;
    private String wilayah;
    private String tanggalString;
    private Date tanggal;
    private double timezone;
    private int metode;
    private String namaMetode;
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
