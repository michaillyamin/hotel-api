package com.gpsolutions.hotelapi.service;

import com.gpsolutions.hotelapi.model.dto.HotelDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getAllHotels();
}
