package com.edmaputra.waktushalat.controller;

import com.edmaputra.waktushalat.model.ReturnValue;
import com.edmaputra.waktushalat.model.Shalat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HandlingController {

    @GetMapping(value = "/error")
    @ResponseBody
    public ReturnValue errorGetter() {
        ReturnValue shalat = new ReturnValue();
        shalat.setPesan("PARAMATER TIDAK DIKENALI ATAU FORMAT TIDAK BENAR");
        shalat.setStatus("FAILED");
        return shalat;
    }
}
