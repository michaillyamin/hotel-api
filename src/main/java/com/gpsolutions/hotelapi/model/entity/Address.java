package com.gpsolutions.hotelapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_number", nullable = false)
    @NotNull(message = "House number is required")
    private Long houseNumber;

    @Column(name = "street", nullable = false)
    @NotBlank(message = "Street of address is required")
    private String street;

    @Column(name = "city", nullable = false)
    @NotBlank(message = "City is required")
    private String city;

    @Column(name = "country", nullable = false)
    @NotBlank(message = "Country is required")
    private String country;

    @Column(name = "post_code", nullable = false)
    @NotBlank(message = "Post code is required")
    private String postCode;

    public String getFullAddress() {
        return String.format("%s %d, %s, %s, %s",
                street, houseNumber, city, postCode, country);
    }
}
