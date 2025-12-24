CREATE TABLE users_roles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    UNIQUE(user_id, role_id)
);
