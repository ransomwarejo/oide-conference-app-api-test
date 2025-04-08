package com.oide.conference_app.repositories;

import com.oide.conference_app.models.ParticipantType;
import com.oide.conference_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByParticipantType(ParticipantType participantType);

}

