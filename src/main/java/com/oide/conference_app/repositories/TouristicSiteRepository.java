package com.oide.conference_app.repositories;

import com.oide.conference_app.models.TouristicSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TouristicSiteRepository extends JpaRepository<TouristicSite, Long> {
    Optional<TouristicSite> findByName(String name);
}
