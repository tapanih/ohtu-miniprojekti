package logiikka;

import java.sql.SQLException;
import dao.DBlukuvinkkiDAO;
import dao.LukuvinkkiInterface;
import java.util.List;

public class LukuvinkkiService implements LukuvinkkiInterface {
    
    private DBlukuvinkkiDAO dbLukuvinkkiDAO;

    /**
     * the constructor for lukuvinkkiService
     *
     * @param dbDAO
     * @throws SQLException when connection to the database fails
     */
    public LukuvinkkiService(DBlukuvinkkiDAO dbDAO) throws SQLException {
        this.dbLukuvinkkiDAO = dbDAO;
    }

    @Override
    public boolean addBook(Kirja kirja) throws SQLException, Exception {
        try {
            dbLukuvinkkiDAO.addBook(kirja);
            return true;
        } catch (SQLException ex) {
            System.out.println("createExpense error message is..." + ex.getMessage());
            return false;
        }
    }

    @Override
    public Kirja findOne(String title) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Kirja> getAllBooks() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
