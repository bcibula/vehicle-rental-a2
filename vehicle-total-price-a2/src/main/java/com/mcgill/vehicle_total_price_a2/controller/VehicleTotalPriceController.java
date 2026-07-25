package com.mcgill.vehicle_total_price_a2.controller;

import com.mcgill.vehicle_total_price_a2.model.VehicleTotalPrice;
import com.mcgill.vehicle_total_price_a2.service.VehicleTotalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleTotalPriceController {

    @Autowired
    private VehicleTotalPriceService service;

    @GetMapping("/total-price")
    public ResponseEntity<VehicleTotalPrice> getTotalPrice(
            @RequestParam String vehicle,
            @RequestParam String season,
            @RequestParam int days) {

        VehicleTotalPrice result = service.calculateTotalPrice(vehicle, season, days);
        return ResponseEntity.ok(result);


    }

}
