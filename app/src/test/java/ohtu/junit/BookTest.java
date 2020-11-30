package ohtu.junit;

import java.util.ArrayList;
import logic.Book;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    private Book book;

    @Test
    public void constructorInitializesBookWithCorrectNameAuthorAndPages() {
        String title = "Title";
        String author = "Author";
        int pages = 166;
        book = new Book(title, author, pages);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(pages, book.getPages());
    }

    @Test
    public void constructorWithCurrentPageInitializesBookWithCorrectNameAuthorAndPages() {
        String title = "Title";
        String author = "Author";
        int pages = 166;
        int currentPage = 58;
        book = new Book(title, author, currentPage, pages);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(currentPage, book.getCurrentPage());
        assertEquals(pages, book.getPages());
    }

    @Test
    public void constructorWithCurrentPageAndTagListInitializesBookWithCorrectNameAuthorAndPages() {
        String title = "Title";
        String author = "Author";
        int pages = 166;
        int currentPage = 58;
        ArrayList<String> tags = new ArrayList<>();
        tags.add("funny");

        book = new Book(title, author, currentPage, pages, tags);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(currentPage, book.getCurrentPage());
        assertEquals(pages, book.getPages());
        assertEquals(tags, book.getTags());
    }
}
