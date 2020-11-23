
package ohtu.junit;

import dao.BookmarkDao;
import dao.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Book;
import logic.BookmarkService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookmarkDaoTest {
    
    private BookmarkDao service;
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        Database db = new Database(":memory:");
        service = new BookmarkService(db);
    }
    
    @Test
    public void abbBookAddsBookToDatabase() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        service.addBook(book);
        List<Book> books = service.getAllBooks();
        assertTrue(books.contains(book));
    }
    
    @After
    public void tearDown() {
        
    }
}
