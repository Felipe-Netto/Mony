package app.mony.api.repositories.transaction;

import app.mony.api.domains.account.Account;
import app.mony.api.domains.transaction.Transaction;
import app.mony.api.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByAccount(Account account);
    List<Transaction> findByAccountUser(User user);
    Optional<Transaction> findByIdAndAccount(UUID id, Account account);
}
