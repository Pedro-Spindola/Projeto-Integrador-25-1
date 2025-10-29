package com.lpo.greenlog.controller;

import com.lpo.greenlog.model.Usuario;
import com.lpo.greenlog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200") // Libera o Angular
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        boolean autenticado = authService.autenticar(username, password);
        return Map.of("success", autenticado);
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        return authService.salvarUsuario(usuario);
    }
}
