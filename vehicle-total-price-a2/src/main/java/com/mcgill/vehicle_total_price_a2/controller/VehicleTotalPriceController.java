package com.mcgill.vehicle_total_price_a2.controller;

// This file handles web requests related to calculating a vehicle's total price.
import com.mcgill.vehicle_total_price_a2.model.VehicleTotalPrice;
import com.mcgill.vehicle_total_price_a2.service.VehicleTotalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// This tells Spring that this class will answer HTTP requests.
@RestController
public class VehicleTotalPriceController {

    // Spring puts the service object here automatically so we can use it.
    @Autowired
    private VehicleTotalPriceService service;

    // When someone visits /total-price, this method runs.
    @GetMapping("/total-price")
    public ResponseEntity<VehicleTotalPrice> getTotalPrice(
            // The type of vehicle the user wants.
            @RequestParam String vehicle,
            // The season to use for pricing.
            @RequestParam String season,
            // The number of days the vehicle is needed.
            @RequestParam int days) {

        // Ask the service to calculate the total price using the values from the request.
        VehicleTotalPrice result = service.calculateTotalPrice(vehicle, season, days);
        // Send the result back as a successful web response.
        return ResponseEntity.ok(result);


    }

}
