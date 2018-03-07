package com.prm.model;

import java.io.Serializable;

public class User implements Serializable {
  private String username;
  private String password;
  private String fullname;
  private String role;
  private boolean leaf;

  public User() {
  }

  public User(String username, String password, String fullname, String role) {
    this.username = username;
    this.password = password;
    this.fullname = fullname;
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean isLeaf() {
    return leaf;
  }

  public void setLeaf(boolean leaf) {
    this.leaf = leaf;
  }
}