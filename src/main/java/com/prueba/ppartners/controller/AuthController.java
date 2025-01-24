package com.prueba.ppartners.controller;

import com.prueba.ppartners.exception.ResourceNotFoundException;
import com.prueba.ppartners.exception.UnauthorizedException;
import com.prueba.ppartners.security.CustomUserDetailsService;
import com.prueba.ppartners.security.JwtTokenUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          CustomUserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/auth/usuarios")
    public String getUsuarios() {
        boolean userValid = false; 
        if (!userValid) {
            throw new UnauthorizedException("Credenciales inválidas");
        }
        return "Lista de usuarios";
    }

    @GetMapping("/usuarios")
    public List<String> getAllUsers() {
        List<String> usuarios = List.of(); 

        if (usuarios.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron usuarios en el sistema.");
        }

        return usuarios;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        try {
            
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

            if (userDetails.getUsername() == null || userDetails.getUsername().isEmpty()) {
                throw new IllegalArgumentException("El nombre de usuario no puede ser nulo o vacío");
            }
            

            // Generar JWT
            return jwtTokenUtil.generateToken(userDetails.getUsername());

        } catch (Exception e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}
