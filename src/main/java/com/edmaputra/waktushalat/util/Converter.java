package com.edmaputra.waktushalat.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {

    public static Date stringToDate(String tgl) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date date;
        try {
            if (tgl.equals("") || tgl.equals(null)) {
                date = new Date();
            }
            date = formatter.parse(tgl);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    public static String dateToStringDashSeparator(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

}
