package app.mony.api.DTOs.account.response;

import app.mony.api.domains.account.Account;
import app.mony.api.enums.account.AccountType;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponseDTO(UUID id, String name, AccountType accountType, BigDecimal balance) {
    public AccountResponseDTO(Account account) {
        this(
                account.getId(),
                account.getName(),
                account.getType(),
                account.getBalance()
        );
    }
}
