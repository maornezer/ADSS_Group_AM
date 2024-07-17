package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

// add col of type shift

public class ShiftHistoryDAO {

    public List<String> read(int branchNum) {
        List<String> res = new ArrayList<>();



        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM ShiftHistory WHERE branchNum = ? ORDER BY weekCounter;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, branchNum);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                res.add(resultSet.getString("shiftString"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public void create(Dictionary<String, String> data) {

        try {
            Connection connection = DB.getConnection();
            String sql = "INSERT INTO ShiftHistory (branchNum, weekCounter, shiftString, dayOfWeek, shiftType) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int branchNum = Integer.parseInt(data.get("branchNum"));
            int weekCounter = Integer.parseInt(data.get("weekCounter"));

            for (int i = 0; i < 7; i++) {
                for(int j = 0; j<2; j++) {
                    String shiftStringKey = "shiftString" + i + j;
                    if (data.get(shiftStringKey) != null) {
                        preparedStatement.setInt(1, branchNum);
                        preparedStatement.setInt(2, weekCounter);
                        preparedStatement.setString(3, data.get(shiftStringKey));
                        preparedStatement.setInt(4, i + 1);
                        preparedStatement.setInt(5, j + 1);
                        preparedStatement.addBatch();
                    }
                }
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean update(Dictionary<String, String> data) {
        // Placeholder for update logic. To be implemented as per requirements.
        return true;
    }

    public void delete(int id) {

        try {
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM ShiftHistory WHERE branchNum = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {

        try {
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM ShiftHistory;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
