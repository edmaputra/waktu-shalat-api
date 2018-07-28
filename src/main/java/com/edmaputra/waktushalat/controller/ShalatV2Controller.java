package com.edmaputra.waktushalat.controller;

import com.edmaputra.waktushalat.model.ReturnValue;
import com.edmaputra.waktushalat.model.Shalat;
import com.edmaputra.waktushalat.model.ShalatDetail;
import com.edmaputra.waktushalat.model.maps.GoogleMaps;
import com.edmaputra.waktushalat.model.maps.Lokasi;
import com.edmaputra.waktushalat.util.Converter;
import com.edmaputra.waktushalat.util.GoogleMapsApi;
import com.edmaputra.waktushalat.util.PrayTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shalat")
@Api(value = "ShalatController", description = "Mendapatkan Waktu Shalat di Seluruh Dunia")
public class ShalatV2Controller {

    private LocalDate tanggal;
    private String lokasi;
    private Integer metode;
    private Double timezone;

    private boolean valid;
    private String pesanError;

    @GetMapping
    @ResponseBody
    @ApiOperation(response = ReturnValue.class, value = "Mendapatkan waktu Shalat dengan parameter tanggal, " +
            "lokasi, metode kalkukasi dan zona waktu =(timezone)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pengambilan Data Sukses"),
            @ApiResponse(code = 404, message = "Resource Tidak Ditemukan")
    })
    public ReturnValue getShalat(@RequestParam(value = "d", required = false) String tanggal,
                                 @RequestParam(value = "l", defaultValue = "samarinda", required = false) String lokasi,
                                 @RequestParam(value = "m", defaultValue = "3", required = false) int metode,
                                 @RequestParam(value = "t", defaultValue = "8.0", required = false) double timezone) {

        ReturnValue returnValue = new ReturnValue();

        try {
            if (tanggal == null || tanggal.isEmpty())
                tanggal = Converter.dateToString(new Date());

            if (valid(tanggal, metode, timezone) || valid) {
                init();
                if (tanggal != null || !tanggal.isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                    this.tanggal = LocalDate.parse(tanggal, formatter);
                }
                if (lokasi != null || !lokasi.isEmpty()) {
                    this.lokasi = lokasi;
                }
                this.metode = metode;
                this.timezone = timezone;

                returnValue.setResult(getShalatTime(this.lokasi, this.tanggal, this.metode, this.timezone));
                returnValue.setPesan("OK");
                returnValue.setStatus("OK");
            } else {
                returnValue.setStatus("FAILED");
                returnValue.setPesan(pesanError);
            }
        } catch (Exception e) {
            returnValue.setPesan(pesanError);
            returnValue.setStatus("FAILED");
            e.printStackTrace();
        }

        return returnValue;
    }

    private Shalat getShalatTime(String lokasi, LocalDate tanggal, Integer metode, Double timezone) {
        PrayTime prayTime = new PrayTime();
        Date d = Date.from(tanggal.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        GoogleMaps googleMaps = null;
        try {
            googleMaps = GoogleMapsApi.getGoogleMaps(lokasi);
        } catch (Exception e) {
            valid = false;
            pesanError = "Akses Pencarian Lokasi Terputus";
            e.printStackTrace();

        }

        Lokasi lok = googleMaps.getResults().get(0);
        double lat = lok.getGeometry().getLocation().getLat();
        double lng = lok.getGeometry().getLocation().getLng();
        List<ShalatDetail> prays = prayTime.getListPrayTime(lat, lng, timezone, metode, d);

        Shalat shalat = new Shalat();
        shalat.setTitle("Waktu Shalat");
        shalat.setWilayah(googleMaps.getResults().get(0).getFormatted_address());
        shalat.setTimezone(timezone);
        shalat.setTanggal(d);
        shalat.setTanggalString(Converter.dateToStringDashSeparator(d));
        shalat.setMetode(metode);
        shalat.setNamaMetode(getCalcMethodNames(metode));
        shalat.setShalats(prays);

        return shalat;
    }

    private boolean valid(String tanggal, int metode, double timezone) {
        pesanError = "";
        if (!isValidDate(tanggal)) {
            pesanError += "Format Tanggal Salah; ";
            return false;
        }

        if (!isNumeric(String.valueOf(metode))) {
            pesanError += "Pilihan Metode Kalkulasi Bukan Angka; ";
            return false;
        } else if (metode > 6 || metode < 0) {
            pesanError += "Pilih Metode Kalkulasi antara 0 s/d 6; ";
            return false;
        }

        if (!isNumeric(String.valueOf(timezone))) {
            pesanError += "Pilihan Timezone Bukan Angka; ";
            return false;
        } else if (timezone > 14 || timezone < -10) {
            pesanError += "Pilih Timezone antara -10 s/d 14; ";
            return false;
        }
        return true;
    }

    private void init() {
        valid = true;
        tanggal = LocalDate.now();
        timezone = 8.0;
        metode = 3;
        lokasi = "samarinda";
        pesanError = "";
    }

    private String getCalcMethodNames(int calcMethod) {
        String names = "";
        if (calcMethod == 0) {
            names = "Ithna Ashari";
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

    private boolean isValidDate(String tanggal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            LocalDate a = LocalDate.parse(tanggal, formatter);
            if (a.equals(null)) return false;
        } catch (java.time.DateTimeException pe) {
            return false;
        }
        return true;
    }

    private boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
