package com.mcgill.vehicle_total_price_a2.model;

// This class stores information about a vehicle rental and its total price
public class VehicleTotalPrice {

    // The type of vehicle being rented (e.g., car, truck, SUV)
    private String vehicle;
    // The time of year for the rental (e.g., summer, winter, spring)
    private String season;
    // How many days the vehicle is being rented for
    private int days;
    // The price per day for renting this vehicle
    private int dailyRate;
    // The final total price for the entire rental
    private int totalPrice;
    // The server port number this service is running on (e.g., 8200)
    private String port;

    // Constructor - this method initializes (sets up) a new VehicleTotalPrice object with all the rental details
    public VehicleTotalPrice(String vehicle, String season, int days, int dailyRate, int totalPrice, String port) {
        this.vehicle = vehicle;
        this.season = season;
        this.days = days;
        this.dailyRate = dailyRate;
        this.totalPrice = totalPrice;
        this.port = port;
    }

    // This method returns the type of vehicle that was rented
    public String getVehicle() {
        return vehicle;
    }

    // This method returns the season when the rental took place
    public String getSeason() {
        return season;
    }

    // This method returns how many days the vehicle was rented for
    public int getDays() {
        return days;
    }

    // This method returns the final total price of the entire rental
    public int getTotalPrice() {
        return totalPrice;
    }

    // This method returns the daily rental price for this vehicle
    public int getDailyRate() {
        return dailyRate;
    }

    // This method returns the server port number this service is running on
    public String getPort() {
        return port;
    }
}
