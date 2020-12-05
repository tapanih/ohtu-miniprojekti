package ohtu.junit;

import dao.BookmarkDao;
import dao.Database;
import java.sql.SQLException;
import java.util.List;
import logic.Book;
import logic.Article;
import logic.Bookmark;
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
    public void addArticleReturnTrueIfArticleWithTitleeIsAdded() throws Exception {
        Article article = new Article("Shrinking massive neural networks used to model language", "https://news.mit.edu/2020/neural-model-language-1201");
        boolean articleAdded = service.addArticle(article);
        assertTrue(articleAdded);
    }

    @Test
    public void addBookReturnFalseIfAuthorIsMissing() throws Exception {
        Book testbook = new Book("TestBook", null, 333);
        assertFalse(service.addBook(testbook));
    }

    @Test
    public void addArticleReturnFalseIfTitleIsMissing() throws Exception {
        Article newArticle = new Article(null, "https://news.mit.edu/2020/neural-model-language-1201");
        assertFalse(service.addArticle(newArticle));
    }

    @Test
    public void addBookReturnFalseIfTitleIsMissing() throws Exception {
        Book testbook = new Book(null, "anonymous", 333);
        assertFalse(service.addBook(testbook));
    }

    @Test
    public void addArticleReturnFalseIfHyperlinkIsMissing() throws Exception {
        Article newArticle = new Article("newArticle", null);
        assertFalse(service.addArticle(newArticle));
    }

    @Test
    public void abbedBookIsPreservedInTheDatabase() throws Exception {
        Book book = new Book("Creative Title", "Awesome Author", 333);
        service.addBook(book);
        List<Book> books = service.getAllBooks();
        assertTrue(books.contains(book));
    }

    @Test
    public void addedArticlekIsPreservedInTheDatabase() throws Exception {

        Article newArticle = new Article("Shrinking massive neural networks used to model language", "https://news.mit.edu/2020/neural-model-language-1201");
        service.addArticle(newArticle);
        List<Article> articles = service.getAllArticles();
        assertTrue(articles.contains(newArticle));
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
        Book book1 = new Book("Creative Title", "Awesome Author", 333, 100);
        Book book2 = new Book("Fantastic Title", "Such A Good Author", 222, 100);
        Book book3 = new Book("Funny Title", "Mediocre Author", 111, 100);
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
    public void getAllArticlesReturnsAllArticles() throws Exception {
        Article a1 = new Article("Article1", "www.a1.com");
        Article a2 = new Article("Article2", "www.a2.com");
        Article a3 = new Article("Article1", "www.a1.com");
        service.addArticle(a1);
        service.addArticle(a2);
        service.addArticle(a3);
        List<Article> articles = service.getAllArticles();
        assertEquals(articles.size(), 3);
        assertTrue(articles.contains(a1));
        assertTrue(articles.contains(a2));
        assertTrue(articles.contains(a3));
    }

    @Test
    public void deleteBookReturnsTrueIfBookDeleted() throws Exception {
        Article a1 = new Article("Article1", "www.a1.com");
        a1.setId(1);
        boolean successfulAdd = service.addArticle(a1);
        assertTrue(successfulAdd);
        service.deleteArticle(a1);
        List<Article> articles = service.getAllArticles();
        assertFalse(articles.contains(a1));
    }

    @Test
    public void deleteARticleReturnsTrueIfBookDeleted() throws Exception {
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
