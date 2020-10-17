import java.sql.*;

public class DBClass {

    private static Connection conn;

    public static Connection getConnction() throws ClassNotFoundException,java.sql.SQLException{
        if (conn == null) {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_review", "milinda", "1234");
        }
        return conn;
    }

    public static void saveSentences(String idsent, String reviewsId, String sentences, String sentiment, String sentiProb){
       try {
           // create our java preparedstatement using a sql update query
           PreparedStatement ps = getConnction().prepareStatement( "INSERT INTO `hotel_review`.`sentences` (`idsent`, `reviewsId`, `sentences`, `sentiment`, `sentimentValue`) VALUES (?, ?, ?, ?, ?)" );

           // set the preparedstatement parameters
           ps.setString( 1, idsent );
           ps.setString( 2, reviewsId );
           ps.setString( 3, sentences );
           ps.setString( 4, sentiment );
           ps.setString( 5, sentiProb );

           // call executeUpdate to execute our sql update statement
           ps.executeUpdate();
           ps.close();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public static void saveKeywords(String idsent,  String keyword, String keywordProb){
        try {
            // create our java preparedstatement using a sql update query
            PreparedStatement ps = getConnction().prepareStatement( "INSERT INTO `hotel_review`.`keywords` (`idSent`, `keyword`, `keywordValue`) VALUES ( ?, ?, ?)" );

            // set the preparedstatement parameters
            ps.setString( 1, idsent );
            ps.setString( 2, keyword );
            ps.setString( 3, keywordProb );

            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
