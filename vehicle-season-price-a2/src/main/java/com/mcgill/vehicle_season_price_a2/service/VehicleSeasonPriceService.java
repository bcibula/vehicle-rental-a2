package com.mcgill.vehicle_season_price_a2.service;

import com.mcgill.vehicle_season_price_a2.model.VehiclePrice;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Service
public class VehicleSeasonPriceService {

    // This keeps the pricing information loaded from the JSON file in memory.
    private List<VehiclePrice> vehiclePrices;

    // This runs once when the app starts so the price data is ready to use.
    @PostConstruct
    public void loadData() throws Exception {
        // This helper reads the JSON file for us.
        ObjectMapper objectMapper = new ObjectMapper();
        // This opens the JSON file that lives inside the app's resources folder.
        InputStream is = getClass().getResourceAsStream("/vehicle_season.json");
        // This turns the JSON file into a Java list of vehicle price objects.
        vehiclePrices = objectMapper.readValue(is, new TypeReference<List<VehiclePrice>>() {});
    }


    // This finds the right price for a vehicle and a season.
    public Integer getPrice(String vehicleType, String season){
        // normalize input to Title Case
        // Make the input look the same way each time so matching is easier.
        String normalizedVehicle = normalize(vehicleType);
        // Do the same cleanup for the season name.
        String normalizedSeason = normalize(season);

        // Check each vehicle until we find the one the user asked for.
        for (VehiclePrice vp : vehiclePrices) {
            // Compare names without caring about uppercase or lowercase letters.
            if (vp.getVehicleType().equalsIgnoreCase(normalizedVehicle)) {
                // Return the price for that season.
                return vp.getPricing().get(normalizedSeason);
            }
        }
        // If nothing matches, return no price.
        return null;
    }


    // converts "summer" or "SUMMER" to "Summer"
    // This changes text into a simple standard format.
    private String normalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        // Keep the first letter capitalized and make the rest lowercase.
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

}
