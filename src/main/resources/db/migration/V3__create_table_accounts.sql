CREATE TABLE accounts
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    user_id    UUID                     NOT NULL,
    name       VARCHAR(100)             NOT NULL,
    type       VARCHAR(30)              NOT NULL,
    balance    NUMERIC(15, 2)           NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_accounts_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
)