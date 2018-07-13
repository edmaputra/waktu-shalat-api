package com.edmaputra.waktushalat.controller;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/shalat")
public class ShalatController {

    private String tanggal;

    @GetMapping(value = "/{find}")
    @ResponseBody
    public String helloWorld(@PathVariable("find") String find){


        if (isValidDate(find)){
            return tanggal;
        } else {
            return "Bukan Tanggal";
        }
    }



    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MMM yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
            tanggal = inDate.trim();
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

}
