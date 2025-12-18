package app.mony.api.enums.transaction;

import app.mony.api.domains.account.Account;

import java.math.BigDecimal;

public enum TransactionType {
    INCOME {
        @Override
        public void apply(Account account, BigDecimal amount) {
            account.addBalance(amount);
        }
    },
    EXPENSE {
        @Override
        public void apply(Account account, BigDecimal amount) {
            account.subtractBalance(amount);
        }
    };

    public abstract void apply(Account account, BigDecimal amount);
}
