package com.gpsolutions.hotelapi.service.impl;

import com.gpsolutions.hotelapi.mapper.AddressMapper;
import com.gpsolutions.hotelapi.mapper.ContactsMapper;
import com.gpsolutions.hotelapi.mapper.HotelMapper;
import com.gpsolutions.hotelapi.model.dto.CreateHotelRequest;
import com.gpsolutions.hotelapi.model.dto.HotelDetailedDto;
import com.gpsolutions.hotelapi.model.dto.HotelDto;
import com.gpsolutions.hotelapi.model.entity.Address;
import com.gpsolutions.hotelapi.model.entity.ArrivalTime;
import com.gpsolutions.hotelapi.model.entity.Contacts;
import com.gpsolutions.hotelapi.model.entity.Hotel;
import com.gpsolutions.hotelapi.model.exception.HotelNotFoundException;
import com.gpsolutions.hotelapi.repository.HotelRepository;
import com.gpsolutions.hotelapi.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final AddressMapper addressMapper;
    private final ContactsMapper contactsMapper;

    @Override
    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::convertToHotelDto)
                .toList();
    }

    @Override
    public HotelDetailedDto getHotelById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Cannot find hotel with id: " + hotelId));
        return hotelMapper.convertToHotelDetailedDto(hotel);
    }

    @Override
    @Transactional
    public HotelDto createHotel(CreateHotelRequest createHotelRequest) {
        Hotel hotel = new Hotel();
        hotel.setName(createHotelRequest.getHotelName());
        hotel.setDescription(createHotelRequest.getDescription());
        hotel.setBrand(createHotelRequest.getBrand());

        Address address = addressMapper.convertToAddress(createHotelRequest.getAddressDto());
        address.setId(null);
        hotel.setAddress(address);

        Contacts contacts = contactsMapper.convertToContacts(createHotelRequest.getContactsDto());
        contacts.setId(null);
        hotel.setContacts(contacts);

        if (createHotelRequest.getArrivalTimeDto() != null) {
            ArrivalTime arrivalTime = new ArrivalTime();
            arrivalTime.setCheckIn(createHotelRequest.getArrivalTimeDto().getCheckIn());
            arrivalTime.setCheckOut(createHotelRequest.getArrivalTimeDto().getCheckOut());
            arrivalTime.setId(null);
            hotel.setArrivalTime(arrivalTime);
        }

        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.convertToHotelDto(savedHotel);
    }

    @Override
    @Transactional
    public void addAmenitiesToHotel(Long hotelId, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Cannot find hotel with id: " + hotelId));


        Set<String> currentAmenities = hotel.getAmenities();

        Set<String> newAmenities = amenities.stream()
                .filter(amenity -> amenity != null && !amenity.isEmpty())
                .filter(amenity -> !currentAmenities.contains(amenity))
                .collect(Collectors.toSet());

        if (!newAmenities.isEmpty()) {
            currentAmenities.addAll(newAmenities);
            hotel.setAmenities(currentAmenities);
        }

        hotelRepository.save(hotel);
    }
}
