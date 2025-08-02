package com.gpsolutions.hotelapi.service.impl;

import com.gpsolutions.hotelapi.mapper.HotelMapper;
import com.gpsolutions.hotelapi.model.dto.HotelDetailedDto;
import com.gpsolutions.hotelapi.model.dto.HotelDto;
import com.gpsolutions.hotelapi.model.entity.Hotel;
import com.gpsolutions.hotelapi.model.exception.HotelNotFoundException;
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

    @Override
    public HotelDetailedDto getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Cannot find hotel with id: " + id));
        return hotelMapper.convertToHotelDetailedDto(hotel);
    }
}
