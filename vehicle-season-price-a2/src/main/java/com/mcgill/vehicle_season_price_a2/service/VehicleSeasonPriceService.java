package com.mcgill.vehicle_season_price_a2.service;

import com.mcgill.vehicle_season_price_a2.model.VehiclePrice;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

// Calculates the total rental price.
// It first requests the daily rate from the Vehicle Season Price service and then multiplies that rate by the number of rental days.
@Service
public class VehicleSeasonPriceService {

    private List<VehiclePrice> vehiclePrices;

    // Loads vehicle_season.json once after Spring creates this service.
    // Jackson converts each JSON record into a VehiclePrice object.
    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/vehicle_season.json");
        vehiclePrices = objectMapper.readValue(is, new TypeReference<List<VehiclePrice>>() {});
    }

    // Normalizes the request values and searches the loaded pricing data.
    // Returns the daily price for the matching vehicle and season.
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

    // Converts input such as "SUMMER" or "summer" to "Summer"
    // so it matches the names used in the JSON pricing data.
    private String normalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

}
