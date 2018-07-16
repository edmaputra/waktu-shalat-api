package com.edmaputra.waktushalat;

import com.edmaputra.waktushalat.util.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Coba {

    private String tanggal;

    private Date date = new Date();
    private String address = null;
    private Integer method = 3;
    private double timezone = 8.0;

    private boolean valid = true;
    private String errorMessage = "";

    public void helloWorld(String find){
        String[] params = new String[10];
        params = find.split("/");
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("d")) {
                if (isValidDate(params[i+1])) {
                    date = Converter.stringToDate(params[i+1]);
                } else {
                    valid = false;
                    errorMessage += "Format Tanggal Salah";
                }
            } else if (params[i].equals("l")) {
                address = params[i+1];
            } else if (params[i].equals("m")) {
                method = new Integer(params[i+1]);
            } else if (params[i].equals("t")) {
                timezone = new Double(params[i+1]);
            }
        }

        if (valid) {
            PrayTime prayTime = new PrayTime();
            List<String> list = prayTime.getPrayTime(2.7677654, 117.4321452, timezone, method, date);
            System.out.println(setMessage(list, date, timezone, method));

        } else {
            System.out.println(errorMessage);
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



    public static void main(String[] args) {
        Coba c = new Coba();
        c.helloWorld("d/11112020/l/kota+bangun/m/3/t/8");

    }
}
