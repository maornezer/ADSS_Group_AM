package DAL;

import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class WorkersDAO {

    public Worker read(int id){
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM Workers WHERE id = " + id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet  resultSet  = preparedStatement.executeQuery();
            Dictionary<String,String> data = new Hashtable<>();

            createWorkerDict(resultSet, data);

            return new Worker(data);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Worker> readWorkersByBranch(int branchNum){
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM Workers WHERE branchNum = " + branchNum + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet  resultSet  = preparedStatement.executeQuery();
            List<Worker> workerList = new ArrayList<>();

            while(resultSet.next()) {
                Dictionary<String, String> data = new Hashtable<>();
                createWorkerDict(resultSet, data);
                workerList.add(new Worker(data));
            }
            return workerList;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private void createWorkerDict(ResultSet resultSet, Dictionary<String, String> data){
        try {
            data.put("branchNum", Integer.toString(resultSet.getInt("branchNum")));
            data.put("firstName", resultSet.getString("firstName"));
            data.put("lastName", resultSet.getString(("lastName")));
            data.put("bankDetails", Integer.toString(resultSet.getInt("bankDetails")));
            data.put("hourRate", resultSet.getString(("hourRate")));
            data.put("jobType", resultSet.getString(("jobType")));
            data.put("year", resultSet.getString(("year")));
            data.put("month", resultSet.getString(("month")));
            data.put("day", resultSet.getString(("day")));
            data.put("id", Integer.toString(resultSet.getInt("id")));

            ArrayList<String> roles = DB.getRolesDAO().read(resultSet.getInt("id"));
            int amount = roles.size();
            data.put("amount", Integer.toString(amount));

            for (int i = 0; i < amount; i++) {
                data.put(Integer.toString(i), roles.get(i));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Worker create(Dictionary<String, String> data){
        try {
            Connection connection = DB.getConnection();
            String sql ="insert into Workers (id, firstName, lastName, bankDetails, hourRate, jobType, \"year\", \"month\", \"day\", branchNum) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(data.get("id")));
            preparedStatement.setString(2, data.get("firstName"));
            preparedStatement.setString(3,data.get("lastName"));
            preparedStatement.setInt(4,Integer.parseInt(data.get("bankDetails")));
            preparedStatement.setInt(5, Integer.parseInt(data.get("hourRate")));
            preparedStatement.setString(6, data.get("jobType"));
            preparedStatement.setInt(7, Integer.parseInt(data.get("year")));
            preparedStatement.setInt(8,Integer.parseInt(data.get("month")));
            preparedStatement.setInt(9,Integer.parseInt(data.get("day")));
            preparedStatement.setInt(10,  Integer.parseInt(data.get("branchNum")));

            preparedStatement.execute();

            DB.getRolesDAO().create(data);

            return new Worker(data);
        }
        catch (Exception e){
            System.out.println(e.getMessage() + e.getLocalizedMessage());
            return null;
        }
    }

    public Boolean update(Dictionary<String, String> data){
        if(data.get("update").equals("role")) {
            if (data.get("update2").equals("add"))
                return DB.getRolesDAO().update(data);
            else {
                DB.getRolesDAO().delete(Integer.parseInt(data.get("key")), data.get("value"));
                return true;
            }
        }
        try {
            Connection connection = DB.getConnection();
//            System.out.println("update = " + data.get("update"));
//            System.out.println("year = " + data.get("year"));
//            System.out.println("month = " + data.get("month"));
//            System.out.println("day = " + data.get("day"));
//            System.out.println("key = " + data.get("key"));
//            System.out.println("value = " + data.get("value"));
            if(data.get("update").equals("date"))
            {
                String sql = "UPDATE Workers SET year = "+ Integer.parseInt(data.get("year")) + " WHERE id = "+ Integer.parseInt(data.get("key")) +";";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();

                sql = "UPDATE Workers SET month = "+ Integer.parseInt(data.get("month")) + " WHERE id = "+ Integer.parseInt(data.get("key")) +";";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();

                sql = "UPDATE Workers SET day = "+ Integer.parseInt(data.get("day")) + " WHERE id = "+ Integer.parseInt(data.get("key")) +";";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();
            }

            else {
                String columnToUpdate = data.get("update");
                String sql = "UPDATE Workers SET " + columnToUpdate + " = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Set the value based on the type
                if (data.get("type").equals("int")) {
                    preparedStatement.setInt(1, Integer.parseInt(data.get("value")));
                } else {
                    preparedStatement.setString(1, data.get("value"));
                }

                preparedStatement.setInt(2, Integer.parseInt(data.get("key")));
                preparedStatement.execute();
            }


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
            String sql = "DELETE FROM Workers WHERE id = "+ id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            DB.getRolesDAO().delete(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}