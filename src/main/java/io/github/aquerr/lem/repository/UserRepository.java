package io.github.aquerr.lem.repository;

import io.github.aquerr.lem.domain.user.model.LemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<LemUser, Long>
{
    LemUser findByUsername(String username);

//    @Query("SELECT authority FROM lem_user " +
//            "JOIN lem_user_authority ON lem_user.id = lem_user_authority.user_id " +
//            "WHERE lem_user.username = :username")
//    List<String> findLemUserAuthoritiesByUsername(@Param("username") String username);
}
