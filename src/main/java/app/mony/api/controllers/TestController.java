package app.mony.api.controllers;

import app.mony.api.domains.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseEntity<String> test(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok("Olá " + user.getName() + ", seu email é " + user.getEmail());
    }
}
