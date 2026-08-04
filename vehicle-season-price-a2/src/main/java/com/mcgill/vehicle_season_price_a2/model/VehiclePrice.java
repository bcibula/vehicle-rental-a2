package com.mcgill.vehicle_season_price_a2.model;

import java.util.Map;

// This class represents one pricing record loaded by the Vehicle Season Price service.
// Each record contains a vehicle type and a collection of daily rental prices organized by season.
public class VehiclePrice {

    private String vehicleType;

    // Stores the daily rental price for each season.
    // The season name is the key and the price is the value.
    private Map<String, Integer> pricing;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Map<String, Integer> getPricing() {
        return pricing;
    }

    public void setPricing(Map<String, Integer> pricing) {
        this.pricing = pricing;
    }
}
