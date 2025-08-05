package com.gpsolutions.hotelapi.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrivalTimeDto {

    @NotEmpty(message = "Check in time cannot be empty")
    private String checkIn;

    private String checkOut;
}