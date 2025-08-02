package com.gpsolutions.hotelapi.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ContactsDto {

    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;

    @NotEmpty(message = "Email address cannot be empty")
    private String email;
}