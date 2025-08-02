package com.gpsolutions.hotelapi.mapper;

import com.gpsolutions.hotelapi.model.dto.AddressDto;
import com.gpsolutions.hotelapi.model.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address convertToAddress(AddressDto addressDto) {
         return Address.builder()
                 .houseNumber(addressDto.getHouseNumber())
                 .street(addressDto.getStreet())
                 .city(addressDto.getCity())
                 .country(addressDto.getCountry())
                 .postCode(addressDto.getPostCode())
                 .build();
    }
}