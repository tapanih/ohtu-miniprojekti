
package ohtu.junit;

import dao.Database;
import dao.DatabaseHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseHelperTest {
    
    private DatabaseHelper helper;
    private Connection connection;
    
    public DatabaseHelperTest() {
//        Database db = new Database(":memory:");
//        Connection c = db.connect();
//        System.out.println(db.getDbName());
//        System.out.println(db.initializeDatabase());
//        helper = new DatabaseHelper(db);
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
    }
    
    @Test
    public void addBookAddsBookToDatabase() throws SQLException {
        Statement s = connection.createStatement();
        PreparedStatement createBookTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS books ("
                    + "id INTEGER PRIMARY KEY,"
                    + "title  varchar(100), "
                    + "author  varchar(100), "
                    + "pageCount  integer); "
        );
        createBookTable.execute();
        createBookTable.close();
        Book book = new Book("Title", "Author", 300);
        
        PreparedStatement statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO books (title, author, pageCount) VALUES (?, ?, ?);"
        );
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setInt(3, book.getPages());
        statement.executeUpdate();
        statement.close();
        
        PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM books WHERE title = ?");
        statement2.setString(1, book.getTitle());
        ResultSet resultSet = statement2.executeQuery();
        boolean findOne = resultSet.next();
        if (findOne) {
            Book book2 = new Book(resultSet.getString("title"), resultSet.getString("author"), resultSet.getInt("pageCount"));
            statement2.close();
            resultSet.close();
            assertEquals(book, book2);
        }
    }
    
    @After
    public void tearDown() {
        
    }
}
