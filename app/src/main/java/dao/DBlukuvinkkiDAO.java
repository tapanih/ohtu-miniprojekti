package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logiikka.Kirja;

public class DBlukuvinkkiDAO {
    private int id;
    private String description;
    private lukuvinkkiDatabase db;
    private String database;
    private List<Kirja> books= new ArrayList<>();
    private String sql1 = "SELECT*FROM kirja where user_username = ?";
    private String sql2 = "SELECT*FROM kirja where author = ?";
    private String sql3 = "SELECT*FROM kirja where title = ?";
    private Connection c = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;
        /**
     * DBlukuvinkkiDAO class constructor. The database is initialized in the
     * constructor.
     *
     * @param db database given as a parameter
     */
    public DBlukuvinkkiDAO(lukuvinkkiDatabase db){
        this.db = db;
        db.initializeDatabase();
        Statement stmt = null;
        // books = new ArrayList<>();
        try {
            c = db.connect();
            db = new lukuvinkkiDatabase(database);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("Select*FROM books");
            while (rs.next()) {
                Kirja kirja= new Kirja();
                // kirja.setUserName(rs.getString("user_username"));
                kirja.setOtsikko(rs.getString("title"));
                kirja.setKirjailija(rs.getString("author"));
                books.add(kirja);
            }
            c.close();
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Finds the most recent balance entry created by the user to a list
     *
     * @param username user's username
     * @return the logged in user's book by author
     * @throws SQLException if retrieving data from the database fails
     *
     */
    public Kirja findOne(String username, String author) throws SQLException {
        c = db.connect();
        PreparedStatement stmt = c.prepareStatement("SELECT*FROM books WHERE author = ? AND user_username = ?");
        stmt.setString(1, author);
        rs = stmt.executeQuery();
        boolean findOne = rs.next();
        if (!findOne) {
            return null;
        } else {
          
            Kirja b = new Kirja(rs.getString("user_username"), rs.getString("author"));
            stmt.close();
            rs.close();
            c.close();
            return b;
        }
    }
    }


