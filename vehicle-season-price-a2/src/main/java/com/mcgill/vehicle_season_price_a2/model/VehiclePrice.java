package com.mcgill.vehicle_season_price_a2.model;

// This import lets us store price values in a key/value list.
import java.util.Map;

// This class holds one vehicle type and its season-based prices.
public class VehiclePrice {

    // The kind of vehicle, truck, SUV, or sedan, etc.
    private String vehicleType;

    // The pricing information - seasons and their prices.
    private Map<String, Integer> pricing;

    // Gets the vehicle type.
    public String getVehicleType() {
        return vehicleType;
    }

    // Sets the vehicle type.
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    // Gets the pricing details.
    public Map<String, Integer> getPricing() {
        return pricing;
    }

    // Sets the pricing details.
    public void setPricing(Map<String, Integer> pricing) {
        this.pricing = pricing;
    }
}
