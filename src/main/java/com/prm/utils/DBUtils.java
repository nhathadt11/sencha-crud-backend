package com.prm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBUtils {
    private static final String DB_CONFIG_PATH = "/settings.properties";
    private static final Properties PROPERTIES = new Properties();
    private static String url;
    private static String username;
    private static String password;

    static {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        PROPERTIES.load(new FileInputStream(
            DBUtils.class.getResource(DB_CONFIG_PATH).getFile()
        ));
        url       = PROPERTIES.getProperty("db.jdbc.url");
        username  = PROPERTIES.getProperty("db.jdbc.username");
        password  = PROPERTIES.getProperty("db.jdbc.password");
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

    public static Connection getConnection() {
      Connection connection = null;
      try {
        connection = DriverManager.getConnection(url, username, password);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return connection;
    }


}
