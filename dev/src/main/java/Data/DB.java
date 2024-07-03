package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    protected static BranchesDAO  branchesDAO = new BranchesDAO();

    protected static WorkersDAO workersDAO = new WorkersDAO();

    protected static RolesDAO rolesDAO = new RolesDAO();

    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    public DB(){
        branchesDAO =new BranchesDAO();
        workersDAO = new WorkersDAO();
        rolesDAO = new RolesDAO();
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
}
