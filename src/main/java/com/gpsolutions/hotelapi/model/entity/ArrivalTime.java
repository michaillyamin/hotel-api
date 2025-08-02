package com.gpsolutions.hotelapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.*;

@Entity
@Table(name = "arrival_time")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArrivalTime {

    @Id
    @Column(name = "arrival_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in", nullable = false)
    private String checkIn;

    @Column(name = "check_out", nullable = false)
    private String checkOut;
}
