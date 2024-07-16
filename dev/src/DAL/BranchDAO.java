package DAL;

import Domain.Branch;

import java.sql.*;
import java.util.*;

public class BranchDAO {
    public Branch read(int id) {

        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM Site WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Dictionary<String, String> data = new Hashtable<>();
                data.put("branchNum", Integer.toString(resultSet.getInt("id")));
                data.put("address", resultSet.getString("address"));
                data.put("zone",resultSet.getString("zone"));
                data.put("contactName",resultSet.getString("contactName"));
                data.put("phoneNumber",resultSet.getString("phoneNumber"));
                data.put("deadline", resultSet.getString("deadline"));

                return new Branch(data);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("BranchDAO " + e.getMessage());
            return null;
        }
    }

    public Branch create(Dictionary<String, String> data) {

        try {
            Connection connection = DB.getConnection();
            String sql = "INSERT INTO Site (id, address, zone, contactName, phoneNumber, deadline) VALUES (?, ?, ?, ?, ? ,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(data.get("branchNum")));
            preparedStatement.setString(2, data.get("address"));
            preparedStatement.setString(3, data.get("zone"));
            preparedStatement.setString(4, data.get("contactName"));
            preparedStatement.setString(5, data.get("phoneNumber"));
            preparedStatement.setString(6, data.get("deadline"));
            preparedStatement.executeQuery();

            return new Branch(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean update(Dictionary<String, String> data) {

        try {
            String fieldToUpdate = data.get("update");
            if (fieldToUpdate != null && fieldToUpdate.equals("deadline")) {
                Connection connection = DB.getConnection();
                String sql = "UPDATE Site SET deadline = ? WHERE id = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, data.get("value"));
                preparedStatement.setInt(2, Integer.parseInt(data.get("key")));

                preparedStatement.executeUpdate();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void delete(int id) {
        String deleteSiteSql = "DELETE FROM Site WHERE id = ?;";
        String selectWorkersSql = "SELECT * FROM Workers WHERE branchNum = ?;";
        String deleteRoleSql = "DELETE FROM Roles WHERE id = ?;";
        String deleteWorkersSql = "DELETE FROM Workers WHERE branchNum = ?;";

        try (Connection connection = DB.getConnection()) {
            connection.setAutoCommit(false);  // Start transaction

            try (PreparedStatement deleteSiteStmt = connection.prepareStatement(deleteSiteSql);
                 PreparedStatement selectWorkersStmt = connection.prepareStatement(selectWorkersSql);
                 PreparedStatement deleteRoleStmt = connection.prepareStatement(deleteRoleSql);
                 PreparedStatement deleteWorkersStmt = connection.prepareStatement(deleteWorkersSql)) {

                // Delete from Site
                deleteSiteStmt.setInt(1, id);
                deleteSiteStmt.executeUpdate();

                // Select from Workers
                selectWorkersStmt.setInt(1, id);
                try (ResultSet resultSet = selectWorkersStmt.executeQuery()) {
                    while (resultSet.next()) {
                        // Delete from Roles
                        deleteRoleStmt.setInt(1, resultSet.getInt("id"));
                        deleteRoleStmt.executeUpdate();
                    }
                }

                // Delete from Workers
                deleteWorkersStmt.setInt(1, id);
                deleteWorkersStmt.executeUpdate();

                connection.commit();  // Commit transaction
            } catch (Exception e) {
                connection.rollback();  // Rollback transaction on error
                throw e;  // Rethrow exception
            }
        } catch (Exception e) {
            e.printStackTrace();  // Consider using a logging framework
        }
    }

//
//        try {
//            Connection connection = DB.getConnection();
//            String sql = "DELETE FROM Site WHERE id = ?;";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//
//            sql = "SELECT * FROM Workers WHERE branchNum = ?;";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()){
//                sql = "DELETE FROM Roles WHERE id = ?;";
//                preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setInt(1, resultSet.getInt("id"));
//                preparedStatement.executeUpdate();
//            }
//
//            sql = "DELETE FROM Workers WHERE branchNum = ?;";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }


    public List<Integer> getBranchesNums() {
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM Site;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Integer> branchesNums = new ArrayList<>();
            while (resultSet.next()) {
                branchesNums.add(resultSet.getInt("id"));
            }
            return branchesNums;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}


//package DAL;
//
//import Domain.Branch;
//
//import java.sql .*;
//import java.util .*;
//
//public class BranchDAO {
//    public Branch read(int id) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DB.getConnection();
//            String sql = "SELECT * FROM Site WHERE id = ?;";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                Dictionary<String, String> data = new Hashtable<>();
//                data.put("branchNum", Integer.toString(resultSet.getInt("id")));
//                data.put("address", resultSet.getString("address"));
//                data.put("zone",resultSet.getString("zone"));
//                data.put("contactName",resultSet.getString("contactName"));
//                data.put("phoneNumber",resultSet.getString("phoneNumber"));
//                data.put("deadline", resultSet.getString("deadline"));
//
//                return new Branch(data);
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            System.out.println("BranchDAO " + e.getMessage());
//            return null;
//        } finally {
//            closeResources(resultSet, preparedStatement, connection);
//        }
//    }
//
//    public Branch create(Dictionary<String, String> data) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = DB.getConnection();
//            String sql = "INSERT INTO Site (id, address, zone, contactName, phoneNumber, deadline) VALUES (?, ?, ?, ?, ? ,?);";
//            preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setInt(1, Integer.parseInt(data.get("branchNum")));
//            preparedStatement.setString(2, data.get("address"));
//            preparedStatement.setString(3, data.get("zone"));
//            preparedStatement.setString(4, data.get("contactName"));
//            preparedStatement.setString(5, data.get("phoneNumber"));
//            preparedStatement.setString(6, data.get("deadline"));
//
//            preparedStatement.executeUpdate();
//            return new Branch(data);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        } finally {
//            closeResources(null, preparedStatement, connection);
//        }
//    }
//
//    public Boolean update(Dictionary<String, String> data) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            String fieldToUpdate = data.get("update");
//            if (fieldToUpdate != null && fieldToUpdate.equals("deadline")) {
//                connection = DB.getConnection();
//                String sql = "UPDATE Site SET deadline = ? WHERE id = ?;";
//                preparedStatement = connection.prepareStatement(sql);
//
//                preparedStatement.setString(1, data.get("value"));
//                preparedStatement.setInt(2, Integer.parseInt(data.get("key")));
//
//                preparedStatement.executeUpdate();
//                return true;
//            }
//            return false;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        } finally {
//            closeResources(null, preparedStatement, connection);
//        }
//    }
//
//    public void delete(int id) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = DB.getConnection();
//            String sql = "DELETE FROM Site WHERE id = ?;";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            closeResources(null, preparedStatement, connection);
//        }
//    }
//
//    public List<Integer> getBranchesNums() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DB.getConnection();
//            String sql = "SELECT DISTINCT id FROM Site;";
//            preparedStatement = connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//
//            List<Integer> branchesNums = new ArrayList<>();
//            while (resultSet.next()) {
//                branchesNums.add(resultSet.getInt("id"));
//            }
//            return branchesNums;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        } finally {
//            closeResources(resultSet, preparedStatement, connection);
//        }
//    }
//
//    private void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
//        try {
//            if (resultSet != null) resultSet.close();
//            if (preparedStatement != null) preparedStatement.close();
//            if (connection != null) connection.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
//
