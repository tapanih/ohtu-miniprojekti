
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    
    private String dbName;
    
    public Database(String databaseName) {
        this.dbName = databaseName;
    }
    
    public Database() {
        this.dbName = "lukuvinkki.db";
    }

    public String getDbName() {
        return dbName;
    }

    /**
     * Connects to the database.
     * @return Connection to the database.
     * @throws java.sql.SQLException when connecting to the database is unsuccessful.
     */
    public Connection connect() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + dbName;
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    
    /**
     * Initializes the five database tables.
     * @return true if the database tables are created and false otherwise.
     */
    public boolean initializeDatabase() {
        try {
            initializeBookTable();
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            return false;
        }
        return true;
    }

    private void initializeBookTable() {
        try {
            Connection connection = connect();

            PreparedStatement createBookTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS books ("
                    + "id INTEGER PRIMARY KEY,"
                    + "title  varchar(100), "
                    + "author  varchar(100), "
                    + "pageCount  integer); "
            );
            createBookTable.execute();
            createBookTable.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}  