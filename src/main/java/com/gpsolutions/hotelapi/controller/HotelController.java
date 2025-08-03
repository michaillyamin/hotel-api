package com.gpsolutions.hotelapi.controller;

import com.gpsolutions.hotelapi.model.dto.CreateHotelRequest;
import com.gpsolutions.hotelapi.model.dto.HotelDetailedDto;
import com.gpsolutions.hotelapi.model.dto.HotelDto;
import com.gpsolutions.hotelapi.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        List<HotelDto> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDetailedDto> getHotelById(@PathVariable Long id) {
        HotelDetailedDto hotelDetailedDto = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotelDetailedDto);
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody CreateHotelRequest createHotelRequest) {
        HotelDto createdHotel = hotelService.createHotel(createHotelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenitiesToHotel(@PathVariable Long id,
                                                     @RequestBody List<String> amenities) {
        hotelService.addAmenitiesToHotel(id, amenities);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHotelsHistogram(@PathVariable String param) {
        return ResponseEntity.ok(hotelService.getHotelsHistogram(param));
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelDto>> searchHotels(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String brand,
                                                       @RequestParam(required = false) String city,
                                                       @RequestParam(required = false) String country,
                                                       @RequestParam(required = false) String amenity) {
        List<HotelDto> searchedHotels = hotelService.searchHotels(name, brand, city, country, amenity);
        return ResponseEntity.ok(searchedHotels);
    }
}
