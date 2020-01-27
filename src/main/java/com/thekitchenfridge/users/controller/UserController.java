package com.thekitchenfridge.users.controller;

import com.thekitchenfridge.dto.BasicUserDto;
import com.thekitchenfridge.security.JwtUtility;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.dto.UserProfileDto;
import com.thekitchenfridge.users.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired JwtUtility jwtUtility;

    @PostMapping(value="/register")
    public ResponseEntity<String> registerUser(@RequestBody UserProfileDto userProfileDto){
        if(userDetailsService.registerNewUser(userProfileDto)){
            return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Username Already Exists", HttpStatus.OK);
    }

    @PostMapping(value="/login")
    public ResponseEntity<BasicUserDto> login(HttpServletResponse rsp){
        String token = rsp.getHeader("Authorization");
        Jws<Claims> claims = jwtUtility.getClaims(token);
        String username = claims.getBody().getSubject();

        BasicUserDto user = userDetailsService.getUserProfileByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value="/user/update")
    public ResponseEntity<HttpStatus> updateUserRoles(@RequestBody UserProfileDto userProfileDto) throws RoleNotFoundException {
        userDetailsService.updateAuthorities(userProfileDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/user/activate")
    public ResponseEntity activateUser(@RequestParam("token") String token){
        System.out.println(token);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping(value="/users/profile/{username}")
    public ResponseEntity userProfile(@PathVariable String username){
        User user = userDetailsService.loadUserByUsername(username);
        if (user == null){
            return new ResponseEntity (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
