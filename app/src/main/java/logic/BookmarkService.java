package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.DatabaseHelper;
import java.util.List;
import dao.BookmarkDao;

public class BookmarkService implements BookmarkDao {
    
    private DatabaseHelper helper;

    /**
     * the constructor for lukuvinkkiService
     *
     * @param helper
     * @throws SQLException when connection to the database fails
     */
    public BookmarkService(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
    }

    @Override
    public boolean addBook(Book book) throws SQLException {
        try {
            helper.addBook(book);
            return true;
        } catch (SQLException ex) {
            System.out.println("addBook error message is..." + ex.getMessage());
            return false;
        }
    }

    @Override
    public Book findOne(String title) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        try {
            return helper.getAllBooks();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
