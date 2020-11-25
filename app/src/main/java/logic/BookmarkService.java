package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.Database;
import java.util.List;
import dao.BookmarkDao;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookmarkService implements BookmarkDao {
    
    private Database db;

    public BookmarkService(Database db) throws SQLException {
        this.db = db;
    }

    @Override
    public boolean addBook(Book book) {
        try {
            db.addBook(book);
            return true;
        } catch (SQLException ex) {
            System.out.println("addBook error message is..." + ex.getMessage());
            return false;
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        try {
            return db.getBookByTitle(title);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        try {
            return db.getAllBooks();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
    
    @Override
    public boolean deleteBook(Book b) throws SQLException {
        try {
            return db.deleteBook(b);
        } catch (Exception e) {
            System.out.println("BookmarkService delteBook method error: " + e.getMessage());
            return false;
        }
    }
}
