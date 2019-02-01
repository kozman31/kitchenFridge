package com.thekitchenfridge.users.service;

import com.thekitchenfridge.exceptions.UserExistsException;
import com.thekitchenfridge.security.Authority;
import com.thekitchenfridge.security.Role;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.entity.UserProfileImpl;
import com.thekitchenfridge.users.repository.AuthorityRepository;
import com.thekitchenfridge.users.repository.RoleRepository;
import com.thekitchenfridge.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private RoleService roleService;
    private AuthorityService authorityService;

    private RoleRepository roleRepository;
    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User "+ username + "not found")
        );
    }

    public void updateAuthorities(UserProfileImpl userProfile){
        User user = loadUserByUsername(userProfile.getUsername());
        user.setRole(userProfile.getRole());
        userRepository.save(user);
    }
    public User registerNewUser(UserProfileImpl userProfile) throws UserExistsException {
        if (userExists(userProfile.getUsername())) {
            throw new UserExistsException(
                    "Error: User account" + userProfile.getUsername()+" already exists.");
        }
        Role profileRole=userProfile.getRole();
        Role role = roleRepository.getRolesByName(profileRole.getName()).stream().filter(
                foundRole -> profileRole.equals(foundRole)).findFirst()
                .orElse(Role.builder().name(profileRole.getAuthority()).build());

        if(role.getAuthorities().isEmpty()){
            Set<String> existingAuthorities = authorityRepository.findAll().stream().map(Authority::getAuthority).collect(Collectors.toSet());
            Set<Authority> newAuthorities = new HashSet<>();
            for(Authority auth:userProfile.getAuthorities()){
               if (existingAuthorities.contains(auth.getAuthority())){
                   newAuthorities.add(auth);
               }
               else{
                   Authority newAuthority = new Authority(auth.getId(), auth.getAuthority());
                   newAuthorities.add(newAuthority);
               }
           }
            newAuthorities = authorityRepository.saveAll(newAuthorities).stream().collect(Collectors.toSet());
            role.setAuthorities(newAuthorities);
        }
        role = roleRepository.save(role);

        User user = User.builder()
                .username(userProfile.getUsername())
                .password(userProfile.getPassword())
                .role(role)
                .build();

        user.setPassword(passwordEncoder.encode(userProfile.getPassword()));

        //user.setFirstName(userProfile.getFirstName());
        //user.setLastName(userProfile.getLastName());
        //user.setEmail(userProfile.getEmail());
        return userRepository.save(user);
    }

    private boolean userExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }
}
