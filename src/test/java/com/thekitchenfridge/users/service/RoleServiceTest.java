package com.thekitchenfridge.users.service;

import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.users.repository.AuthorityRepository;
import com.thekitchenfridge.users.repository.RoleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.management.relation.RoleNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RoleServiceTest {

    @Configuration
    static class AuthorityServiceTestConfiguration{
        @Bean
        public RoleService roleService(){
            return new RoleService();
        }
        @Bean
        public RoleRepository roleRepository(){
            return Mockito.mock(RoleRepository.class);
        }

        @Bean
        public AuthorityRepository authorityRepository(){
            return Mockito.mock(AuthorityRepository.class);
        }

        @Bean
        public AuthorityService authorityService(){
            return Mockito.mock(AuthorityService.class);
        }
    }

    Role roleAdmin1;
    Role roleAdmin2;
    Role roleUser;

    Authority authority1;
    Authority authority2;
    Authority authority3;

    List<Authority> authorityListAdmin1;
    List<Authority> authorityListAdmin2;
    List<Authority> authorityListUser1;

    Set<Authority> authoritySetAdmin1 = new HashSet<>();
    Set<Authority> authoritySetAdmin2 = new HashSet<>();
    Set<Authority> authoritySetUser = new HashSet<>();

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Mock
    AuthorityRepository authorityRepository;

    @Autowired
    @InjectMocks
    AuthorityService authorityService;

    @Before
    public void setUp() throws Exception {

        authority1 = Authority.builder().authorityId(11L).name("READ").build();
        authority2 = Authority.builder().authorityId(12L).name("CREATE").build();
        authority3 = Authority.builder().authorityId(13L).name("EDIT").build();

        authorityListAdmin1 = Arrays.asList(authority1, authority2, authority3);
        authorityListAdmin2 = Arrays.asList(authority1, authority2);
        authorityListUser1 = Arrays.asList(authority2);

        authoritySetAdmin1.addAll(authorityListAdmin1);
        authoritySetAdmin2.addAll(authorityListAdmin2);
        authoritySetUser.addAll(authorityListUser1);

        roleAdmin1 = Role.builder().roleId(1L).roleName("ROLE_ADMIN").authorities(authoritySetAdmin1).build();
        roleAdmin2 = Role.builder().roleId(1L).roleName("ROLE_ADMIN").authorities(authoritySetAdmin2).build();
        roleUser = Role.builder().roleId(2L).roleName("ROLE_USER").authorities(authoritySetUser).build();

        Mockito.when(roleRepository.save(Mockito.any(Role.class))).then(i -> i.getArguments()[0]);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void generateRoleAdmin1() {
        Mockito.when(roleRepository.findRoleByRoleId(Mockito.anyLong())).thenReturn(Optional.ofNullable(roleAdmin1));
        Mockito.when(authorityService.generateAuthoritySet(Mockito.anySet())).thenReturn(authoritySetAdmin1);

        Role role = roleService.createRole(roleAdmin1);
        assertEquals(roleAdmin1, role);
    }

    @Test
    public void generateRoleAdmin2() {
        Mockito.when(roleRepository.findRoleByRoleId(Mockito.anyLong())).thenReturn(Optional.ofNullable(roleAdmin2));
        Mockito.when(authorityService.generateAuthoritySet(Mockito.anySet())).thenReturn(authoritySetAdmin2);

        Role role = roleService.createRole(roleAdmin2);
        assertEquals(Optional.ofNullable(roleAdmin2), role);
    }

    @Test
    public void findRolesByRoleId() throws RoleNotFoundException {
        Mockito.when(roleRepository.findRoleByRoleId(Mockito.anyLong())).thenReturn(Optional.ofNullable(roleAdmin1));
        Role roles = roleService.findRolesByRoleId(1L);
        assertEquals(Optional.ofNullable(roleAdmin1), roles);
    }

    @Test
    public void saveRole() {
        Role role = roleService.saveRole(roleAdmin1);
        assertEquals(roleAdmin1, role);
    }

}