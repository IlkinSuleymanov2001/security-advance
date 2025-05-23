package com.example.demo.dao.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;


@Table(name = "travels")
@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = PRIVATE)
public class Travel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "from_date")
    LocalDateTime fromDate;

    @Column(name = "country")
    String country;


    @Column(name = "to_date")
    LocalDateTime toDate;

    @OneToMany(mappedBy = "travel",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    List<Guest> guests;

    @OneToMany(mappedBy = "travel",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    List<Ticket> tickets;


    public Travel() {
        guests = new ArrayList<>();
        tickets = new ArrayList<>();
    }


}
