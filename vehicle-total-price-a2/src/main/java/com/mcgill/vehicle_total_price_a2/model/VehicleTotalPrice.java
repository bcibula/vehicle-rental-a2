package com.mcgill.vehicle_total_price_a2.model;

public class VehicleTotalPrice {

    private String vehicle;
    private String season;
    private int days;
    private int dailyRate;
    private int totalPrice;
    private String port;

    public VehicleTotalPrice(String vehicle, String season, int days, int dailyRate, int totalPrice, String port) {
        this.vehicle = vehicle;
        this.season = season;
        this.days = days;
        this.dailyRate = dailyRate;
        this.totalPrice = totalPrice;
        this.port = port;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getSeason() {
        return season;
    }

    public int getDays() {
        return days;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDailyRate() {
        return dailyRate;
    }

    public String getPort() {
        return port;
    }
}
