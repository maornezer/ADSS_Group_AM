package DAL;

import java.sql.*;

public class DB {

        private static final String URL = "jdbc:sqlite:identifier.sqlite";

        public static Connection connect() throws SQLException {
                return DriverManager.getConnection(URL);

        }

}




    //private static final String URL = "jdbc:sqlite:C:/Users/USER/Desktop/Ben Gurion/second year/semester D/analysis and planning/PROJECT/dev/src/AMTransport.db";
//    private static final String URL = "jdbc:sqlite:C:\\Users\\USER\\Desktop\\Ben Gurion\\second year\\semester D\\analysis and planning\\PROJECT\\dev\\src\\AMtransports.db";
//    private Connection connection;
//
//    public DB()
//    {
//        try
//        {
//            //Class.forName("org.sqlite.JDBC");
//            this.connection = DriverManager.getConnection(URL);
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public Connection getDB(){return this.connection;}


