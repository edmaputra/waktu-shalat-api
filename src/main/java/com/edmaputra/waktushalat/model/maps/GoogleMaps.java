package com.edmaputra.waktushalat.model.maps;

import java.util.List;

public class GoogleMaps {

    private List<Lokasi> results;
    private String status;

    public List<Lokasi> getResults() {
        return results;
    }

    public void setResults(List<Lokasi> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
