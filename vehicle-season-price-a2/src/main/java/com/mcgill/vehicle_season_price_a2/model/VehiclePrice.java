package com.mcgill.vehicle_season_price_a2.model;

import java.util.Map;

public class VehiclePrice {

    private String vehicleType;
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
