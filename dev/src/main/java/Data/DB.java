package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    protected static BranchesDAO  branchesDAO = new BranchesDAO();

    protected static WorkersDAO workersDAO = new WorkersDAO();

    protected static RolesDAO rolesDAO = new RolesDAO();

    protected static ShiftHistoryDAO shiftHistoryDAO = new ShiftHistoryDAO();

    protected static TransportationDAO transportationDAO = new TransportationDAO();

    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    protected static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite::resource:identifier.sqlite");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static BranchesDAO getBranchesDAO() {
        return branchesDAO;
    }

    public static WorkersDAO getWorkersDAO() {
        return workersDAO;
    }

    public static RolesDAO getRolesDAO() {
        return rolesDAO;
    }

    public static ShiftHistoryDAO getShiftHistoryDAO() {
        return shiftHistoryDAO;
    }

    public static TransportationDAO getTransportationDAO() {
        return transportationDAO;
    }
}
