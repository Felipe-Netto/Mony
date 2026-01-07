package app.mony.api.services.transaction;

import app.mony.api.DTOs.transaction.request.TransactionRequestDTO;
import app.mony.api.DTOs.transaction.response.TransactionResponseDTO;
import app.mony.api.domains.account.Account;
import app.mony.api.domains.transaction.Transaction;
import app.mony.api.domains.user.User;
import app.mony.api.infra.exceptions.EntityNotFoundException;
import app.mony.api.repositories.account.AccountRepository;
import app.mony.api.repositories.transaction.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO, User user) {
        Account account = accountRepository.findByIdAndUser(transactionRequestDTO.accountId(), user)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        transactionRequestDTO.type().apply(account, transactionRequestDTO.amount());

        Transaction transaction = new Transaction(
                account,
                transactionRequestDTO.amount(),
                transactionRequestDTO.type(),
                transactionRequestDTO.description()
        );

        transactionRepository.save(transaction);
        accountRepository.save(account);

        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getCreatedAt(),
                account.getName(),
                account.getType()
        );
    }

    @Transactional(readOnly = true)
    public List<TransactionResponseDTO> getTransactions(User user) {
        return transactionRepository.findByAccountUser(user).stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getDescription(),
                        transaction.getCreatedAt(),
                        transaction.getAccount().getName(),
                        transaction.getAccount().getType()
                ))
                .toList();
    }
}
