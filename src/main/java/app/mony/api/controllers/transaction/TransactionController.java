package app.mony.api.controllers.transaction;

import app.mony.api.DTOs.transaction.request.TransactionRequestDTO;
import app.mony.api.DTOs.transaction.response.TransactionResponseDTO;
import app.mony.api.domains.transaction.Transaction;
import app.mony.api.domains.user.User;
import app.mony.api.services.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionResponseDTO create(
            @RequestBody @Valid TransactionRequestDTO transactionRequestDTO,
            @AuthenticationPrincipal User user
    ) {
        return transactionService.createTransaction(transactionRequestDTO, user);
    }

}
