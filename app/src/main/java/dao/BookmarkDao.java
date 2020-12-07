package dao;

import java.sql.SQLException;
import java.util.List;
import logic.Article;
import logic.Book;
import logic.Bookmark;

public interface BookmarkDao {

    boolean addBook(Book book) throws SQLException, Exception;

    boolean addArticle(Article article) throws SQLException;
    
    boolean addTag(Bookmark bookmark, String keyword) throws SQLException;

    List<Book> getAllBooks() throws SQLException;
    
    List<Article> getAllArticles() throws SQLException;
    
    List<String> getTags(Bookmark bookmark);
    
    List<String> getTagsLowerCase(Bookmark bookmark);
    
    boolean deleteBook(Book book) throws SQLException;
    
    boolean deleteArticle(Article article) throws SQLException;
    
    boolean deleteBookmark(Bookmark bookmark) throws SQLException;
    
    boolean deleteTag(Bookmark bookmark, String keyword);
    
    List<Bookmark> getAllBookmarks() throws SQLException;

    public List<String> getAllTags();

}
