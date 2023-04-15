package io.github.aquerr.lem.repository;

import io.github.aquerr.lem.domain.model.LemUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Profile;

import java.util.Set;

@Mapper
@Profile({"local"})
public interface DBUserRepository extends UserRepository
{
    @Override
    @Select("SELECT lem_user.id, username, password, authority FROM lem_user LEFT JOIN lem_user_authority ON user_id = #{id} WHERE id = #{id}")
    LemUser findUserById(Long id);

    @Override
    @Results(id = "users", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "authorities", column = "id", javaType = Set.class, many = @Many(select = "getUserAuthorities"))
    })
    @Select("SELECT * FROM lem_user WHERE username = #{username}")
    LemUser findUserByUsername(String username);

    @Override
    @Update("UPDATE lem_user SET name = #{name} WHERE id = #{id}")
    void updateUser(LemUser lemUser);

    @Override
    @Insert("INSERT INTO lem_user (id, name) VALUES(#{id}, #{username})")
    void createUser(LemUser lemUser);

    @Override
    @Delete("DELETE FROM lem_user WHERE id = #{id}")
    void deleteUser(LemUser lemUser);

    @Select("SELECT authority FROM lem_user_authority WHERE user_id = #{id}")
    Set<String> getUserAuthorities(Long id);
}
