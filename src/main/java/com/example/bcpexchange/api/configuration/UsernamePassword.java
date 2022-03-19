package com.example.bcpexchange.api.configuration;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UsernamePassword {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public UsernamePassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
