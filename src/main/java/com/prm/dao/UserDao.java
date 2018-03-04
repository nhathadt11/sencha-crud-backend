/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prm.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prm.model.UserDTO;
import com.prm.utils.DBUtils;


/**
 *
 * @author TINH HUYNH
 */
public class UserDao implements Serializable {

    private PreparedStatement statement;
    private Connection connection;
    private ResultSet resultSet;

    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(UserDTO dTO) {
        boolean result = false;
        try {
            String sql = "INSERT INTO [user] VALUES(?,?,?,?)";
            //TODO: get connection from DBUtils
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

    public List<UserDTO> getAllAccounts() {
        List<UserDTO> result = null;
        try {
            String sql = "SELECT username, fullname, [role] FROM [user]";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            String username, fullname, role = null;
            result = new ArrayList<>();
            while (resultSet.next()) {
                username = resultSet.getString("username");
                fullname = resultSet.getString("fullname");
                role = resultSet.getString("role");
                UserDTO dTO = new UserDTO(username, null, fullname, role);
                dTO.setLeaf(true);
                result.add(dTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public UserDTO checkLogin(String username, String password){
        UserDTO dTO = null;
        try {
            String sql = "SELECT username, [role] FROM [user] "
                    + "WHERE username = ? AND [password] = ?";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dTO = new UserDTO(resultSet.getString("username"),
                        "",
                        "",
                        resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dTO;
    }
}
