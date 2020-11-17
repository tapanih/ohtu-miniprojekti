package logiikka;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.DBlukuvinkkiDAO;
import dao.lukuvinkkiDatabase;

public class lukuvinkkiService {
    
    private lukuvinkkiDatabase db;
    private DBlukuvinkkiDAO dbLukuvinkkiDAO;

 

        /**
     * the constructor for lukuvinkkiService
     *
     * @param datab the database given as a parameter
     * @throws SQLException when connection to the database fails
     */
    public lukuvinkkiService(lukuvinkkiDatabase datab) throws SQLException {
        this.db = datab;
        this.db.initializeDatabase();
        dbLukuvinkkiDAO = new DBlukuvinkkiDAO(db);
    }

    public boolean addBook( String title, String author, int pageCount ) throws SQLException {

        Kirja kirja = new Kirja(title, author, pageCount);
     
        try {
          
                DBlukuvinkkiDAO.addBook(kirja);
                return true;

        } catch (SQLException ex) {
            System.out.println("createExpense error message is..." + ex.getMessage());
            return false;
        }
    }

}
