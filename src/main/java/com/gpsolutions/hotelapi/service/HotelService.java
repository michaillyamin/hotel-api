package com.gpsolutions.hotelapi.service;

import com.gpsolutions.hotelapi.model.dto.CreateHotelRequest;
import com.gpsolutions.hotelapi.model.dto.HotelDetailedDto;
import com.gpsolutions.hotelapi.model.dto.HotelDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getAllHotels();

    HotelDetailedDto getHotelById(Long hotelId);

    HotelDto createHotel(CreateHotelRequest request);

    void addAmenitiesToHotel(Long hotelId, List<String> amenities);
}
