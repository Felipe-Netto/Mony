package app.mony.api.controllers.transaction;

import app.mony.api.DTOs.transaction.request.TransactionRequestDTO;
import app.mony.api.DTOs.transaction.response.TransactionResponseDTO;
import app.mony.api.domains.user.User;
import app.mony.api.services.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(
            @RequestBody @Valid TransactionRequestDTO transactionRequestDTO,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.createTransaction(transactionRequestDTO, user));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(
                transactionService.getTransactions(user)
        );
    }
}
