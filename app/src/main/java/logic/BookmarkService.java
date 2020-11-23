package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.DatabaseHelper;
import java.util.List;
import dao.BookmarkDao;

public class BookmarkService implements BookmarkDao {
    
    private DatabaseHelper helper;

    public BookmarkService(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
    }

    @Override
    public boolean addBook(Book book) {
        try {
            helper.addBook(book);
            return true;
        } catch (SQLException ex) {
            System.out.println("addBook error message is..." + ex.getMessage());
            return false;
        }
    }

    @Override
    public Book findOne(String title) {
        System.out.println("Työn alla!");
        return new Book("Koiramäen vipinäviikot", "Mauri Lunnas", 333);
    }

    @Override
    public List<Book> getAllBooks() {
        try {
            return helper.getAllBooks();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
