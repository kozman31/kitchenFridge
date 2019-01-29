package com.thekitchenfridge.users.service;

import com.thekitchenfridge.exceptions.UserExistsException;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.entity.UserProfileImpl;
import com.thekitchenfridge.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User "+ username + "not found")
        );
    }

    public void updateAuthorities(UserProfileImpl userProfile){
        User user = loadUserByUsername(userProfile.getUsername());
        user.setRoles(userProfile.getRoles());
        userRepository.save(user);
    }
    public User registerNewUser(UserProfileImpl userProfile) throws UserExistsException {
        if (userExists(userProfile.getUsername())) {
            throw new UserExistsException(
                    "Error: User account" + userProfile.getUsername()+" already exists.");
        }
        User user = User.builder()
                .username(userProfile.getUsername())
                .password(userProfile.getPassword())
                .roles(userProfile.getRoles())
                .build();
        //user.setFirstName(userProfile.getFirstName());
        //user.setLastName(userProfile.getLastName());

        user.setPassword(passwordEncoder.encode(userProfile.getPassword()));

        //user.setEmail(userProfile.getEmail());
        return userRepository.save(user);
    }

    private boolean userExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }
}
