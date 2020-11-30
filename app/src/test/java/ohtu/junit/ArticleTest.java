package ohtu.junit;

import java.util.ArrayList;
import logic.Article;
import logic.Book;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleTest {

    private Article article;

    @Test
    public void constructorWorksCorrectly() {
        String title = "Title";
        String link = "https://news.mit.edu/2020/translating-lost-languages-using-machine-learning-1021";
        ArrayList<String> tags = new ArrayList<>();
        tags.add("useful");

        article = new Article(title, link, tags);
        assertEquals(title, article.getTitle());
        assertEquals(link, article.getHyperlink());
        assertEquals(tags, article.getTags());
    }
}
