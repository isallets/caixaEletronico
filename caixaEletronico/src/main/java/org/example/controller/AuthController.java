package org.example.controller;

import org.example.Usuario;
import org.example.dto.AuthResponse; // Nova importação
import org.example.dto.LoginRequest; // Nova importação
import org.example.dto.RegisterRequest; // Nova importação
import org.example.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            Usuario novoUsuario = usuarioService.cadastrarUsuario(
                    request.getNome(),
                    request.getSenha(),
                    request.getTipoConta()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new AuthResponse(novoUsuario.getNome(), "Usuário cadastrado com sucesso!", true)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    new AuthResponse(null, e.getMessage(), false)
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioAutenticado = usuarioService.autenticarUsuario(
                request.getNome(),
                request.getSenha()
        );

        if (usuarioAutenticado.isPresent()) {
            return ResponseEntity.ok(
                    new AuthResponse(usuarioAutenticado.get().getNome(), "Login bem-sucedido!", true)
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AuthResponse(null, "Nome de usuário ou senha inválidos.", false)
            );
        }
    }
}