package com.example.demo.dao.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Table(name = "tickets")
@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = PRIVATE)
public class Ticket {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;


    @ManyToOne(fetch = LAZY,
            cascade = {MERGE, PERSIST}
            , optional = false)
    @JoinColumn(name = "guest_id")
    Guest guest;


    @ManyToOne(fetch = LAZY,
            cascade = {MERGE, PERSIST}
            , optional = false)
    @JoinColumn(name = "travel_id")
    Travel travel;


}
