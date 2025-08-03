package com.gpsolutions.hotelapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "hotels")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name of the hotel is required")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "brand", nullable = false)
    @NotBlank(message = "Brand of the hotel is required")
    private String brand;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @NotNull(message = "Address is required")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contacts_id", referencedColumnName = "contacts_id")
    @NotNull(message = "Contacts is required")
    private Contacts contacts;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "arrival_time_id", referencedColumnName = "arrival_time_id")
    @NotNull(message = "Arrival time is required")
    private ArrivalTime arrivalTime;

    @Column(name = "amenity")
    @ElementCollection
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    private Set<String> amenities;
}
