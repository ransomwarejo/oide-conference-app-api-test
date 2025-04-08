package com.oide.conference_app.services;


import com.oide.conference_app.dto.ConferenceDTO;
import com.oide.conference_app.exception.ResourceNotFoundException;
import com.oide.conference_app.models.Conference;
import com.oide.conference_app.repositories.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public List<ConferenceDTO> getAllConferences() {
        return conferenceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ConferenceDTO getConferenceById(Long id) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference not found with id " + id));
        return convertToDTO(conference);
    }

    public ConferenceDTO createConference(ConferenceDTO conferenceDTO) {
        Conference conference = convertToEntity(conferenceDTO);
        conference.setCreatedAt(LocalDateTime.now());
        conference.setUpdatedAt(LocalDateTime.now());
        Conference savedConference = conferenceRepository.save(conference);
        return convertToDTO(savedConference);
    }

    public ConferenceDTO updateConference(Long id, ConferenceDTO conferenceDTO) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference not found with id " + id));
        conference.setTitle(conferenceDTO.getTitle());
        conference.setDescription(conferenceDTO.getDescription());
        conference.setLocation(conferenceDTO.getLocation());
        conference.setCapacity(conferenceDTO.getCapacity());
        conference.setUpdatedAt(LocalDateTime.now());
        Conference updatedConference = conferenceRepository.save(conference);
        return convertToDTO(updatedConference);
    }

    public void deleteConference(Long id) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference not found with id " + id));
        conferenceRepository.delete(conference);
    }

    // Méthode de conversion entité → DTO
    private ConferenceDTO convertToDTO(Conference conference) {
        ConferenceDTO dto = new ConferenceDTO();
        dto.setId(conference.getId());
        dto.setTitle(conference.getTitle());
        dto.setDescription(conference.getDescription());
        dto.setLocation(conference.getLocation());
        dto.setCapacity(conference.getCapacity());
        return dto;
    }

    // Méthode de conversion DTO → entité
    private Conference convertToEntity(ConferenceDTO dto) {
        Conference conference = new Conference();
        conference.setTitle(dto.getTitle());
        conference.setDescription(dto.getDescription());
        dto.setLocation(conference.getLocation());
        conference.setCapacity(dto.getCapacity());
        return conference;
    }
}
