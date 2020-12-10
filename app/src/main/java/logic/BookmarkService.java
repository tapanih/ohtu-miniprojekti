package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.Database;
import java.util.List;
import dao.BookmarkDao;

public class BookmarkService implements BookmarkDao {

    private Database db;

    public BookmarkService(Database db) throws SQLException {
        this.db = db;
    }

    @Override
    public boolean addBook(Book book) {
        if (book.getAuthor() == null || book.getAuthor().equals("") || book.getTitle() == null || book.getTitle().equals("")) {
            return false;
        }
        try {
            db.addBook(book);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean addArticle(Article article) throws SQLException {
        if (article.getTitle() == null || article.getTitle().equals("") || article.getHyperlink() == null || article.getHyperlink().equals("")) {
            return false;
        }
        try {
            db.addArticle(article);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    @Override
    public List<Article> getAllArticles() throws SQLException {
        try {
            return db.getAllArticles();
        } catch (SQLException ex) {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Book> getAllBooks() {
        try {
            return db.getAllBooks();
        } catch (SQLException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Bookmark> getAllBookmarks() throws SQLException {
        ArrayList<Bookmark> bookmarkList = new ArrayList<>();
        try {
            bookmarkList.addAll(db.getAllBooks());
            bookmarkList.addAll(db.getAllArticles());
            return bookmarkList;
        } catch (SQLException ex) {
            return new ArrayList<>();
        }
    }
    
    @Override
    public boolean deleteBookmark(Bookmark bookmark) throws SQLException {
        deleteTags(bookmark);
        if (bookmark.getClass().getName().equals(Book.class.getName())) {
            
            return deleteBook((Book) bookmark);
        } else {
            return deleteArticle((Article) bookmark);
        }
    }
    
    public void deleteTags(Bookmark bookmark) throws SQLException {
        for (String tag : bookmark.getTags()) {
            deleteTag(bookmark, tag);
        }
    }

    @Override
    public boolean deleteBook(Book book) throws SQLException {
        try {
            return db.deleteBook(book);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteArticle(Article article) throws SQLException {
        try {
            return db.deleteArticle(article);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addTag(Bookmark bookmark, String keyword) throws SQLException {
        int id = bookmark.getId();
        BookmarkType type = bookmark.getType();
        if (keyword == null || keyword.equals("")) {
            return false;
        }
        try {
            db.addTag(keyword, id, type);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    @Override
    public List<String> getTags(Bookmark bookmark) {
        int id = bookmark.getId();
        BookmarkType type = bookmark.getType();
        return db.getTags(id, type);
    }

    @Override
    public boolean deleteTag(Bookmark bookmark, String keyword) {
        int id = bookmark.getId();
        BookmarkType type = bookmark.getType();
        try {
            return db.deleteTag(keyword, id, type);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> getAllTags() {
        try {
            return db.getAllTags();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getTagsLowerCase(Bookmark bookmark) {
        int id = bookmark.getId();
        BookmarkType type = bookmark.getType();
        List<String> tags = db.getTags(id, type);
        List<String> result = new ArrayList<>();
        for (String tag : tags) {
            result.add(tag.toLowerCase());
        }
        return result;
    }
}
