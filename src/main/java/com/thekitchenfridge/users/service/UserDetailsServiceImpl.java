package com.thekitchenfridge.users.service;

import com.thekitchenfridge.email.EmailService;
import com.thekitchenfridge.exceptions.UserExistsException;
import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.entity.UserProfileImpl;
import com.thekitchenfridge.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private RoleService roleService;
    private EmailService emailService;
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User "+ username + " not found")
        );
    }

    public void updateAuthorities(UserProfileImpl userProfile){
        User user = loadUserByUsername(userProfile.getUsername());
        Role role = roleService.generateRole(userProfile.getRole());
        user.setRole(role);
        userRepository.save(user);
    }
    public boolean registerNewUser(UserProfileImpl userProfile) throws UserExistsException {
        if (!userExists(userProfile.getUsername())) {
            Role role = roleService.generateRole(userProfile.getRole());
            User user = User.builder()
                    .username(userProfile.getUsername())
                    .password(userProfile.getPassword())
                    .role(role)
                    .build();

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user.setFirstName(userProfile.getFirstName());
            user.setLastName(userProfile.getLastName());
            user.setLocation(userProfile.getLocation());
            user.setEmail(userProfile.getEmail());
            userRepository.save(user);

            String id = UUID.randomUUID().toString();
            emailService.confirmNewUser(id, user);

            return true;
        }
        return false;
    }

    public boolean userExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public void updateUserDetails(User user){
        userRepository.save(user);
    }
}
