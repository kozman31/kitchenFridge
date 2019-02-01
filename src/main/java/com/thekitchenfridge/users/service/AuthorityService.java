package com.thekitchenfridge.users.service;

import com.thekitchenfridge.security.Authority;
import com.thekitchenfridge.users.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthorityService {

    private AuthorityRepository authorityRepository;

    public List<Authority> getAuthorities(){
        return authorityRepository.findAll();
    }

    public void saveAuthorities(List<Authority>
                                        authority){
        authorityRepository.saveAll(authority);
    }
}
