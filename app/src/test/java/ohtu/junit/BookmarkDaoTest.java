
package ohtu.junit;

import dao.BookmarkDao;
import dao.Database;
import java.sql.SQLException;
import java.util.List;
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
    public void abbBookReturnTrueIfBookWithUniqueNameIsAdded() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        boolean bookAdded = service.addBook(book);
        assertTrue(bookAdded);
    }
    
    @Test
    public void abbedBookIsPreservedInTheDatabase() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        service.addBook(book);
        List<Book> books = service.getAllBooks();
        assertTrue(books.contains(book));
    }
    
    @Test
    public void getAllBooksReturnsAllBooks() throws Exception {
        Book book1 = new Book("Creative Title", "Awesome Author", 333);
        Book book2 = new Book("Fantastic Title", "Such A Good Author", 222);
        Book book3 = new Book("Funny Title", "Mediocre Author", 111);
        service.addBook(book1);
        service.addBook(book2);
        service.addBook(book3);
        List<Book> books = service.getAllBooks();
        assertEquals(books.size(), 3);
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
        assertTrue(books.contains(book3));
    }
    
    @Test
    public void abbBookReturnsFalseIfNameAlreadyTaken() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        boolean successfulAdd = service.addBook(book);
        assertTrue(successfulAdd);
        Book book2 = new Book("Creative Title", "Author Who Copied The Name Of The Previous Book", 333);
        boolean unsuccessfulAdd = service.addBook(book2);
        assertFalse(unsuccessfulAdd);
    }
    
    @Test
    public void abbBookDoesNotAddBookToDatabasefNameAlreadyTaken() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        service.addBook(book);
        Book book2 = new Book("Creative Title", "Author Who Copied The Name Of The Previous Book", 333);
        service.addBook(book2);
        List<Book> books = service.getAllBooks();
        assertFalse(books.contains(book2));
    }
    @Test 
    public void deleteBookReturnsTrueIfBookDeleted() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        boolean successfulAdd = service.addBook(book);
        assertTrue(successfulAdd);
        service.deleteBook(book);
        List<Book> books = service.getAllBooks();
        assertFalse(books.contains(book));
    }
    
    @After
    public void tearDown() {
        
    }
}
