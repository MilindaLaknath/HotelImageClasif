/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;

/**
 *
 * @author Milinda Arambawela
 */

public class DBCon {
    private static Connection connection;                                             // The database connection object.
//    private static Statement statement;                                                 // the database statement object, used to execute SQL commands.
    private static String dbname = "hotel_review";
    private static String username = "root";
    private static String password = "";

    
    
    public static Connection getDbCon () {                                          // the constructor for the database manager.
        if (connection != null) {
            return connection;
        }
        String url = "jdbc:mysql://localhost:3306/" + dbname;                // our database--username is your O'Reilly login username and is passed in.
        try {
            Class.forName ("com.mysql.jdbc.Driver");                                    // get the driver for this database.
            System.out.println("Driver is set; ready to go!");
        }
        catch (Exception e) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            return null;                                                                // cannot even find the driver--return to caller since cannot do anything.
        }
        try {                                                                        // Establish the database connection, create a statement for execution of SQL commands.
            connection = DriverManager.getConnection (url, username, password );    // username and password are passed into this Constructor.
//            statement  = connection.createStatement();                              // statement used to do things in the database (e.g., create the PhoneBook table).
        }
        catch (SQLException exception ) {
            System.out.println ("\n*** SQLException caught ***\n");
            while (exception != null) 
            {                                                                       // grab the exception caught to tell us the problem.
                System.out.println ("SQLState:   " + exception.getSQLState()  );
                System.out.println ("Message:    " + exception.getMessage()   );
                System.out.println ("Error code: " + exception.getErrorCode() );
                exception = exception.getNextException ();
                System.out.println ( "" );
            }
        }
        catch (java.lang.Exception exception) {                                     // perhaps there is an exception that was not SQL related.
            exception.printStackTrace();                                            // shows a trace of the exception error--like we see in the console.
        }
        return connection;
    }
} 