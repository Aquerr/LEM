package io.github.aquerr.lem.repository;

import io.github.aquerr.lem.domain.model.LemUser;

public interface UserRepository
{
    LemUser findUserById(Long id);

    LemUser findUserByUsername(String username);

    void updateUser(LemUser lemUser);

    void createUser(LemUser lemUser);

    void deleteUser(LemUser lemUser);
}
