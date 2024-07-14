package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class ShiftHistoryDAO {

    public List<String> read(int branchNum){
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM ShiftHistory WHERE branchNum =" + branchNum + ";";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet  = preparedStatement.executeQuery();

            ArrayList<String> res = new ArrayList<>();

            while (resultSet.next()){
                res.add(resultSet.getString("shiftString"));
            }
            return res;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void create(Dictionary<String, String> data){
        try {
            Connection connection = DB.getConnection();
            String sql = "insert into ShiftHistory (branchNum, weekCounter, shiftString, dayOfWeek) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(data.get("branchNum")));
            preparedStatement.setInt(2, Integer.parseInt(data.get("weekCounter")));

            for(int i = 0; i<7; i++) {
                if(data.get("shiftString" + Integer.toString(i)) != null) {
                    preparedStatement.setString(3, data.get("shiftString" + Integer.toString(i)));
                    preparedStatement.setInt(4, i + 1);

                    preparedStatement.execute();
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean update(Dictionary<String, String> data){
        return true;
    }

    public void delete(int id){
        try {
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM ShiftHistory WHERE branchNum = "+ id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(){
        try {
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM ShiftHistory;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}