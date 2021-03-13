package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.Article;
import logic.Book;
import logic.BookmarkType;

public class Database {

    private Connection db;

    public Database(String databaseName) throws SQLException {
        connect(databaseName);
        initializeDatabase();
    }

    private void connect(String databaseName) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + databaseName;
            db = DriverManager.getConnection(url);
        } catch (Exception e) {
        }
    }

    private void initializeDatabase() {
        try {
            initializeBookTable();
            initializeTagTable();
            initializeArticleTable();
        } catch (Throwable t) {
        }
    }

    private void initializeBookTable() {
        try {
            PreparedStatement createBookTable = db.prepareStatement("CREATE TABLE IF NOT EXISTS books ("
                    + "id INTEGER PRIMARY KEY,"
                    + "title  varchar(100), "
                    + "author  varchar(100), "
                    + "currentPage integer(100), "
                    + "pageCount  integer(100) "
                    + ");");
            createBookTable.execute();
            createBookTable.close();
        } catch (SQLException e) {
        }
    }
    
    private void initializeArticleTable() {
        try {
            PreparedStatement createArticleTable = db.prepareStatement("CREATE TABLE IF NOT EXISTS Articles ("
                    + "id INTEGER PRIMARY KEY,"
                    + "title  varchar(100), "
                    + "hyperlink  varchar(300)"
                    + ");");
            createArticleTable.execute();
            createArticleTable.close();
        } catch (SQLException e) {
        }
    }
    
    private void initializeTagTable() {
        try {
            PreparedStatement createTagTable = db.prepareStatement("CREATE TABLE IF NOT EXISTS Tags (id integer PRIMARY KEY, referenceId integer, keyword varchar(50), isBook boolean,   CONSTRAINT FK_1 FOREIGN KEY(referenceId)  \n"
                    + "         REFERENCES books(id), \n"
                    + "    CONSTRAINT FK_2 FOREIGN KEY (referenceId)  \n"
                    + "         REFERENCES Articles(id)"
                    + ");");
            createTagTable.execute();
            createTagTable.close();
        } catch (SQLException e) {

        }
    }

    /**
     * Adds a book to the database.
     *
     * @param book
     * @return true, if book added successfully, false otherwise.
     * @throws SQLException if adding book to the database fails.
     */
    public boolean addBook(Book book) throws SQLException {
        try {

            PreparedStatement statement = db.prepareStatement(
                    "INSERT OR REPLACE INTO books (title, author, currentPage, pageCount) VALUES (?, ?, ?, ?);"
            );
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPages());
            statement.setInt(4, book.getCurrentPage());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean addArticle(Article article) throws SQLException {
        try {
            PreparedStatement statement = db.prepareStatement(
                    "INSERT OR REPLACE INTO articles (title, hyperlink) VALUES (?, ?);"
            );
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getHyperlink());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addTag(String keyword, int id, BookmarkType type) throws SQLException {
        try {
            PreparedStatement statement = db.prepareStatement("INSERT OR REPLACE INTO tags (keyword, referenceId, isBook) VALUES (?, ?, ?);");
            statement.setString(1, keyword);
            statement.setInt(2, id);
            if (type == BookmarkType.BOOK) {
                statement.setBoolean(3, true);
            } else {
                statement.setBoolean(3, false);
            }
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Returns all books from the database.
     *
     * @return returns a list of Books.
     * @throws SQLException if retrieving data from the database fails.
     */
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        PreparedStatement statement = db.prepareStatement("SELECT * FROM books");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Book book = new Book(resultSet.getString("title").trim(), resultSet.getString("author").trim(), resultSet.getInt("pageCount"), resultSet.getInt("currentPage"));
            book.setId(resultSet.getInt("id"));
            books.add(book);
        }
        statement.close();
        return books;
    }

    public List<Article> getAllArticles() throws SQLException {
        List<Article> articles = new ArrayList<>();

        PreparedStatement statement = db.prepareStatement("SELECT * FROM articles");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Article article = new Article(resultSet.getString("title").trim(), resultSet.getString("hyperlink").trim());
            article.setId(resultSet.getInt("id"));
            articles.add(article);
        }
        statement.close();

        return articles;
    }

    public List<String> getAllTags() throws SQLException {
        List<String> tags = new ArrayList<>();

        PreparedStatement statement = db.prepareStatement("SELECT * FROM tags");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String tag = resultSet.getString("keyword");
            tags.add(tag);
        }
        statement.close();

        return tags;
    }
    
    public List<String> getTags(int id, BookmarkType type) {
        List<String> tags = new ArrayList<>();
        try {
            PreparedStatement statement = db.prepareStatement("SELECT * FROM tags WHERE referenceId = ? AND isBook = ?");
            statement.setInt(1, id);
            if (type == BookmarkType.BOOK) {
                statement.setBoolean(2, true);
            } else {
                statement.setBoolean(2, false);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tag = resultSet.getString("keyword");
                tags.add(tag);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tags;
    }
    
    public boolean deleteBook(Book book) throws SQLException {
        try {

            PreparedStatement statement = db.prepareStatement("DELETE FROM books WHERE id = ?");

            statement.setInt(1, book.getId());

            statement.executeUpdate();

            statement.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteArticle(Article article) throws SQLException {

        try {

            PreparedStatement statement = db.prepareStatement("DELETE FROM articles WHERE id = ?");

            statement.setInt(1, article.getId());

            statement.executeUpdate();

            statement.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteTag(String keyword, int id, BookmarkType type) throws SQLException {
        try {
            PreparedStatement statement = db.prepareStatement("DELETE FROM tags WHERE keyword = ? AND referenceId = ? AND isBook = ?");
            statement.setString(1, keyword);
            statement.setInt(2, id);
            if (type == BookmarkType.BOOK) {
                statement.setBoolean(3, true);
            } else {
                statement.setBoolean(3, false);
            }
            statement.executeUpdate();
            statement.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
