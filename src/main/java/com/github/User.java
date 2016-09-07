package com.github;

public class User {

    private String nickname;
    private Status status;
    private Role role;

    public User(String nickname, Status status, Role role) {
        this.nickname = nickname;
        this.status = status;
        this.role = role;
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", status=" + status +
                ", role=" + role +
                '}';
    }
}
