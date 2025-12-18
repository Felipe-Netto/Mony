package app.mony.api.DTOs.transaction.request;

import app.mony.api.enums.transaction.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequestDTO(
        @NotNull
        UUID accountId,

        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        TransactionType type,

        String description
) {
}
