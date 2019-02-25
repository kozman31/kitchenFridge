package com.thekitchenfridge.users.service;

import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.users.repository.AuthorityRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AuthorityServiceTest {

    @Configuration
    static class AuthorityServiceTestConfiguration{
        @Bean
        public AuthorityService authorityService(){
            return new AuthorityService();
        }
        @Bean
        public AuthorityRepository authorityRepository(){
            return Mockito.mock(AuthorityRepository.class);
        }
    }

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AuthorityRepository authorityRepository;

    Authority authority1;
    Authority authority2;
    Authority authority3;
    Authority authorityRole1;
    Authority authorityRole2;

    List<Authority> authorityListAll;
    List<Authority> authorityListAdmin;
    List<Authority> authorityListUser;

    List<Authority> rtrnLst = new ArrayList<>();

    Set<Authority> authoritySetAll = new HashSet();
    Set<Authority> authoritySetAdmin = new HashSet();
    Set<Authority> authoritySetUser = new HashSet();

    @Before
    public void setUp() throws Exception {
        authority1 = Authority.builder().authorityId(11L).name("READ").build();
        authority2 = Authority.builder().authorityId(12L).name("CREATE").build();
        authority3 = Authority.builder().authorityId(13L).name("EDIT").build();

        authorityRole1 = Authority.builder().authorityId(1L).name("ROLE_ADMIN").build();
        authorityRole2 = Authority.builder().authorityId(2L).name("ROLE_USER").build();

        authorityListAll = Arrays.asList(authorityRole1, authorityRole2, authority1, authority2, authority3);
        authorityListAdmin = Arrays.asList(authorityRole1, authority1, authority2, authority3);
        authorityListUser = Arrays.asList(authorityRole2, authority2);


        authoritySetAll.addAll(authorityListAll);
        authoritySetAdmin.addAll(authorityListAdmin);
        authoritySetUser.addAll(authorityListUser);

        Mockito.when(authorityRepository.findAll()).thenReturn(authorityListAll);
        Mockito.when(authorityRepository.saveAll(Mockito.anySet())).thenAnswer(i -> ((Set<Authority>)i.getArguments()[0]).stream().collect(Collectors.toList()));
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void generateAuthoritySet() {
        Set<Authority> authList = authorityService.generateAuthoritySet(authoritySetAdmin);
        assertEquals(authoritySetAdmin, authList);
    }

    @Test
    public void findAllByAuthoritySetAdmin() {
        Set<Authority> authSet = authorityService.findAllByAuthoritySet(authoritySetAdmin);
        assertEquals(authoritySetAdmin, authSet);
    }

    @Test
    public void findAllByAuthoritySetUser() {
        Set<Authority> authSet = authorityService.findAllByAuthoritySet(authoritySetUser);
        assertEquals(authoritySetUser, authSet);
    }

    @Test
    public void saveAuthoritiesAdmin() {
        Set<Authority> authSet = authorityService.saveAuthorities(authoritySetAdmin);
        assertEquals(authoritySetAdmin, authSet);
    }

    @Test
    public void saveAuthoritiesUser() {
        Set<Authority> authSet = authorityService.saveAuthorities(authoritySetUser);
        assertEquals(authoritySetUser, authSet);
    }
}