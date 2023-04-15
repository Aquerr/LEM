package io.github.aquerr.lem.domain.model;

import java.util.Objects;
import java.util.Set;

public final class LemUser
{
    private Long id;
    private String username;
    private String password;
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
