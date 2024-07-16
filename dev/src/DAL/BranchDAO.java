package DAL;

import Domain.Branch;

import java.sql .*;
import java.util .*;

public class BranchDAO {
    public Branch read(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            String sql = "SELECT * FROM Branches WHERE branchNum = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Dictionary<String, String> data = new Hashtable<>();
                data.put("branchNum", Integer.toString(resultSet.getInt("branchNum")));
                data.put("address", resultSet.getString("address"));
                data.put("deadline", resultSet.getString("deadline"));
                return new Branch(data);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }
    }

    public Branch create(Dictionary<String, String> data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();
            String sql = "INSERT INTO Branches (branchNum, address, deadline) VALUES (?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(data.get("branchNum")));
            preparedStatement.setString(2, data.get("address"));
            preparedStatement.setString(3, data.get("deadline"));

            preparedStatement.executeUpdate();
            return new Branch(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    public Boolean update(Dictionary<String, String> data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String fieldToUpdate = data.get("update");
            if (fieldToUpdate != null && fieldToUpdate.equals("deadline")) {
                connection = DB.getConnection();
                String sql = "UPDATE Branches SET deadline = ? WHERE branchNum = ?;";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, data.get("value"));
                preparedStatement.setInt(2, Integer.parseInt(data.get("key")));

                preparedStatement.executeUpdate();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();
            String sql = "DELETE FROM Branches WHERE branchNum = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    public List<Integer> getBranchesNums() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            String sql = "SELECT DISTINCT branchNum FROM Branches;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<Integer> branchesNums = new ArrayList<>();
            while (resultSet.next()) {
                branchesNums.add(resultSet.getInt("branchNum"));
            }
            return branchesNums;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }
    }

    private void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

