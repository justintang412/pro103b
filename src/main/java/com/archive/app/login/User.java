package com.archive.app.login;

import java.util.Set;

public class User {
    private String username;
    private String password;
    private String role;
    private String active;
    private String admin;
    private Set<String> rights;

    public User(String username, String password, String role, String active, String admin) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
        this.admin = admin;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRights() {
        return rights;
    }

    public void setRights(Set<String> rights) {
        this.rights = rights;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
