package com.gpsolutions.hotelapi.mapper;

import com.gpsolutions.hotelapi.model.dto.HotelDto;
import com.gpsolutions.hotelapi.model.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelMapper {

    private final ModelMapper modelMapper;

    public HotelDto convertToHotelDto(Hotel hotel) {
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);

        if (hotel.getAddress() != null) {
            hotelDto.setAddress(hotel.getAddress().getFullAddress());
        }

        if (hotel.getContacts() != null) {
            hotelDto.setPhone(hotel.getContacts().getPhone());
        }

        return hotelDto;
    }
}
