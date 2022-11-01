package com.datacenter.datacenter.controller;

import com.datacenter.datacenter.model.User;
import com.datacenter.datacenter.payload.request.LoginRequest;
import com.datacenter.datacenter.payload.response.JwtResponse;
import com.datacenter.datacenter.payload.response.MessageResponse;
import com.datacenter.datacenter.repository.UserRepository;
import com.datacenter.datacenter.security.jwt.JwtUtils;
import com.datacenter.datacenter.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
                userDetails.getEmail()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        // Validation ketika email sudah digunakan
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Kesalahan: Email telah digunakan!"));
        }

        // Create new user's account
        String role = user.getRole();
        if (role == null) {
            user.setRole("admin");
        } else {
            switch (user.getRole()) {
                case "super_admin":
                    user.setRole("super admin");
                    break;
                default:
                    user.setRole("admin");
            }
        }
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse(" Register telah berhasil!"));
    }

}
