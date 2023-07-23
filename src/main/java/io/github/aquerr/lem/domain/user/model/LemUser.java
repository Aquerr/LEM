package io.github.aquerr.lem.domain.user.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lem_user")
public final class LemUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lem_user_generator")
    @SequenceGenerator(name = "lem_user_generator", sequenceName = "lem_user_seq", allocationSize = 5)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable(name = "lem_user_authority", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "authority")
    private Set<String> authorities;

    public LemUser()
    {

    }

    public LemUser(Long id, String username, String password, Set<String> authorities)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public Long id()
    {
        return id;
    }

    public String username()
    {
        return username;
    }

    public String password()
    {
        return password;
    }

    public Set<String> authorities()
    {
        return authorities;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LemUser) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.authorities, that.authorities);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, username, password, authorities);
    }

    @Override
    public String toString()
    {
        return "LemUser[" +
                "id=" + id + ", " +
                "username=" + username + ", " +
                "password=" + password + ", " +
                "authorities=" + authorities + ']';
    }


}
