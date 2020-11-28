package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.Book;

public class Database {

    private Connection db;

    public Database(String databaseName) throws SQLException {
        connect(databaseName);
        initializeDatabase();
    }

    private void connect(String databaseName) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + databaseName;
            db = DriverManager.getConnection(url);
        } catch (Exception e) {
        }
    }
    

    private void initializeDatabase() {
        try {
            initializeBookTable();
        } catch (Throwable t) {
        }
    }

    private void initializeBookTable() {
        try {
            PreparedStatement createBookTable = db.prepareStatement("CREATE TABLE IF NOT EXISTS books ("
                    + "id INTEGER PRIMARY KEY,"
                    + "title  varchar(100), "
                    + "author  varchar(100), "
                    + "pageCount  integer); "
            );
            createBookTable.execute();
            createBookTable.close();
        } catch (SQLException e) {
        }
    }

    /**
     * Adds a book to the database.
     *
     * @param book
     * @return true, if book added successfully, false otherwise.
     * @throws SQLException if adding book to the database fails.
     */
    public boolean addBook(Book book) throws SQLException {
        try {
            PreparedStatement statement = db.prepareStatement(
                    "INSERT OR REPLACE INTO books (title, author, pageCount) VALUES (?, ?, ?);"
            );
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPages());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns all books from the database.
     *
     * @return returns a list of Books.
     * @throws SQLException if retrieving data from the database fails.
     */
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        PreparedStatement statement = db.prepareStatement("SELECT * FROM books");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Book book = new Book(resultSet.getString("title").trim(), resultSet.getString("author"), resultSet.getInt("pageCount"));
            book.setId(resultSet.getInt("id"));
            books.add(book);
        }
        statement.close();

        return books;
    }

    /**
     * Retrieves a book with the given title from the database.
     *
     * @param title
     * @return a Book with the given title.
     * @throws SQLException if retrieving data from the database fails.
     */
//    public Book getBookByTitle(String title) throws SQLException {
//        PreparedStatement statement = db.prepareStatement("SELECT * FROM books WHERE title = ?");
//        statement.setString(1, title);
//        ResultSet resultSet = statement.executeQuery();
//        boolean findOne = resultSet.next();
//        if (!findOne) {
//            return null;
//        } else {
//            Book book = new Book(resultSet.getString("title"), resultSet.getString("author"), resultSet.getInt("pageCount"));
//            statement.close();
//            resultSet.close();
//            return book;
//        }
//    }

    /**
     * Tells if a book with the given title already exists in the database.
     *
     * @param title
     * @return true if title is already taken and false otherwise.
     * @throws SQLException if retrieving data from the database fails.
     */

    public boolean deleteBook(Book book) throws SQLException {
      
        try {

            PreparedStatement statement = db.prepareStatement("DELETE FROM books WHERE id = ?");

            statement.setInt(1, book.getId());

            statement.executeUpdate();

            statement.close();
            
        } catch (Exception e) {
            return false;
        }

        return true;

    }
}
