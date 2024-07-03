package Data;

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
            Connection connection = DB.connect();
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
            Connection connection = DB.connect();
            String sql = "insert into Roles (id, role) values (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(data.get("id")));

            int amount = Integer.parseInt(data.get("amount"));
            for(int i =0; i<amount; i++){
                preparedStatement.setString(2, data.get("i"));
                preparedStatement.execute();
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean update(Dictionary<String, String> data){
        try {
            Connection connection = DB.connect();
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
            Connection connection = DB.connect();
            String sql = "DELETE FROM Roles WHERE id = "+ id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
