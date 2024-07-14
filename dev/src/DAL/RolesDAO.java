package DAL;

import Domain.Branch;
import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class RolesDAO {

    public ArrayList<String> read(int id){
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT role FROM Roles WHERE id = " + id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet  = preparedStatement.executeQuery();

            ArrayList<String> res = new ArrayList<>();

            while (resultSet.next()){
                res.add(resultSet.getString("role"));
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
            int amount = Integer.parseInt(data.get("amount"));
            String sql = "insert into Roles (id, role) values (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(data.get("id")));
            for(int i =0; i<amount; i++){
                preparedStatement.setString(2, data.get(Integer.toString(i)));
                preparedStatement.execute();
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean update(Dictionary<String, String> data){
        try {
            Connection connection = DB.getConnection();
            String sql = "insert into Roles (id, role) values (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(data.get("key")));
            preparedStatement.setString(2, data.get("value"));

            preparedStatement.execute();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void delete(int id){
        try {
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM Roles WHERE id = "+ id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id, String role){
        try {
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM Roles WHERE id = "+ id + " AND role = "+ role + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}