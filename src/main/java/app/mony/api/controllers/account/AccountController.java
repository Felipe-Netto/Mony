package app.mony.api.controllers.account;

import app.mony.api.DTOs.account.request.AccountRequestDTO;
import app.mony.api.DTOs.account.response.AccountResponseDTO;
import app.mony.api.domains.account.Account;
import app.mony.api.domains.user.User;
import app.mony.api.services.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(
            @RequestBody @Valid AccountRequestDTO accountRequestDTO,
            @AuthenticationPrincipal User user
    ) {
        Account account = accountService.create(accountRequestDTO, user);
        return ResponseEntity.ok(new AccountResponseDTO(account));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAccounts(
            @AuthenticationPrincipal User user
    ) {
        List<AccountResponseDTO> accounts = accountService.getAccountsByUser(user);

        return ResponseEntity.ok(accounts);
    }
}
