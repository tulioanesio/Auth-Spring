package com.tulioanesio.Spring_Auth.controllers;

import com.tulioanesio.Spring_Auth.infra.security.TokenService;
import com.tulioanesio.Spring_Auth.model.user.User;
import com.tulioanesio.Spring_Auth.model.user.dtos.AuthenticationDTO;
import com.tulioanesio.Spring_Auth.model.user.dtos.LoginResponseDTO;
import com.tulioanesio.Spring_Auth.model.user.dtos.RegisterDTO;
import com.tulioanesio.Spring_Auth.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().body("Email already registered");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());

        this.repository.save(newUser);
         return ResponseEntity.ok().build();
    }
}
