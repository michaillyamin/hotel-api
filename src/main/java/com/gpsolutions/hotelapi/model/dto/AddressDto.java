package com.gpsolutions.hotelapi.model.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Integer houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
}
