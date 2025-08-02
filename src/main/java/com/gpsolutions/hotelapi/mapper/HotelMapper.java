package com.gpsolutions.hotelapi.mapper;

import com.gpsolutions.hotelapi.model.dto.*;
import com.gpsolutions.hotelapi.model.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

    public HotelDetailedDto convertToHotelDetailedDto(Hotel hotel) {
        HotelDetailedDto hotelDetailedDto = modelMapper.map(hotel, HotelDetailedDto.class);

        if (hotel.getAddress() != null) {
            hotelDetailedDto.setAddressDto(modelMapper.map(hotel.getAddress(), AddressDto.class));
        }

        if (hotel.getContacts() != null) {
            hotelDetailedDto.setContactsDto(modelMapper.map(hotel.getContacts(), ContactsDto.class));
        }

        if (hotel.getArrivalTime() != null) {
            hotelDetailedDto.setArrivalTimeDto(modelMapper.map(hotel.getArrivalTime(), ArrivalTimeDto.class));
        }

        if (hotel.getAmenities() != null) {
            hotelDetailedDto.setAmenities(new ArrayList<>(hotel.getAmenities()));
        }

        return hotelDetailedDto;
    }
}
