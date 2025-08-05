package com.gpsolutions.hotelapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelRequest {

    @JsonProperty("name")
    @NotBlank(message = "Name of the hotel cannot be empty")
    private String hotelName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("brand")
    @NotBlank(message = "Hotel brand cannot be empty")
    private String brand;

    @JsonProperty("address")
    @NotNull(message = "Address cannot be empty")
    @Valid
    private AddressDto addressDto;

    @JsonProperty("contacts")
    @NotNull(message = "Contacts cannot be empty")
    @Valid
    private ContactsDto contactsDto;

    @JsonProperty("arrivalTime")
    @NotNull(message = "Arrival time cannot be empty")
    @Valid
    private ArrivalTimeDto arrivalTimeDto;
}
