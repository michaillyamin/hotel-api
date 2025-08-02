package com.gpsolutions.hotelapi.service.impl;

import com.gpsolutions.hotelapi.mapper.HotelMapper;
import com.gpsolutions.hotelapi.model.dto.HotelDto;
import com.gpsolutions.hotelapi.repository.HotelRepository;
import com.gpsolutions.hotelapi.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::convertToHotelDto)
                .toList();
    }
}
