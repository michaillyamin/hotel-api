package com.gpsolutions.hotelapi.model.dto;

import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
