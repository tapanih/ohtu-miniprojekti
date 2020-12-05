package dao;

import java.sql.SQLException;
import java.util.List;
import logic.Article;
import logic.Book;
import logic.Bookmark;

public interface BookmarkDao {

    /**
     * @param kirja
     * @return true, if successfully added book, false otherwise
     * @throws SQLException if saving the book object fails
     */
    boolean addBook(Book book) throws SQLException, Exception;

    boolean addArticle(Article article) throws SQLException;

    boolean addTag(String keyword) throws SQLException;

    /**
     * Retrieves all the books in the database
     *
     * @param title
     * @return a book by title
     * @throws SQLException if retrieving data from the database fails
     *
     */
//    Book getBookByTitle(String title) throws SQLException;
    /**
     * @return returns a list of categories created by the given user
     * @throws SQLException when retrieving data from the database fails
     */
    List<Book> getAllBooks() throws SQLException;

    boolean deleteBook(Book b) throws SQLException;

    boolean deleteArticle(Article article) throws SQLException;

    boolean deleteTag(int id) throws SQLException;

    List<Bookmark> getAllBookmarks() throws SQLException;

}
