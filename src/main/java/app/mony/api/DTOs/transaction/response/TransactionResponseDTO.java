package app.mony.api.DTOs.transaction.response;

import app.mony.api.enums.account.AccountType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionResponseDTO(
        UUID id,
        BigDecimal amount,
        String description,
        OffsetDateTime createdAt,
        String accountName,
        AccountType accountType
) {
}
