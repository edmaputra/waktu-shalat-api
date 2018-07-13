package com.edmaputra.waktushalat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Coba {

    private String tanggal;

    public void helloWorld(String find){
        String[] params = new String[5];
        params = find.split("/");
        for (int i = 0; i < params.length; i++) {
            if (isValidDate(params[i])){
                System.out.println(params[i] + " Date");
            } else if (isNumeric(params[i])) {
                System.out.println(params[i] + " Numeric");
            } else {
                System.out.println(params[i]);
            }

        }

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



    public static void main(String[] args) {
        Coba c = new Coba();
        c.helloWorld("1212208/aku/0");
        PrayTime pray = new PrayTime();
        for (int i = 0; i < pray.getPrayTime(2.7677654, 117.4321452, 8, ))
    }
}
