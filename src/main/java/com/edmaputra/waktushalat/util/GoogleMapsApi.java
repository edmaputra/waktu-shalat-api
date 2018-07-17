package com.edmaputra.waktushalat.util;

import com.edmaputra.waktushalat.model.maps.GoogleMaps;
import org.springframework.web.client.RestTemplate;

public class GoogleMapsApi {


    public static GoogleMaps getGoogleMaps(String location) {

        location = location.trim().replaceAll(" ", "+");

        final String API_KEY = "AIzaSyAdReNFE8MFbEwGAwi-R_nhRJ-StpAm2aY";
        final String uri = "https://maps.googleapis.com/maps/api/geocode/json?address="+location+"&key="+API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        GoogleMaps gmaps = restTemplate.getForObject(uri, GoogleMaps.class);

        return gmaps;
    }

}
