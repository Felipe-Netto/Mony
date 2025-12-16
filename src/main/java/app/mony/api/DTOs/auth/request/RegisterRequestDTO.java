package app.mony.api.DTOs.auth.request;

import app.mony.api.enums.user.UserRole;

public record RegisterRequestDTO(String name, String email, String password, UserRole role) {
}
