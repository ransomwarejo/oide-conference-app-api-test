package com.oide.conference_app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {

    private Long id;
    private Long userId;
    private Long conferenceId;
    private Long siteTouristiqueId;
    private LocalDateTime registrationDate;
}
