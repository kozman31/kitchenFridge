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

    public Set<Authority> findAllByAuthoritySet(Set<? extends GrantedAuthority> authorities){
        return authorityRepository.findAll().stream().filter(authorities::contains).collect(Collectors.toSet());
    }

    public Optional<Authority> findAuthoritiesById(Long authId){
        return authorityRepository.findById(authId);
    }
    public List<Authority> saveAuthorities(Set<Authority> authority){
        return authorityRepository.saveAll(authority);
    }




}
