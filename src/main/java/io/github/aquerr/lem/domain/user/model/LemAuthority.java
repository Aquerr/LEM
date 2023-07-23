package io.github.aquerr.lem.domain.user.model;

public enum LemAuthority
{
    VIEW_LOGS("VIEW_LOGS"),
    VIEW_PROCESSES("VIEW_PROCESSES"),
    VIEW_USERS("VIEW_USERS"),
    EDIT_USERS("EDIT_USERS"),
    KILL_PROCESSES("KILL_PROCESSES"),
    VIEW_LOGIN_PAGE("VIEW_LOGIN_PAGE"),
    VIEW_SETTINGS("VIEW_SETTINGS");

    private final String authority;

    LemAuthority(String authority)
    {
        this.authority = authority;
    }

    public String getAuthority()
    {
        return authority;
    }
}
