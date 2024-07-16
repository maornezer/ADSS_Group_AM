package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class ShiftHistoryDAO {

    public List<String> read(int branchNum) {
        List<String> res = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            String sql = "SELECT * FROM ShiftHistory WHERE branchNum = ? ORDER BY weekCounter;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, branchNum);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                res.add(resultSet.getString("shiftString"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }
        return res;
    }

    public void create(Dictionary<String, String> data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();
            String sql = "INSERT INTO ShiftHistory (branchNum, weekCounter, shiftString, dayOfWeek) VALUES (?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);

            int branchNum = Integer.parseInt(data.get("branchNum"));
            int weekCounter = Integer.parseInt(data.get("weekCounter"));

            for (int i = 0; i < 7; i++) {
                String shiftStringKey = "shiftString" + i;
                if (data.get(shiftStringKey) != null) {
                    preparedStatement.setInt(1, branchNum);
                    preparedStatement.setInt(2, weekCounter);
                    preparedStatement.setString(3, data.get(shiftStringKey));
                    preparedStatement.setInt(4, i + 1);
                    preparedStatement.addBatch();
                }
            }

            preparedStatement.executeBatch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    public Boolean update(Dictionary<String, String> data) {
        // Placeholder for update logic. To be implemented as per requirements.
        return true;
    }

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();
            String sql = "DELETE FROM ShiftHistory WHERE branchNum = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    public void delete() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();
            String sql = "DELETE FROM ShiftHistory;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(null, preparedStatement, connection);
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
