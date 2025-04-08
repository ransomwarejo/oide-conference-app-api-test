package com.oide.conference_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "conferences")
public class Conference extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String location;

    @Column(nullable = false)
    private int capacity;


    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Registration> registrations = new ArrayList<>();
}

