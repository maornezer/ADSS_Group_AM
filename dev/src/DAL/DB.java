//package DAL;
//
//import java.sql.*;
//
//public class DB {
//
//        private static final String URL = "jdbc:sqlite:identifier.sqlite";
//
//        public static Connection connect() throws SQLException {
//                return DriverManager.getConnection(URL);
//
//        }
//
//}
//
//


//package DAL;
//import java.sql.*;
//public class DB {
//
//    private static final String URL = "jdbc:sqlite:identifier.sqlite";
//    private Connection connection;
//
//    public DB() {
//        try {
//            this.connection = DriverManager.getConnection(URL);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    public Connection getDB(){return this.connection;}
//}


///noa
package DAL;
import java.sql.*;
public class DB {


    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private TruckDAO truckDAO;
    private DriverDAO driverDAO;
    private TransportDAO transportDAO;
    private SiteDAO siteDAO;
    private static final String URL = "jdbc:sqlite:identifier.sqlite";
    protected static Connection connection;

    static {
        try{
            connection = DriverManager.getConnection(URL);
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){return connection;}

    public DB() {
        itemDAO=new ItemDAO();
        orderDAO=new OrderDAO();
        truckDAO=new TruckDAO();
        driverDAO=new DriverDAO();
        transportDAO=new TransportDAO();
        siteDAO=new SiteDAO();
    }
    public static Connection connect()throws SQLException{return DriverManager.getConnection(URL);}
}
