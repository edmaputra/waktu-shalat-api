package com.edmaputra.waktushalat.controller;

import com.edmaputra.waktushalat.model.Shalat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HandlingController {

    @GetMapping(value = "/error")
    @ResponseBody
    public Shalat errorGetter() {
        Shalat shalat = new Shalat();
        shalat.setPesan("PARAMATER TIDAK DIKENALI ATAU FORMAT TIDAK BENAR");
        shalat.setStatus("FAILED");
        return shalat;
    }
}
