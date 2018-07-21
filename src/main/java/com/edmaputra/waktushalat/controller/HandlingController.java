package com.edmaputra.waktushalat.controller;

import com.edmaputra.waktushalat.model.ReturnValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HandlingController {

    @GetMapping(value = "/error")
    public ReturnValue errorGetter() {
        ReturnValue shalat = new ReturnValue();
        shalat.setPesan("PARAMATER TIDAK DIKENALI ATAU FORMAT TIDAK BENAR");
        shalat.setStatus("FAILED");
        return shalat;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
