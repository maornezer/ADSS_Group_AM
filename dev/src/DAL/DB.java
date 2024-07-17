package DAL;
import java.sql.*;
public class DB {


    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private TruckDAO truckDAO;
    private DriverDAO driverDAO;
    private TransportDAO transportDAO;
    private WorkersDAO workersDAO;
    private static RolesDAO rolesDAO = new RolesDAO();
    private ShiftHistoryDAO shiftHistoryDAO;

    protected static Connection connection;
    private static final String URL = "jdbc:sqlite:C:/Users/USER/Desktop/Ben Gurion/second year/semester D/analysis and planning/PROJECT/dev/src/resources/Transports.sqlite";

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
        workersDAO = new WorkersDAO();
        shiftHistoryDAO = new ShiftHistoryDAO();
    }
    public static Connection connect()throws SQLException{return DriverManager.getConnection(URL);}

    public static RolesDAO getRolesDAO() {
        return rolesDAO;
    }
}
