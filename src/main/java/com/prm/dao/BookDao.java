package com.prm.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prm.model.Book;
import com.prm.utils.DBUtils;

public class BookDao implements Serializable {
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

    public boolean insert(Book book) {
        boolean result = false;

        try {
            String sql = "INSERT INTO book (title, author, publisher, available, quantity, genre, description) VALUES(?,?,?,?,?,?,?)";

            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setBoolean(4, book.isAvailable());
            statement.setInt(5, book.getQuantity());
            statement.setString(6, book.getGenre());
            statement.setString(7, book.getDescription());

            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return result;
    }

    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();

        try {
            String sql = "SELECT * FROM book";
            String title, author, publisher, genre, description = null;
            boolean isAvailable;
            int quantity;
            Book book = null;

            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                title       = resultSet.getString("title");
                author      = resultSet.getString("author");
                publisher   = resultSet.getString("publisher");
                genre       = resultSet.getString("genre");
                quantity    = resultSet.getInt("quantity");
                isAvailable = resultSet.getBoolean("available");
                description = resultSet.getString("description");

                book = new Book(title, author, publisher, isAvailable, quantity, genre, description);
                book.setLeaf(true);

                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return result;
    }

}
