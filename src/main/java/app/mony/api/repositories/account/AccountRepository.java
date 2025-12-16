package app.mony.api.repositories.account;

import app.mony.api.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import app.mony.api.domains.account.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findByUser(User user);
    Optional<Account> findByIdAndUser(UUID id, User user);
}
