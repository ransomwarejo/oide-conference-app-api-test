package com.oide.conference_app.services;


import com.oide.conference_app.dto.TouristicSiteDTO;
import com.oide.conference_app.exception.ResourceNotFoundException;
import com.oide.conference_app.models.TouristicSite;
import com.oide.conference_app.repositories.TouristicSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TouristicSiteService {

    private final TouristicSiteRepository siteRepository;

    public List<TouristicSiteDTO> getAllSites() {
        return siteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TouristicSiteDTO getSiteById(Long id) {
        TouristicSite site = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + id));
        return convertToDTO(site);
    }

    public TouristicSiteDTO createSite(TouristicSiteDTO dto) {
        TouristicSite site = convertToEntity(dto);
        site.setCreatedAt(LocalDateTime.now());
        site.setUpdatedAt(LocalDateTime.now());
        TouristicSite savedSite = siteRepository.save(site);
        return convertToDTO(savedSite);
    }

    public TouristicSiteDTO updateSite(Long id, TouristicSiteDTO dto) {
        TouristicSite site = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + id));
        site.setName(dto.getName());
        site.setDescription(dto.getDescription());
        site.setLocation(dto.getLocation());
        site.setCapacity(dto.getCapacity());
        site.setUpdatedAt(LocalDateTime.now());
        TouristicSite updatedSite = siteRepository.save(site);
        return convertToDTO(updatedSite);
    }

    public void deleteSite(Long id) {
        TouristicSite site = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + id));
        siteRepository.delete(site);
    }

    // Conversion entité → DTO
    private TouristicSiteDTO convertToDTO(TouristicSite site) {
        TouristicSiteDTO dto = new TouristicSiteDTO();
        dto.setId(site.getId());
        dto.setName(site.getName());
        dto.setDescription(site.getDescription());
        dto.setLocation(site.getLocation());
        dto.setCapacity(site.getCapacity());
        return dto;
    }

    // Conversion DTO → entité
    private TouristicSite convertToEntity(TouristicSiteDTO dto) {
        TouristicSite site = new TouristicSite();
        site.setName(dto.getName());
        site.setDescription(dto.getDescription());
        site.setLocation(dto.getLocation());
        site.setCapacity(dto.getCapacity());
        return site;
    }
}
