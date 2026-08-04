package com.mcgill.vehicle_season_price_a2.service;

import com.mcgill.vehicle_season_price_a2.model.VehiclePrice;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

// This service manages the vehicle pricing data used by the application.
// It loads the seasonal pricing information from JSON and provides the daily rental rate requested by the controller.
@Service
public class VehicleSeasonPriceService {

    private List<VehiclePrice> vehiclePrices;

    // Loads the vehicle pricing data when the service starts.
    // The JSON records are converted into VehiclePrice objects that can be searched during rental price requests.
    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/vehicle_season.json");
        vehiclePrices = objectMapper.readValue(is, new TypeReference<List<VehiclePrice>>() {});
    }

    // Finds the daily rental price for the requested vehicle type and season.
    // Input values are normalized first so requests like "summer" and "SUMMER" match the same pricing data.
    public Integer getPrice(String vehicleType, String season){
        String normalizedVehicle = normalize(vehicleType);
        String normalizedSeason = normalize(season);

        for (VehiclePrice vp : vehiclePrices) {
            if (vp.getVehicleType().equalsIgnoreCase(normalizedVehicle)) {
                return vp.getPricing().get(normalizedSeason);
            }
        }
        return null;
    }

    // Converts user input into the format used by the JSON pricing data.
    // This allows the service to handle different capitalization styles.
    private String normalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

}
