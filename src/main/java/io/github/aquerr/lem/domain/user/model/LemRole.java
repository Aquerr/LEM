package io.github.aquerr.lem.domain.user.model;

import java.util.Set;

public enum LemRole
{
    ROLE_OWNER(Set.of(
            LemAuthority.VIEW_LOGS,
            LemAuthority.VIEW_USERS,
            LemAuthority.VIEW_PROCESSES,
            LemAuthority.VIEW_LOGIN_PAGE,
            LemAuthority.EDIT_USERS,
            LemAuthority.KILL_PROCESSES
    )),
    ROLE_ADMIN(Set.of(
            LemAuthority.VIEW_LOGS,
            LemAuthority.VIEW_USERS,
            LemAuthority.VIEW_PROCESSES,
            LemAuthority.VIEW_LOGIN_PAGE
    )),
    ROLE_USER(Set.of(
            LemAuthority.VIEW_LOGIN_PAGE
    ));

    private final Set<LemAuthority> authorities;

    LemRole(Set<LemAuthority> authorities)
    {
        this.authorities = authorities;
    }

    public Set<LemAuthority> getAuthorities()
    {
        return Set.copyOf(this.authorities);
    }
}
