package com.gpsolutions.hotelapi.controller;

import com.gpsolutions.hotelapi.model.dto.CreateHotelRequest;
import com.gpsolutions.hotelapi.model.dto.HotelDetailedDto;
import com.gpsolutions.hotelapi.model.dto.HotelDto;
import com.gpsolutions.hotelapi.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "REST API сервис для управления отелями")
public class HotelController {

    private final HotelService hotelService;

    @Operation(
            summary = "Получить список всех отелей",
            description = "Возвращает список всех отелей в системе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        List<HotelDto> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @Operation(
            summary = "Получить отель по ID",
            description = "Возвращает детальную информацию об отеле",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Отель найден"),
                    @ApiResponse(responseCode = "404", description = "Отель не найден"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDetailedDto> getHotelById(@Parameter(description = "id отеля", example = "3") @PathVariable Long id) {
        HotelDetailedDto hotelDetailedDto = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotelDetailedDto);
    }

    @Operation(
            summary = "Создать новый отель",
            description = "Добавляет новый отель в систему",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Отель успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    @PostMapping("/hotels")
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody CreateHotelRequest createHotelRequest) {
        HotelDto createdHotel = hotelService.createHotel(createHotelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @Operation(
            summary = "Добавить удобства к отелю",
            description = "Добавляет список удобств к указанному отелю",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удобства добавлены"),
                    @ApiResponse(responseCode = "404", description = "Отель не найден"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenitiesToHotel(@Parameter(description = "id отеля", example = "2") @PathVariable Long id,
                                                     @RequestBody List<String> amenities) {
        hotelService.addAmenitiesToHotel(id, amenities);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получить статистику по отелям",
            description = "Возвращает статистику отлей по указанному параметру (brand, city, country, amenities)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Статистика получена"),
                    @ApiResponse(responseCode = "400", description = "Некорректный параметр"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHotelsHistogram(@Parameter(description = "Параметр поиска", example = "brand")
                                                                    @PathVariable String param) {
        return ResponseEntity.ok(hotelService.getHotelsHistogram(param));
    }

    @Operation(
            summary = "Поиск отелей",
            description = "Поиск отелей по заданным параметрам",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Результаты поиска"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера")
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<HotelDto>> searchHotels(
            @Parameter(description = "Название отеля", example = "Buta Boutique Hotel")
            @RequestParam(required = false) String name,

            @Parameter(description = "Бренд отеля", example = "Boutique")
            @RequestParam(required = false) String brand,

            @Parameter(description = "Город", example = "Minsk")
            @RequestParam(required = false) String city,

            @Parameter(description = "Страна", example = "Belarus")
            @RequestParam(required = false) String country,

            @Parameter(description = "Удобство", example = "Free WiFi")
            @RequestParam(required = false) String amenity) {
        List<HotelDto> searchedHotels = hotelService.searchHotels(name, brand, city, country, amenity);
        return ResponseEntity.ok(searchedHotels);
    }
}