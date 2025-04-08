package com.oide.conference_app.services;


import com.oide.conference_app.dto.RegistrationDTO;
import com.oide.conference_app.dto.RegistrationRequestDTO;
import com.oide.conference_app.models.*;
import com.oide.conference_app.repositories.ConferenceRepository;
import com.oide.conference_app.repositories.RegistrationRepository;
import com.oide.conference_app.repositories.TouristicSiteRepository;
import com.oide.conference_app.repositories.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;
    private final ConferenceRepository conferenceRepository;
    private final TouristicSiteRepository touristicSiteRepository;




    private RegistrationDTO convertToDTO(Registration registration) {
        RegistrationDTO dto = new RegistrationDTO();
        dto.setId(registration.getId());
        dto.setUserId(registration.getUser().getId());
        dto.setConferenceId(registration.getConference().getId());
        dto.setSiteTouristiqueId(registration.getTouristicSite().getId());
        return dto;
    }


    public void registerUser(Long personId, Long conferenceId, Long touristSiteId) {
        User user = userRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Personne non trouvée"));

        if (conferenceId != null) {
            Conference conference = conferenceRepository.findById(conferenceId)
                    .orElseThrow(() -> new RuntimeException("Conférence non trouvée"));
            Registration inscriptionConf = new Registration();
            inscriptionConf.setUser(user);
            inscriptionConf.setConference(conference);
            registrationRepository.save(inscriptionConf);
        }

        if (touristSiteId != null) {
            TouristicSite touristSite = touristicSiteRepository.findById(touristSiteId)
                    .orElseThrow(() -> new RuntimeException("Site touristique non trouvé"));
            Registration inscriptionSite = new Registration();
            inscriptionSite.setUser(user);
            inscriptionSite.setTouristicSite(touristSite);
            registrationRepository.save(inscriptionSite);
        }
    }

    public List<Registration> getInscriptionsByPerson(Long personId) {
        return registrationRepository.findByUserId(personId);
    }

    public List<RegistrationRequestDTO> getInscriptionsByConference(Long conferenceId) {
        List<Registration> registrations = registrationRepository.findByConferenceId(conferenceId);

        return registrations.stream().map(registration -> new RegistrationRequestDTO(
                registration.getId(),
                registration.getUser().getEmail(),
                registration.getConference().getTitle()
        )).collect(Collectors.toList());
    }

    public List<Registration> getInscriptionsByTouristSite(Long touristSiteId) {
        return registrationRepository.findByTouristicSiteId(touristSiteId);
    }
}
