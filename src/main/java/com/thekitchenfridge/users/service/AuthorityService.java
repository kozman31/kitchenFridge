package com.thekitchenfridge.users.service;

import com.thekitchenfridge.security.entities.Authority;

import com.thekitchenfridge.users.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Set<Authority> generateAuthoritySet(Set<Authority> profilesAuthorities){
        Set<Authority> activeAuthorities = findAllByAuthoritySet(profilesAuthorities);
        if (!profilesAuthorities.equals(activeAuthorities)) {
            activeAuthorities.addAll(profilesAuthorities);
            activeAuthorities = saveAuthorities(activeAuthorities);
        }
        return activeAuthorities;
    }

    public Set<Authority> findAllByAuthoritySet(Set<? extends GrantedAuthority> authorities){
        return authorityRepository.findAll().stream().filter(authorities::contains).collect(Collectors.toSet());
    }

    public Set<Authority> saveAuthorities(Set<Authority> authority){
        return authorityRepository.saveAll(authority).stream().collect(Collectors.toSet());
    }




}
