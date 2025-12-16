package app.mony.api.DTOs.account.request;

import app.mony.api.enums.account.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountRequestDTO(
        @NotBlank
        String name,

        @NotNull
        AccountType type
) { }
