package com.gpsolutions.hotelapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contacts {

    @Id
    @Column(name = "contacts_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @Email(message = "Provide valid Email")
    private String email;
}
