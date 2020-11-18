package logiikka;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.DatabaseHelper;
import java.util.List;
import dao.LukuvinkkiDAO;

public class LukuvinkkiService implements LukuvinkkiDAO {
    
    private DatabaseHelper helper;

    /**
     * the constructor for lukuvinkkiService
     *
     * @param helper
     * @throws SQLException when connection to the database fails
     */
    public LukuvinkkiService(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
    }

    @Override
    public boolean addBook(Kirja kirja) throws SQLException {
        try {
            helper.addBook(kirja);
            return true;
        } catch (SQLException ex) {
            System.out.println("addBook error message is..." + ex.getMessage());
            return false;
        }
    }

    @Override
    public Kirja findOne(String title) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Kirja> getAllBooks() throws SQLException {
        try {
            return helper.getAllBooks();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
