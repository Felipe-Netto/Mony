package app.mony.api.controllers.auth;

import app.mony.api.DTOs.auth.RegisterDTO;
import app.mony.api.domains.user.User;
import app.mony.api.repositories.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (this.userRepository.findByEmail(registerDTO.email()).isPresent()) return ResponseEntity.badRequest().build();

        String encriptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User user = new User(registerDTO.name(), registerDTO.email(), encriptedPassword, registerDTO.role());

        this.userRepository.save(user);

        return ResponseEntity.ok("User created successfully");
    }
}
