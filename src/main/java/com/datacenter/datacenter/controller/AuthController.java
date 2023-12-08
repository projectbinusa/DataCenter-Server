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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // Check user status
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      if (userDetails.getRole().equals("admin")) {
            // Admin Validation
            if (!userDetails.getStatus().equals("Diterima")) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("Akun Anda belum disetujui. Silakan hubungi administrator untuk persetujuan."));
            }

            // Proceed with normal login flow
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getEmail(),
                    userDetails.getRole(),
                    userDetails.getSekolah()
            ));
        } else if (userDetails.getRole().equals("super admin")) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getEmail(),
                    userDetails.getRole(),
                    userDetails.getSekolah()
            ));
        }  else {
            // Return error message for other roles
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Role tidak ditemukan"));
        }
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
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

        User users = userRepository.save(user);
        return ResponseEntity.ok(users);
    }

}
