package com.oide.conference_app.services;


import com.oide.conference_app.dto.UserCreationDTO;
import com.oide.conference_app.dto.UserResponseDTO;
import com.oide.conference_app.exception.ResourceNotFoundException;
import com.oide.conference_app.models.ParticipantType;
import com.oide.conference_app.models.Role;
import com.oide.conference_app.models.User;
import com.oide.conference_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Récupérer tous les utilisateurs VIP
    public List<UserResponseDTO> getAllVipUsers() {
        List<User> vipUsers = userRepository.findByParticipantType(ParticipantType.VIP);
        return vipUsers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserResponseDTO getVipUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        if (user.getParticipantType() != ParticipantType.VIP) {
            throw new IllegalArgumentException("User with id " + id + " is not a VIP user");
        }
        return convertToDTO(user);
    }

    public UserResponseDTO createVipUser(UserCreationDTO dto) {
        // Vérifier si l'utilisateur existe déjà si nécessaire
        User user = new User();
        user.setFistName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setCountry(dto.getCountry());
        user.setParticipantType(ParticipantType.VIP);
        user.setRole(Role.PARTICIPANT);
        // Gestion du mot de passe (à adapter selon la logique)
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserResponseDTO updateVipUser(Long id, User dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        if (user.getParticipantType() != ParticipantType.VIP) {
            throw new IllegalArgumentException("User with id " + id + " is not a VIP user");
        }
        user.setFistName(dto.getFistName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteVipUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        if (user.getParticipantType() != ParticipantType.VIP) {
            throw new IllegalArgumentException("User with id " + id + " is not a VIP user");
        }
        userRepository.delete(user);
    }

    // Conversion entité → DTO (sans exposer le mot de passe)
    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFistName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setParticipantType(user.getParticipantType());
        dto.setRole(user.getRole());
        return dto;
    }
}
