package com.thekitchenfridge.users.service;

import com.thekitchenfridge.exceptions.UserExistsException;
import com.thekitchenfridge.security.entities.Authority;
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
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private RoleService roleService;
    private AuthorityService authorityService;
    private UserRepository userRepository;

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
        Role role = generateRole(userProfile);
        user.setRole(role);
        userRepository.save(user);
    }
    public boolean registerNewUser(UserProfileImpl userProfile) throws UserExistsException {
        if (!userExists(userProfile.getUsername())) {
            Role role = generateRole(userProfile);
            User user = User.builder()
                    .username(userProfile.getUsername())
                    .password(userProfile.getPassword())
                    .role(role)
                    .build();

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            //user.setFirstName(userProfile.getFirstName());
            //user.setLastName(userProfile.getLastName());
            //user.setEmail(userProfile.getEmail());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private boolean userExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    private Role generateRole(UserProfileImpl userProfile){
        Role profileRole = userProfile.getRole();
        Role role = roleService.findRolesByRoleId(profileRole.getRoleId()).stream().filter(
                singleRole -> profileRole.equals(singleRole)).findFirst()
                .orElse(Role.builder().name(profileRole.getAuthority()).roleId(profileRole.getRoleId()).build());

        if(!role.getAuthorities().equals(profileRole)) {
            Set<Authority> activeAuthorities = generateAuthoritySet(userProfile.getAuthorities());
            role.setAuthorities(activeAuthorities);
            role = roleService.saveRole(role);
        }
        return role;
    }

    private Set<Authority> generateAuthoritySet(Set<Authority> profilesAuthorities){
        Set<Authority> activeAuthorities = authorityService.findAllByAuthoritySet(profilesAuthorities);
        if (!profilesAuthorities.equals(activeAuthorities)) {
            activeAuthorities.addAll(profilesAuthorities);
            activeAuthorities = authorityService.saveAuthorities(activeAuthorities).stream().collect(Collectors.toSet());
        }
        return activeAuthorities;
    }
}
