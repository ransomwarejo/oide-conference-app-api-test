package com.oide.conference_app.controllers;


import com.oide.conference_app.dto.ConferenceDTO;
import com.oide.conference_app.dto.TouristicSiteDTO;
import com.oide.conference_app.dto.UserCreationDTO;
import com.oide.conference_app.dto.UserResponseDTO;
import com.oide.conference_app.models.ParticipantType;
import com.oide.conference_app.models.User;
import com.oide.conference_app.services.ConferenceService;
import com.oide.conference_app.services.TouristicSiteService;
import com.oide.conference_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
//@PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
public class AdminController {

    private final ConferenceService conferenceService;
    private final TouristicSiteService siteTouristiqueService;
    private final UserService userService;

    // ------- CRUD pour les Conférences -------

    @GetMapping("/conferences")
    public ResponseEntity<List<ConferenceDTO>> getAllConferences() {
        List<ConferenceDTO> conferences = conferenceService.getAllConferences();
        return ResponseEntity.ok(conferences);
    }

    @GetMapping("/conferences/{id}")
    public ResponseEntity<ConferenceDTO> getConferenceById(@PathVariable Long id) {
        ConferenceDTO conference = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(conference);
    }

    @PostMapping("/conferences")
    public ResponseEntity<ConferenceDTO> createConference(@RequestBody ConferenceDTO conferenceDTO) {
        ConferenceDTO created = conferenceService.createConference(conferenceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/conferences/{id}")
    public ResponseEntity<ConferenceDTO> updateConference(@PathVariable Long id, @RequestBody ConferenceDTO conferenceDTO) {
        ConferenceDTO updated = conferenceService.updateConference(id, conferenceDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/conferences/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    // ------- CRUD pour les Sites Touristiques -------

    @GetMapping("/sites")
    public ResponseEntity<List<TouristicSiteDTO>> getAllSites() {
        List<TouristicSiteDTO> sites = siteTouristiqueService.getAllSites();
        return ResponseEntity.ok(sites);
    }

    @GetMapping("/sites/{id}")
    public ResponseEntity<TouristicSiteDTO> getSiteById(@PathVariable Long id) {
        TouristicSiteDTO site = siteTouristiqueService.getSiteById(id);
        return ResponseEntity.ok(site);
    }

    @PostMapping("/sites")
    public ResponseEntity<TouristicSiteDTO> createSite(@RequestBody TouristicSiteDTO siteDTO) {
        TouristicSiteDTO created = siteTouristiqueService.createSite(siteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/sites/{id}")
    public ResponseEntity<TouristicSiteDTO> updateSite(@PathVariable Long id, @RequestBody TouristicSiteDTO siteDTO) {
        TouristicSiteDTO updated = siteTouristiqueService.updateSite(id, siteDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/sites/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        siteTouristiqueService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }

    // ------- CRUD pour les Utilisateurs VIP -------

    @GetMapping("/vipusers")
    public ResponseEntity<List<UserResponseDTO>> getAllVipUsers() {
        List<UserResponseDTO> vipUsers = userService.getAllVipUsers();
        return ResponseEntity.ok(vipUsers);
    }

    @GetMapping("/vipusers/{id}")
    public ResponseEntity<UserResponseDTO> getVipUserById(@PathVariable Long id) {
        UserResponseDTO vipUser = userService.getVipUserById(id);
        return ResponseEntity.ok(vipUser);
    }

    @PostMapping("/vipusers")
    public ResponseEntity<UserResponseDTO> createVipUser(@RequestBody UserCreationDTO user) {
        // On force le type de participant à VIP pour ces utilisateurs
        user.setParticipantType(ParticipantType.VIP);
        UserResponseDTO created = userService.createVipUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/vipusers/{id}")
    public ResponseEntity<UserResponseDTO> updateVipUser(@PathVariable Long id, @RequestBody User user) {
        user.setParticipantType(ParticipantType.VIP);
        UserResponseDTO updated = userService.updateVipUser(id, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/vipusers/{id}")
    public ResponseEntity<Void> deleteVipUser(@PathVariable Long id) {
        userService.deleteVipUser(id);
        return ResponseEntity.noContent().build();
    }
}
