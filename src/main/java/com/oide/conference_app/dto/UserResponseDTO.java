package com.oide.conference_app.dto;


import com.oide.conference_app.models.ParticipantType;
import com.oide.conference_app.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String country;
    private Role role;
    private ParticipantType participantType;

}

