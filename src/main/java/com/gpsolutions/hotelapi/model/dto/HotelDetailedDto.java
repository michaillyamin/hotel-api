package com.gpsolutions.hotelapi.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelDetailedDto {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private AddressDto addressDto;
    private ContactsDto contactsDto;
    private ArrivalTimeDto arrivalTimeDto;
    private List<String> amenities;
}
