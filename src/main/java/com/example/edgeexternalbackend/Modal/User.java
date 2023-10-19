package com.example.edgeexternalbackend.Modal;

import java.io.Serializable;

public class User implements Serializable {

    public User(String email, String userName, String password, boolean externalEdgeInd) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.externalEdgeInd = externalEdgeInd;
    }

    String email;
    String userName;

    String password;

    boolean externalEdgeInd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isExternalEdgeInd() {
        return externalEdgeInd;
    }

    public void setExternalEdgeInd(boolean externalEdgeInd) {
        this.externalEdgeInd = externalEdgeInd;
    }
}
