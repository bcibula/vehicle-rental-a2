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

    private List<VehiclePrice> vehiclePrices;

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/vehicle_season.json");
        vehiclePrices = objectMapper.readValue(is, new TypeReference<List<VehiclePrice>>() {});
    }


    public Integer getPrice(String vehicleType, String season){
        // normalize input to Title Case
        String normalizedVehicle = normalize(vehicleType);
        String normalizedSeason = normalize(season);

        for (VehiclePrice vp : vehiclePrices) {
            if (vp.getVehicleType().equalsIgnoreCase(normalizedVehicle)) {
                return vp.getPricing().get(normalizedSeason);
            }
        }
        return null;
    }


    // converts "summer" or "SUMMER" to "Summer"
    private String normalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

}
