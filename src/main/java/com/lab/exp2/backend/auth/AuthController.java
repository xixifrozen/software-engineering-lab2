package com.lab.exp2.backend.auth;

import com.lab.exp2.backend.user.User;
import com.lab.exp2.backend.user.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // 之后前端用 Vite 跑在5173
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repo;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReq req) {
        return repo.findByUsername(req.getUsername())
                .filter(u -> u.getPassword().equals(req.getPassword()))
                .map(u -> ResponseEntity.ok("Login success"))
                .orElse(ResponseEntity.status(401).body("Login failed"));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginReq req) {
        if (repo.existsByUsername(req.getUsername())) {
            return ResponseEntity.badRequest().body("Username exists");
        }
        repo.save(User.builder().username(req.getUsername()).password(req.getPassword()).build());
        return ResponseEntity.ok("Registered");
    }

    @Data static class LoginReq {
        @NotBlank private String username;
        @NotBlank private String password;
    }
}
