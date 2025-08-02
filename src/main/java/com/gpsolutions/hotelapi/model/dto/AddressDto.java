package com.gpsolutions.hotelapi.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {

    @NotNull(message = "Number of house cannot be empty")
    private Long houseNumber;

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Country cannot be empty")
    private String country;

    @NotEmpty(message = "Post code cannot be empty")
    private String postCode;
}
