package com.thekitchenfridge.users.service;

import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.dto.UserProfileDto;
import com.thekitchenfridge.users.repository.RoleRepository;
import com.thekitchenfridge.users.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class UserDetailsServiceImplTest {

    @Configuration
    static class AuthorityServiceTestConfiguration{
        @Bean
        public RoleService roleService(){
            return Mockito.mock(RoleService.class);
        }
        @Bean
        public RoleRepository userRepository(){
            return Mockito.mock(RoleRepository.class);
        }

        @Bean
        public UserRepository authorityRepository(){
            return Mockito.mock(UserRepository.class);
        }

//        @Bean
//        public UserDetailsServiceImpl authorityService(){
//            return new UserDetailsServiceImpl();
//        }
    }

    UserProfileDto userProfileDtoAdmin, erProfileUser;
    User userAdmin, userUser;
    Role roleAdmin, RoleUser, userSuper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    RoleService roleService;

    @Mock
    RoleRepository roleRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void loadUserByUsername() {
    }

    @Test
    public void updateAuthorities() {
    }

    @Test
    public void registerNewUser() {
    }

    @Test
    public void userExists() {
    }

    @Test
    public void updateUserDetails() {
    }
}