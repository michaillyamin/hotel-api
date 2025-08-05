package com.gpsolutions.hotelapi.controller;

import com.gpsolutions.hotelapi.model.dto.*;
import com.gpsolutions.hotelapi.service.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    @Test
    void getAllHotels_shouldReturnAllHotels() {

        List<HotelDto> expectedHotels = List.of(
                new HotelDto(1L, "DoubleTree by Hilton Minsk",
                        "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms...",
                        "Pobediteley Avenue 9, Minsk, 220004, Belarus",
                        "+375 17 309-80-00"),
                new HotelDto(2L, "Monastyrski Hotel",
                        "Hotel Monastyrsky offers yoga classes and free breakfast, making your trip to Minsk...",
                        "Cyril and Methodius 6, Minsk, 220030, Belarus",
                        "+375 17 329 03 00"),
                new HotelDto(3L, "Buta Boutique Hotel",
                        "Boutique Hotel Buta is a real gem of a cozy quarter in the very center of Minsk...",
                        "Myasnikova 7, Minsk, 220030, Belarus",
                        "+375-29-152-25-55 ")
        );

        when(hotelService.getAllHotels()).thenReturn(expectedHotels);

        ResponseEntity<List<HotelDto>> response = hotelController.getAllHotels();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHotels, response.getBody());
        verify(hotelService, times(1)).getAllHotels();
    }

    @Test
    void getHotelById_shouldReturnHotelWithIdWhenHotelExists() {
        Long expectedHotelId = 1L;

        AddressDto addressDto = new AddressDto(
                9L,
                "Pobediteley Avenue",
                "Minsk",
                "Belarus",
                "220004"
        );

        ContactsDto contactsDto = new ContactsDto(
                "+375 17 309-80-00",
                "doubletreeminsk.info@hilton.com"
        );

        ArrivalTimeDto arrivalTimeDto = new ArrivalTimeDto(
                "14:00",
                "12:00"
        );

        List<String> amenities = List.of(
                "Business center",
                "Fitness center",
                "Concierge",
                "Pet-friendly rooms",
                "Room service",
                "Free parking",
                "Meeting rooms",
                "On-site restaurant",
                "Free WiFi",
                "Non-smoking rooms"
        );

        HotelDetailedDto expectedHotel = new HotelDetailedDto(
                expectedHotelId,
                "DoubleTree by Hilton Minsk",
                "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms...",
                "Hilton",
                addressDto,
                contactsDto,
                arrivalTimeDto,
                amenities
        );

        when(hotelService.getHotelById(expectedHotelId)).thenReturn(expectedHotel);

        ResponseEntity<HotelDetailedDto> response = hotelController.getHotelById(expectedHotelId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHotel, response.getBody());

        verify(hotelService, times(1)).getHotelById(expectedHotelId);
    }

    @Test
    void createHotel_shouldReturnCreatedHotel() {
        AddressDto addressDto = new AddressDto(
                18L,
                "Kirov street",
                "Minsk",
                "Belarus",
                "220030"
        );

        ContactsDto contactsDto = new ContactsDto(
                "+375 (17) 229-70-00",
                "info@president-hotel.by"
        );

        ArrivalTimeDto arrivalTimeDto = new ArrivalTimeDto(
                "14:00",
                "12:00"
        );

        CreateHotelRequest request = new CreateHotelRequest(
                "President Hotel Minsk",
                "Five-star hotel, located in the historical part of the Belarusian capital in close...",
                "President Hotel",
                addressDto,
                contactsDto,
                arrivalTimeDto
        );

        HotelDto expectedHotel = new HotelDto(
                4L,
                "President Hotel Minsk",
                "Five-star hotel, located in the historical part of the Belarusian capital in close...",
                "Kirov street 18, Minsk, 220030, Belarus",
                "+375 (17) 229-70-00"
        );

        when(hotelService.createHotel(request)).thenReturn(expectedHotel);

        ResponseEntity<HotelDto> response = hotelController.createHotel(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedHotel, response.getBody());
        verify(hotelService, times(1)).createHotel(request);
    }

    @Test
    void addAmenitiesToHotel_shouldAddAmenitiesToHotel() {
        Long hotelId = 3L;

        List<String> amenitiesToAdd = List.of(
                "Business center",
                "Meeting rooms"
        );

        ResponseEntity<Void> response = hotelController.addAmenitiesToHotel(hotelId, amenitiesToAdd);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(hotelService, times(1)).addAmenitiesToHotel(hotelId, amenitiesToAdd);
    }
}