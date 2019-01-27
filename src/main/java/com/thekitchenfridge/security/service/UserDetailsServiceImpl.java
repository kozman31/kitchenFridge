package com.thekitchenfridge.security.service;

import com.thekitchenfridge.exceptions.UserExistsException;
import com.thekitchenfridge.security.entity.User;
import com.thekitchenfridge.security.entity.UserProfile;
import com.thekitchenfridge.security.repository.UserRepository;
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

    public User registerNewUser(UserProfile userProfile) throws UserExistsException {
        if (userExists(userProfile.getUsername())) {
            throw new UserExistsException(
                    /*"There is an account with that username:" + userProfile.getUsername()*/);
        }
        User user = User.builder()
                .username(userProfile.getUsername())
                .password(userProfile.getPassword())
                .build();
        //user.setFirstName(userProfile.getFirstName());
        //user.setLastName(userProfile.getLastName());

        user.setPassword(passwordEncoder.encode(userProfile.getPassword()));

        //user.setEmail(userProfile.getEmail());
        //user.setRole(new Role(Integer.valueOf(1), user));
        return userRepository.save(user);
    }

    private boolean userExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }
}
