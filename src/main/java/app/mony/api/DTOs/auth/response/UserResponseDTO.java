package app.mony.api.DTOs.auth.response;

import app.mony.api.enums.user.UserRole;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, UserRole role) {
}
