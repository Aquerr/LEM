package io.github.aquerr.lem.repository;

import io.github.aquerr.lem.domain.user.model.LemUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void prepareData()
    {
        LemUser lemUser = new LemUser(1L, "test", "password", Set.of("test1", "test2"));
        userRepository.save(lemUser);
        userRepository.flush();
    }

    @Test
    void testUserRepositoryFindsUserById()
    {
        LemUser lemUser = new LemUser(null, "yolo", "mypass", Set.of("test1", "test2"));
        LemUser saved = userRepository.save(lemUser);
        userRepository.flush();

        LemUser foundUser = userRepository.findById(saved.id()).orElse(null);
        assertThat(foundUser).isNotNull();
    }

    @Test
    void testUserRepositoryFindsUserByUsername()
    {
        LemUser lemUser = userRepository.findByUsername("test");
        assertThat(lemUser).isNotNull();
        assertThat(lemUser.username()).isEqualTo("test");
    }

    @Test
    void testUserRepositoryFindsUserWithAuthorities()
    {
        LemUser lemUser = userRepository.findByUsername("test");
        assertThat(lemUser).isNotNull();
        assertThat(lemUser.authorities()).isEqualTo(Set.of("test1", "test2"));
    }
}