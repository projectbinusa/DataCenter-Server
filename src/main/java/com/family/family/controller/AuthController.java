package com.family.family.controller;

import com.family.family.model.User;
import com.family.family.payload.request.LoginRequest;
import com.family.family.payload.request.SignupRequest;
import com.family.family.payload.response.JwtResponse;
import com.family.family.payload.response.MessageResponse;
import com.family.family.repository.UserRepository;
import com.family.family.security.jwt.JwtUtils;
import com.family.family.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, Long id) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getNamaSekolah()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Kesalahan: Email telah digunakan!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getNamaSekolah(), signUpRequest.getAlamatSekolah(), signUpRequest.getTeleponSekolah());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse(" Register telah berhasil!"));
    }

    @GetMapping("/useraall")
    public ResponseEntity<?> userAll() {
        List<User> user = userRepository.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
