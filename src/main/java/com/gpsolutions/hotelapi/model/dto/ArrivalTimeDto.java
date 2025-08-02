package com.gpsolutions.hotelapi.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ArrivalTimeDto {

    @NotEmpty(message = "Check in time cannot be empty")
    private String checkIn;

    private String checkOut;
}