package app.mony.api.DTOs.auth;

import app.mony.api.enums.user.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
