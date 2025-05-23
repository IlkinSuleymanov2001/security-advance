package com.example.demo.dao.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Table(name = "guests")
@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = PRIVATE)
public class Guest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(nullable = false, name = "legal_id")
    String legalId;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phoneNumber;

    @Column(name = "country_code")
    Integer countryCode;

    @Column(name = "is_in_travel")
    Boolean isInTravel = false;

    @ManyToOne(fetch = LAZY,
            cascade = {MERGE, PERSIST})
    @JoinColumn(name = "travel_id")
    Travel travel;


    @OneToMany(mappedBy = "guest")
    List<Ticket> tickets;

    public Guest() {
        tickets = new ArrayList<>();
    }
}
