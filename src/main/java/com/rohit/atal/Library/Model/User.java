package com.rohit.atal.Library.Model;

import java.util.Objects;

public class User {
    private int Userid;
    private String Username;

    public User() {
        super();
    }

    public User(int userid, String username) {
        Userid = userid;
        Username = username;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Userid == user.Userid && Objects.equals(Username, user.Username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Userid, Username);
    }
}
