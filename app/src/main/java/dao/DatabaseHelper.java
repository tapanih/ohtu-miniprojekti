package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.Book;

public class DatabaseHelper {
    
    private Database db;
    private List<Book> books = new ArrayList<>();
    private String sql1 = "SELECT * FROM kirja where user_username = ?";
    private String sql2 = "SELECT * FROM kirja where author = ?";
    private String sql3 = "SELECT * FROM kirja where title = ?";
    private Connection c = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;
    
    public DatabaseHelper(Database db) {
        this.db = db;
    }

    /**
     * Adds a book to the database.
     * @param book
     * @throws SQLException if adding book to the database fails.
     */
    public boolean addBook(Book book) throws SQLException {
        c = db.connect();
        try {
            PreparedStatement statement = c.prepareStatement(
                    "INSERT OR REPLACE INTO books (title, author, pageCount) VALUES (?, ?, ?);"
            );

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPages());
            statement.executeUpdate();
            statement.close();
            rs.close();
            c.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * Returns all books from the database.
     * @return returns a list of Books.
     * @throws SQLException if retrieving data from the database fails.
     */
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        c = db.connect();
        try {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM books");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Book kirja = new Book(rs.getString("title").trim(), rs.getString("author"), rs.getInt("pageCount"));
                books.add(kirja);
            }
            stmt.close();
        } catch (Throwable t) {
        } finally {
            c.close();
        }
        return books;
    }

    /**
     * Retrieves a book with the given title from the database.
     * @param title
     * @return a Book with the given title.
     * @throws SQLException if retrieving data from the database fails.
     */
    public Book findOne(String title) throws SQLException {
        c = db.connect();
        PreparedStatement stmt = c.prepareStatement("SELECT*FROM books WHERE title = ?");
        stmt.setString(1, title);
        rs = stmt.executeQuery();
        boolean findOne = rs.next();
        if (!findOne) {
            return null;
        } else {
            Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getInt("pageCount"));
            stmt.close();
            rs.close();
            c.close();
            return book;
        }
    }
}