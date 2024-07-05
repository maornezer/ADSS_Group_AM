package DAL;
import java.sql.*;
public class DB {


    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private TruckDAO truckDAO;
    private DriverDAO driverDAO;
    private TransportDAO transportDAO;
    private SiteDAO siteDAO;

    protected static Connection connection;
    private static final String URL = "jdbc:sqlite:src/resources/Transports.sqlite";
    //    //private static final String URL = "jdbc:sqlite:resource\\Transports.sqlite";
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
