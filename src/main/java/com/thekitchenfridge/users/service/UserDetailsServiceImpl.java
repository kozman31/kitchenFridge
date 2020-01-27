package com.thekitchenfridge.users.service;

import com.thekitchenfridge.email.EmailService;
import com.thekitchenfridge.exceptions.UserExistsException;
import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.dto.BasicUserDto;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.dto.UserProfileDto;
import com.thekitchenfridge.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.management.relation.RoleNotFoundException;

@Service
@Slf4j
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private RoleService roleService;
    private EmailService emailService;
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstruct() throws RoleNotFoundException {
        UserProfileDto admin = new UserProfileDto("Admin", "secretPw", 1L, null);
        adminCreateNewUser(admin);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User "+ username + " not found")
        );
    }

    public BasicUserDto getUserProfileByUsername(String username){
        User user = userRepository.getProfileByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        BasicUserDto basicUserDto = modelMapper.map(user, BasicUserDto.class);
        return basicUserDto;
    }

    public void updateAuthorities(UserProfileDto userProfileDto) throws RoleNotFoundException{
        User user = loadUserByUsername(userProfileDto.getUsername());
        Role role = roleService.findRolesByRoleId(userProfileDto.getRoleId());
        user.setRole(role);
        userRepository.save(user);
    }
    public boolean registerNewUser(UserProfileDto userProfileDto) throws UserExistsException {
        if (!userExists(userProfileDto.getUsername())) {
            User user = User.builder()
                    .username(userProfileDto.getUsername())
                    .password(userProfileDto.getPassword())
                    .role(roleService.getDefaultRole())
                    .build();

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user.setFirstName(userProfileDto.getFirstName());
            user.setLastName(userProfileDto.getLastName());
            user.setEmail(userProfileDto.getEmail());
            userRepository.save(user);

            return true;
        }
        return false;
    }

    public boolean adminCreateNewUser(UserProfileDto userProfileDto) throws UserExistsException, RoleNotFoundException {
        if (!userExists(userProfileDto.getUsername())) {
            Role role = roleService.findRolesByRoleId(userProfileDto.getRoleId());
            User user = User.builder()
                    .username(userProfileDto.getUsername())
                    .password(userProfileDto.getPassword())
                    .role(role)
                    .firstName(userProfileDto.getFirstName())
                    .lastName(userProfileDto.getLastName())
                    .email(userProfileDto.getEmail())
                    .isEnabled(true)
                    .build();

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
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
