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
import org.junit.Rule;
import org.junit.rules.ExpectedException;

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
    public void addBookReturnFalseIfAuthorIsMissing() throws Exception {
        Book testbook = new Book("TestBook", null, 333);
        assertFalse(service.addBook(testbook));
    }

    @Test
    public void addBookReturnFalseIfTitleIsMissing() throws Exception {
        Book testbook = new Book(null, "anonymous", 333);
        assertFalse(service.addBook(testbook));
    }

    @Test
    public void abbedBookIsPreservedInTheDatabase() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        service.addBook(book);
        List<Book> books = service.getAllBooks();
        assertTrue(books.contains(book));
    }
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void throwsExceptionWithSpecificType() throws SQLException {
        expectedException.expect(SQLException.class);

        throw new SQLException();
    }

    @Test
    public void getAllBooksReturnsAllBooks() throws Exception {
        Book book1 = new Book("Creative Title", "Awesome Author", 100, 333);
        Book book2 = new Book("Fantastic Title", "Such A Good Author", 100, 222);
        Book book3 = new Book("Funny Title", "Mediocre Author", 100, 111);
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
    public void deleteBookReturnsTrueIfBookDeleted() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        book.setId(1);
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
