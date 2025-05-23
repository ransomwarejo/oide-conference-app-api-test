package com.oide.conference_app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TouristicSiteDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    private int capacity;
}
