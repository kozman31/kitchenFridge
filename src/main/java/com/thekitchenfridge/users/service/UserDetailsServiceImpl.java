package com.thekitchenfridge.users.service;

import com.thekitchenfridge.exceptions.UserExistsException;
import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.entity.UserProfileImpl;
import com.thekitchenfridge.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private RoleService roleService;
    private AuthorityService authorityService;
    private UserRepository userRepository;

    @Autowired
    private Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

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
    public boolean registerNewUser(UserProfileImpl userProfile) throws UserExistsException {
        if (!userExists(userProfile.getUsername())) {
            Role profileRole = userProfile.getRole();
            Role role = roleService.findRolesByRoleId(profileRole.getRoleId()).stream().filter(
                    foundRole -> profileRole.equals(foundRole)).findFirst()
                    .orElse(Role.builder().name(profileRole.getAuthority()).roleId(profileRole.getRoleId()).build());

            Set<Authority> userAuthorities = userProfile.getAuthorities();
            Set<Authority> newAuthorities = authorityService.findAllByAuthoritySet(userAuthorities);

            if (!userAuthorities.equals(newAuthorities)) {
                newAuthorities.addAll(userAuthorities);
                newAuthorities = authorityService.saveAuthorities(newAuthorities).stream().collect(Collectors.toSet());
            }
            role.setAuthorities(newAuthorities);
            role = roleService.saveRole(role);

            User user = User.builder()
                    .username(userProfile.getUsername())
                    .password(userProfile.getPassword())
                    .role(role)
                    .build();

            user.setPassword(pbkdf2PasswordEncoder.encode(user.getPassword()));

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

    public boolean updateUserPrivileges(String username, Role role){

        return false;
    }
}
