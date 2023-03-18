package com.sexydari.betriush.controllers;

import com.sexydari.betriush.auth.jwt.JwtUtils;
import com.sexydari.betriush.auth.services.UserDetailsImpl;
import com.sexydari.betriush.mongodb.models.User;
import com.sexydari.betriush.mongodb.models.UserRole;
import com.sexydari.betriush.mongodb.models.UserRoleModel;
import com.sexydari.betriush.mongodb.repositories.UserRepository;
import com.sexydari.betriush.mongodb.repositories.UserRoleRepository;
import com.sexydari.betriush.payload.request.LoginRequest;
import com.sexydari.betriush.payload.request.SignupRequest;
import com.sexydari.betriush.payload.response.JwtResponse;
import com.sexydari.betriush.payload.response.MessageResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(i -> i.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId().toString(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        if(userRepository.existsUserByUsername(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if(userRepository.existsUserByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        List<UserRole> strRoles = signupRequest.getRoles();
        List<UserRoleModel> userRoles = strRoles.stream().map(i -> {
           return userRoleRepository.findByName(i);
        }).collect(Collectors.toList());

        User user = new User(new ObjectId(), signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()), userRoles);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
