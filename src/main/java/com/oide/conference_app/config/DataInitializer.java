package com.oide.conference_app.config;

import com.oide.conference_app.models.Role;
import com.oide.conference_app.models.User;
import com.oide.conference_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.findByEmail("superadmin@example.com").isPresent()) {
            User superAdmin = new User();
            superAdmin.setFistName("Super");
            superAdmin.setLastName("Admin");
            superAdmin.setEmail("superadmin@example.com");
            superAdmin.setPhoneNumber("0000000000");
            superAdmin.setRole(Role.SUPER_ADMIN);
            superAdmin.setParticipantType(null);
            superAdmin.setPassword("Test@2025");
            userRepository.save(superAdmin);
        }
    }
}
