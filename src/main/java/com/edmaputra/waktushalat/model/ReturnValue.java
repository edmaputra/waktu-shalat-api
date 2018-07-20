package com.edmaputra.waktushalat.model;

public class ReturnValue {

    private Shalat result;
    private String pesan;
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
