CREATE TABLE lem_user_authority (
    id IDENTITY NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    authority VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES lem_user (id)
);