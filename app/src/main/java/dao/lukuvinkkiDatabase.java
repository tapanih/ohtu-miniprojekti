
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class lukuvinkkiDatabase {
    private String dbName;
        /**
     * constructor where user gives the name for the database when invoked
     *
     * @param databaseName the address of database given as a parameter
     * @throws SQLException when connection fails
     */
    public lukuvinkkiDatabase(String databaseName){
        this.dbName = databaseName;
    } 
    /**
     * constructor without parameters
     *
     * @throws SQLException when connection fails
     */

    public lukuvinkkiDatabase(){
        this.dbName = "lukuvinkki.db";
    } 

        /**
     * Connects to the database
     *
     * @return connection to the database
     * @throws java.sql.SQLException when the connection to the database fails
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
     * initializeDatabase method initializes the five database tables
     *
     * @return returns true if the database tables are created
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


     /**
     * The method initializeBookTable will create the table books if it does not
     * already exist in the database
     */
    public void initializeBookTable() {
        try {
            Connection connection = connect();

            PreparedStatement createBookTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS books ("
                    + "id INTEGER PRIMARY KEY,"
                    + "title  varchar(100), "
                    + "author  varchar(100), "
                    + "pageCount  integer; "
            );
            createBookTable.execute();
            createBookTable.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        finally { 
            System.out.println("finally block executed"); 
        } 
    }

}  