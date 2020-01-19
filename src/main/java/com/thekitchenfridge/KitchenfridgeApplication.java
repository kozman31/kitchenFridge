package com.thekitchenfridge;

import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.security.entities.Role;
import com.thekitchenfridge.users.entity.UserProfileDto;
import com.thekitchenfridge.users.service.UserDetailsServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties
public class KitchenfridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitchenfridgeApplication.class, args);
	}

//	@Bean
//	private CommandLineRunner initDatabase(UserDetailsServiceImpl userDetailsService) {
//		Authority authority = new Authority();
//		authority.setName("admin");
//		Set<Authority> authSet = new HashSet<>();
//		authSet.add(authority);
//		Role.builder().roleName("ADMIN").authorities(authSet).build();
//		UserProfileDto user = new UserProfileDto("Admin", "secretPw", 1L);
//
//		};
//	}
}

/*
private String username;
private String password;
private Role role;
private String firstName;
private String lastName;
private String email;*/
