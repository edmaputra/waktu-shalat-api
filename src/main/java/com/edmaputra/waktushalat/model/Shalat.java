package com.edmaputra.waktushalat.model;

import java.util.Date;
import java.util.List;

public class Shalat {

    private String title;
    private String pesan;
    private String wilayah;
    private String tanggal_string;
    private Date tanggal;
    private double timezone;
    private int metode;
    private String namaMetode;
    private List<ShalatDetail> shalats;

    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getTanggal_string() {
        return tanggal_string;
    }

    public void setTanggal_string(String tanggal_string) {
        this.tanggal_string = tanggal_string;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
