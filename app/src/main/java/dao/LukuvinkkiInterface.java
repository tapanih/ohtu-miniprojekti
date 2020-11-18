
package dao;

import java.sql.SQLException;
import java.util.List;
import logiikka.Kirja;

public interface LukuvinkkiInterface {

    /**
     * @param kirja
     * @return true, if successfully added book, false otherwise
     * @throws SQLException if saving the book object fails
     */
    boolean addBook(Kirja kirja) throws SQLException, Exception;

    /**
     * Retrieves all the books in the database
     * @param title
     * @return a book by title
     * @throws SQLException if retrieving data from the database fails
     *
     */
    Kirja findOne(String title) throws SQLException;

    /**
     * @return returns a list of categories created by the given user
     * @throws SQLException when retrieving data from the database fails
     */
    List<Kirja> getAllBooks() throws SQLException;
    
}
