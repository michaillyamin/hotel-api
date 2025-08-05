package com.gpsolutions.hotelapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
