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
//            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        } catch (Exception e) {
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

//package Data;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class DB {
//
//    protected static BranchesDAO  branchesDAO = new BranchesDAO();
//
//    protected static WorkersDAO workersDAO = new WorkersDAO();
//
//    protected static RolesDAO rolesDAO = new RolesDAO();
//
//    protected static ShiftHistoryDAO shiftHistoryDAO = new ShiftHistoryDAO();
//
//    protected static TransportationDAO transportationDAO = new TransportationDAO();
//
//    private static final Logger logger = LoggerFactory.getLogger(DB.class);
//    private static final String URL = "jdbc:sqlite:src/resources/identifier.sqlite";
//    private static Connection connection = null;
//
//    static {
//        try {
//            connection = DriverManager.getConnection(URL);
//        } catch (SQLException e) {
//            logger.error("Failed to establish a database connection.", e);
//            throw new RuntimeException("Failed to establish a database connection.", e);
//        }
//    }
//
//
////    static {
////        try {
////            connection = DriverManager.getConnection(URL);
////        } catch (SQLException e) {
////            logger.error("Failed to establish a database connection.", e);
////            throw new RuntimeException("Failed to establish a database connection.", e);
////        }
////    }
//
////    public static Connection getConnection() {
////        try {
//////            if (connection == null || connection.isClosed()) {
//////                connection = DriverManager.getConnection(URL);
//////            }
//////        } catch (SQLException e) {
//////            throw new RuntimeException("Failed to re-establish a database connection.", e);
//////        }
//////        return connection;
////
////    }
//
////    private static final String URL = "jdbc:sqlite:src/resources/identifier.sqlite";
////
////    protected static Connection connection;
////
////    static {
////        try {
////            connection = DriverManager.getConnection(URL);
////        } catch (SQLException e) {
////            throw new RuntimeException("Failed to establish a database connection.", e);
////        }
////    }
////
//    public static Connection getConnection() {
//        try {
//            if (connection == null || connection.isClosed()) {
//                connection = DriverManager.getConnection(URL);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to re-establish a database connection.", e);
//        }
//        return connection;
//    }
//
//
//
//
//
//
////    private static final String URL = "jdbc:sqlite:identifier.sqlite";
////
////    protected static Connection connection;
//
////    static {
////        try {
//////            String dbPath = DatabaseUtils.extractDatabase("/identifier.sqlite", "identifier");
//////            Class.forName("org.sqlite.JDBC");
////            //connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
////            connection = DriverManager.getConnection(URL);
////        } catch (Exception e) {
////            throw new RuntimeException(e);
////        }
////    }
//
////    static {
////        try {
////            Class.forName("org.sqlite.JDBC");
////            connection = DriverManager.getConnection("jdbc:sqlite::resource:identifier.sqlite");
////        } catch (ClassNotFoundException | SQLException e) {
////            throw new RuntimeException(e);
////        }
////    }
//
////    public static Connection getConnection() {
////        return connection;
////    }
//
//    public static Connection connect() throws SQLException {
//        return DriverManager.getConnection(URL);
//    }
//
//    public static BranchesDAO getBranchesDAO() {
//        return branchesDAO;
//    }
//
//    public static WorkersDAO getWorkersDAO() {
//        return workersDAO;
//    }
//
//    public static RolesDAO getRolesDAO() {
//        return rolesDAO;
//    }
//
//    public static ShiftHistoryDAO getShiftHistoryDAO() {
//        return shiftHistoryDAO;
//    }
//
//    public static TransportationDAO getTransportationDAO() {
//        return transportationDAO;
//    }
//}
//
//
