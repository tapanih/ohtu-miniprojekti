
package ohtu.junit;

import logic.Book;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    
    private Book book;
    
    @Test public void constructorInitializesBookWithCorrectNameAuthorAndPages() {
        String title = "Title";
        String author = "Author";
        int pages = 166;
        book = new Book(title, author, pages);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(pages, book.getPages());
    }
}
