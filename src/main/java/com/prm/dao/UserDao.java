package com.prm.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prm.model.User;
import com.prm.utils.DBUtils;

public class UserDao implements Serializable {
  private PreparedStatement statement;
  private Connection connection;
  private ResultSet resultSet;

  private void closeConnection() {
    try {
      if (resultSet != null) { resultSet.close(); }
      if (statement != null) { statement.close(); }
      if (connection != null) { connection.close(); }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean insert(User dTO) {
    boolean result = false;

    try {
      String sql = "INSERT INTO [user] VALUES(?,?,?,?)";

      connection = DBUtils.getConnection();
      statement = connection.prepareStatement(sql);

      statement.setString(1, dTO.getUsername());
      statement.setString(2, dTO.getPassword());
      statement.setString(3, dTO.getFullname());
      statement.setString(4, dTO.getRole());

      result = statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }

    return result;
  }

  public List<User> getAllAccounts() {
    List<User> result = new ArrayList<>();

    try {
      String sql = "SELECT username, fullname, [role] FROM [user]";
      String username, fullname, role = null;
      User user = null;

      connection = DBUtils.getConnection();
      statement = connection.prepareStatement(sql);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        username  = resultSet.getString("username");
        fullname  = resultSet.getString("fullname");
        role      = resultSet.getString("role");

        user = new User(username, null, fullname, role);
        user.setLeaf(true);

        result.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }

    return result;
  }

  public User checkLogin(String username, String password){
    User user = null;

    try {
      String sql = "SELECT username, [role] FROM [user] "
          + "WHERE username = ? AND [password] = ?";
      connection = DBUtils.getConnection();
      statement = connection.prepareStatement(sql);

      statement.setString(1, username);
      statement.setString(2, password);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        user = new User(
            resultSet.getString("username"),
            "",
            "",
            resultSet.getString("role")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }

    return user;
  }
}
