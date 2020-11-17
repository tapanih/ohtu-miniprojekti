
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
     *
     * 
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
          
            initializeUser();
        } catch (Throwable t) {

            System.out.println(t.getMessage());
            return false;
        }
        return true;
    }

        /**
     * The method initializeUser will create the table user if it does not
     * already exist in the database
     */
    public void initializeUser() {
        try {
            Connection connection = connect();

            PreparedStatement createUserTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user ("
                    + "id INTEGER PRIMARY KEY, "
                    + "username VARCHAR(100),"
                    + "password VARCHAR(100),"
                    + "UNIQUE(username, password)"
                    + ");"
            );
            createUserTable.execute();
            createUserTable.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{ 
            System.out.println("finally block executed"); 
        } 
    }

     /**
     * The method initializeBalance will create the table Balance if it does not
     * already exist in the database
     */
    public void initializeBookEntry() {
        try {
            Connection connection = connect();

            PreparedStatement createBookTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS books ("
                    + "id INTEGER PRIMARY KEY,"
                    + "user_username varchar(100),"
                    + "title  varchar(100), "
                    + "author  varchar(100), "
                    + "pageCount  integer, "
                    + "FOREIGN KEY (user_username) REFERENCES User(username));"
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