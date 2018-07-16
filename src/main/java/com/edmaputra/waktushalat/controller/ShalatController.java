package com.edmaputra.waktushalat.controller;

import com.edmaputra.waktushalat.PrayTime;
import com.edmaputra.waktushalat.util.Converter;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shalat")
public class ShalatController {

    private String tanggal;

    private Date date = new Date();
    private String address = null;
    private Integer method = 3;
    private double timezone = 8.0;

    private boolean valid = true;
    private String errorMessage = "";

    @GetMapping(value = "/{d1}/{v1}")
    @ResponseBody
    public String oneParamater(@PathVariable("d1") String k1, @PathVariable("v1") String v1){
        String pesan = new String();
        String find = k1+"/"+v1;
        String[] params = new String[8];
        params = find.split("/");
        if (params.length > 8) {
            valid = false;
            errorMessage += "Parameter Melebihi Batas";
        } else {
            setParameter(params);
        }

        if (valid) {
            pesan = getFinalMessage(date, timezone, method);
        } else {
            pesan = errorMessage;
        }
        return pesan;
    }

    private String getFinalMessage(Date date, double timezone, Integer method){
        PrayTime prayTime = new PrayTime();
        List<String> list = prayTime.getPrayTime(2.7677654, 117.4321452, timezone, method, date);
        return setMessage(list, date, timezone, method);
    }

    private void setParameter(String[] params) {
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("d")) {
                if (isValidDate(params[i+1])) {
                    date = Converter.stringToDate(params[i+1]);
                    continue;
                } else {
                    valid = false;
                    errorMessage += "Format Tanggal Salah";
                }
            } else if (params[i].equals("l")) {
                address = params[i+1];
                continue;
            } else if (params[i].equals("m")) {
                int metode = new Integer(params[i+1]);
                if (metode > 6 || metode < 0){
                    valid = false;
                    errorMessage += "Pilihan Metode Kalkulasi Tidak Ada, Harap Pilih 0-6 \n";
                } else if (!isNumeric(Integer.toString(metode))) {
                    valid = false;
                    errorMessage += "Format Metode Salah \n";
                } else if (isNumeric(Integer.toString(metode))) {
                    this.method = new Integer(params[i+1]);
                    continue;
                }

            } else if (params[i].equals("t")) {
                double tz = new Double(params[i+1]);
                if (tz > 14 || tz < -10) {
                    valid = false;
                    errorMessage += "Pilihan TimeZone Melebihi Batas \n";
                } else if (!isNumeric(Double.toString(tz))) {
                    valid = false;
                    errorMessage += "Format Timezone Salah \n";
                } else if (isNumeric(Double.toString(tz))) {
                    timezone = new Double(params[i+1]);
                    continue;
                }
            }
        }
    }

    private String setMessage(List<String> list, Date date, double timezone, Integer method){
        String pesan = new String();
        String tanggal = Converter.dateToStringDashSeparator(date);
        pesan += "WAKTU SHALAT\n" +
                "Untuk Daerah \n" +
                "Tanggal : " + tanggal +"\n" +
                "Timezone : "+ (int) timezone +" \n" +
                "Metode Kalkulasi : "+ getCalcMethodNames(method) + "\n\n";
        for (int i = 0; i < list.size(); i++){
            pesan += list.get(i) + "\n";
        }

        return pesan;
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
            tanggal = inDate.trim();
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
