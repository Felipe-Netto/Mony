package app.mony.api.controllers.auth;

import app.mony.api.DTOs.auth.request.LoginRequestDTO;
import app.mony.api.DTOs.auth.request.RegisterRequestDTO;
import app.mony.api.DTOs.auth.response.LoginResponseDTO;
import app.mony.api.DTOs.auth.response.UserResponseDTO;
import app.mony.api.domains.user.User;
import app.mony.api.repositories.user.UserRepository;
import app.mony.api.services.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        if (this.userRepository.findByEmail(registerRequestDTO.email()).isPresent()) return ResponseEntity.badRequest().build();

        String encriptedPassword = new BCryptPasswordEncoder().encode(registerRequestDTO.password());
        User user = new User(registerRequestDTO.name(), registerRequestDTO.email(), encriptedPassword, registerRequestDTO.role());

        this.userRepository.save(user);

        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(@AuthenticationPrincipal User user) {
        if (user == null) {
            throw new IllegalStateException("Unauthorized");
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());

        return ResponseEntity.ok(userResponseDTO);
    }
}
