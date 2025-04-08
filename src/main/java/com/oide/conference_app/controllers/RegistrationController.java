package com.oide.conference_app.controllers;


import com.oide.conference_app.dto.ConferenceDTO;
import com.oide.conference_app.dto.RegistrationDTO;
import com.oide.conference_app.dto.RegistrationRequestDTO;
import com.oide.conference_app.dto.TouristicSiteDTO;
import com.oide.conference_app.models.Registration;
import com.oide.conference_app.services.ConferenceService;
import com.oide.conference_app.services.RegistrationService;
import com.oide.conference_app.services.TouristicSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/registrations",produces = MediaType.APPLICATION_JSON_VALUE)
//@PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN','PARTICIPANT')")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final TouristicSiteService touristicSiteService;
    private final ConferenceService conferenceService;

    @PostMapping("/register")
    public ResponseEntity<String> inscrirePersonne(
            @RequestParam Long personId,
            @RequestParam(required = false) Long conferenceId,
            @RequestParam(required = false) Long touristSiteId) {

        if (conferenceId == null && touristSiteId == null) {
            return ResponseEntity.badRequest().body("Vous devez fournir au moins une conférence ou un site touristique.");
        }

        registrationService.registerUser(personId, conferenceId, touristSiteId);
        return ResponseEntity.ok("Inscription effectuée avec succès !");
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<Registration>> getInscriptionsByPerson(@PathVariable Long personId) {
        List<Registration> inscriptions = registrationService.getInscriptionsByPerson(personId);
        return ResponseEntity.ok(inscriptions);
    }

    @GetMapping(value = "/conferences/{id}")
    public ResponseEntity<List<RegistrationRequestDTO>> getInscriptionsByConference(@PathVariable Long id) {
        List<RegistrationRequestDTO> inscriptions = registrationService.getInscriptionsByConference(id);
        return ResponseEntity.ok(inscriptions);
    }

    @GetMapping("/site/{touristSiteId}")
    public ResponseEntity<List<Registration>> getInscriptionsByTouristSite(@PathVariable Long touristSiteId) {
        List<Registration> inscriptions = registrationService.getInscriptionsByTouristSite(touristSiteId);
        return ResponseEntity.ok(inscriptions);
    }

}
