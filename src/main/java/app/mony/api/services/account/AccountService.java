package app.mony.api.services.account;

import app.mony.api.DTOs.account.request.AccountRequestDTO;
import app.mony.api.DTOs.account.response.AccountResponseDTO;
import app.mony.api.domains.account.Account;
import app.mony.api.domains.user.User;
import app.mony.api.repositories.account.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account create(AccountRequestDTO accountRequestDTO, User user) {
        Account account = new Account(
                user,
                accountRequestDTO.name(),
                accountRequestDTO.type()
        );

        return accountRepository.save(account);
    }

    public List<AccountResponseDTO> getAccountsByUser(User user) {
        return accountRepository.findByUser(user)
                .stream()
                .map(AccountResponseDTO::new)
                .toList();
    }
}
