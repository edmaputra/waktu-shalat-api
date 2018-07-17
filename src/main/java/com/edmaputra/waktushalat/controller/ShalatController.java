package com.edmaputra.waktushalat.controller;

import com.edmaputra.waktushalat.model.ShalatDetail;
import com.edmaputra.waktushalat.model.maps.Lokasi;
import com.edmaputra.waktushalat.util.PrayTime;
import com.edmaputra.waktushalat.model.Shalat;
import com.edmaputra.waktushalat.model.maps.GoogleMaps;
import com.edmaputra.waktushalat.util.Converter;
import com.edmaputra.waktushalat.util.GoogleMapsApi;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shalat")
public class ShalatController {

    private Date date;
    private String address;
    private Integer method;
    private double timezone;

    private boolean valid;
    private String errorMessage;

    @GetMapping(value = "/{k1}/{v1}")
    @ResponseBody
    public Shalat oneParamater(@PathVariable("k1") String k1, @PathVariable("v1") String v1){
        init();
        Shalat shalat = null;

        String[] params = setArray(k1, v1);
        if (params.length > 8) {
            valid = false;
            errorMessage += "Parameter Melebihi Batas";
        } else {
            shalat = new Shalat();
            setParameter(params);
        }

        if (valid) {
            shalat = getShalatTime(address, date, timezone, method);
            shalat.setStatus("OK");
            shalat.setPesan("OK");
        } else {
            shalat.setStatus("FAILED");
            shalat.setPesan(errorMessage);
        }
        return shalat;
    }

    @GetMapping(value = "/{k1}/{v1}/{k2}/{v2}")
    @ResponseBody
    public Shalat oneParamater(@PathVariable("k1") String k1, @PathVariable("v1") String v1,
                               @PathVariable("k2") String k2, @PathVariable("v2") String v2){
        init();
        Shalat shalat = null;

        String[] params = setArray(k1, v1, k2, v2);
        if (params.length > 8) {
            valid = false;
            errorMessage += "Parameter Melebihi Batas";
        } else {
            shalat = new Shalat();
            setParameter(params);
        }

        if (valid) {
            shalat = getShalatTime(address, date, timezone, method);
            shalat.setStatus("OK");
            shalat.setPesan("OK");
        } else {
            shalat.setStatus("FAILED");
            shalat.setPesan(errorMessage);
        }
        return shalat;
    }

    @GetMapping(value = "/{k1}/{v1}/{k2}/{v2}/{k3}/{v3}")
    @ResponseBody
    public Shalat oneParamater(@PathVariable("k1") String k1, @PathVariable("v1") String v1,
                               @PathVariable("k2") String k2, @PathVariable("v2") String v2,
                               @PathVariable("k3") String k3, @PathVariable("v3") String v3){
        init();
        Shalat shalat = null;

        String[] params = setArray(k1, v1, k2, v2, k3, v3);
        if (params.length > 8) {
            valid = false;
            errorMessage += "Parameter Melebihi Batas";
        } else {
            shalat = new Shalat();
            setParameter(params);
        }

        if (valid) {
            shalat = getShalatTime(address, date, timezone, method);
            shalat.setStatus("OK");
            shalat.setPesan("OK");
        } else {
            shalat.setStatus("FAILED");
            shalat.setPesan(errorMessage);
        }
        return shalat;
    }

    @GetMapping(value = "/{k1}/{v1}/{k2}/{v2}/{k3}/{v3}/{k4}/{v4}")
    @ResponseBody
    public Shalat oneParamater(@PathVariable("k1") String k1, @PathVariable("v1") String v1,
                               @PathVariable("k2") String k2, @PathVariable("v2") String v2,
                               @PathVariable("k3") String k3, @PathVariable("v3") String v3,
                               @PathVariable("k4") String k4, @PathVariable("v4") String v4){
        init();
        Shalat shalat = null;
        String[] params = setArray(k1, v1, k2, v2, k3, v3, k4, v4);

        if (params.length > 8) {
            valid = false;
            errorMessage += "Parameter Melebihi Batas";
        } else {
            shalat = new Shalat();
            setParameter(params);
        }

        if (valid) {
            shalat = getShalatTime(address, date, timezone, method);
            shalat.setStatus("OK");
            shalat.setPesan("OK");
        } else {
            shalat.setStatus("FAILED");
            shalat.setPesan(errorMessage);
        }
        return shalat;
    }

    private String[] setArray(String... arg) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String str : arg){
//            if (i == 0 || i % 2 == 0) {
//                if (!str.equals("d") || !str.equals("m") || !str.equals("l") || !str.equals("t")) {
//                    valid = false;
//                    errorMessage = "Ada Kesalahan Parameter";
//                }
//            }
            builder.append(str);
            i++;
            if (i != arg.length) {
                builder.append("/");
            }
        }
        String[] params = builder.toString().split("/");
        return params;
    }

    private void init(){
        valid = true;
        date = new Date();
        timezone = 8;
        method = 3;
        address = null;
        errorMessage = "";
    }

    private Shalat getShalatTime(String address, Date date, double timezone, Integer method) {
        PrayTime prayTime = new PrayTime();

        GoogleMaps gmaps = GoogleMapsApi.getGoogleMaps(address);
        Lokasi lokasi = gmaps.getResults().get(0);
        double lat = lokasi.getGeometry().getLocation().getLat();
        double lng = lokasi.getGeometry().getLocation().getLng();
        List<ShalatDetail> prays = prayTime.getListPrayTime(lat, lng, timezone, method, date);

        Shalat shalat = new Shalat();
        shalat.setTitle("Waktu Shalat");
        shalat.setWilayah(gmaps.getResults().get(0).getFormatted_address());
        shalat.setTimezone(timezone);
        shalat.setTanggal(date);
        shalat.setTanggal_string(Converter.dateToStringDashSeparator(date));
        shalat.setMetode(method);
        shalat.setNamaMetode(getCalcMethodNames(method));
        shalat.setShalats(prays);

        return shalat;
    }

    private void setParameter(String[] params) {
//         else {
//            valid = false;
//            errorMessage = "Ada Kesalahan Parameter";
//        }
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("d")) {
                if (isValidDate(params[i+1])) {
                    date = Converter.stringToDate(params[i+1]);
                    continue;
                } else {
                    valid = false;
                    errorMessage = "Format Tanggal Salah, pastikan Format Tanggal ddMMyyyy";
                }
            } else if (params[i].equals("l")) {
                address = params[i+1];
                continue;
            } else if (params[i].equals("m")) {
                int metode = Integer.valueOf(params[i+1]);
                if (!isNumeric(Integer.toString(metode))) {
                    valid = false;
                    errorMessage = "Format Metode Salah";
                } else if (metode > 6 || metode < 0){
                    valid = false;
                    errorMessage += "Pilihan Metode Kalkulasi Tidak Ada, Harap Pilih 0-6";
                } else if (isNumeric(Integer.toString(metode))) {
                    this.method = Integer.valueOf(params[i+1]);
                    continue;
                }

            } else if (params[i].equals("t")) {
                double tz = Double.valueOf(params[i+1]);
                if (!isNumeric(Double.toString(tz))) {
                    valid = false;
                    errorMessage += "Format Timezone Salah";
                } else if (tz > 14 || tz < -10) {
                    valid = false;
                    errorMessage = "Pilihan TimeZone Melebihi Batas";
                } else if (isNumeric(Double.toString(tz))) {
                    timezone = Double.valueOf(params[i+1]);
                    continue;
                }
            }
        }
    }

    private String getCalcMethodNames(int calcMethod) {
        String names = "";
        if (calcMethod == 0) {
            names =  "Ithna Ashari";
        } else if (calcMethod == 1) {
            names = "University of Islamic Sciences, Karachi";
        } else if (calcMethod == 2) {
            names = "Islamic Society of North America (ISNA)";
        } else if (calcMethod == 3) {
            names = "Muslim World League (MWL)";
        } else if (calcMethod == 4) {
            names = "Umm al-Qura, Makkah";
        } else if (calcMethod == 5) {
            names = "Egyptian General Authority of Survey";
        } else if (calcMethod == 6) {
            names = "Institute of Geophysics, University of Tehran";
        }

        return names;
    }

    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    private boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
