CREATE TABLE transactions
(
    id          UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    account_id  UUID                     NOT NULL,
    amount      NUMERIC(15, 2)           NOT NULL,
    type        VARCHAR(30)              NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transactions_account
        FOREIGN KEY (account_id)
            REFERENCES accounts (id)
            ON DELETE CASCADE
)