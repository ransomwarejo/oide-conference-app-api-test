package com.oide.conference_app.repositories;

import com.oide.conference_app.models.Conference;
import com.oide.conference_app.models.Registration;
import com.oide.conference_app.models.TouristicSite;
import com.oide.conference_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(User user);
    List<Registration> findByUserId(Long userId);
    List<Registration> findByConference(Conference conference);
    List<Registration> findByConferenceId(Long conferenceId);
    List<Registration> findByTouristicSite(TouristicSite touristicSite);
    List<Registration> findByTouristicSiteId(Long touristicSiteId);
}
