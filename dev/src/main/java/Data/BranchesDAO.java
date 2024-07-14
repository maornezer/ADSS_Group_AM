package Data;

import Domain.Branch;
import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class BranchesDAO {

    public Branch read(int id){
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM Branches WHERE branchNum = " + id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet  = preparedStatement.executeQuery();

            Dictionary<String,String> data = new Hashtable<>();

            data.put("branchNum",Integer.toString(resultSet.getInt("branchNum")));
            data.put("address",resultSet.getString("address"));
            data.put("deadline",resultSet.getString(("deadline")));

            return new Branch(data);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Branch create(Dictionary<String, String> data){
        try {
            Connection connection = DB.getConnection();
            String sql ="insert into Branches (branchNum, address, deadline) values (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(data.get("branchNum")));
            preparedStatement.setString(2, data.get("address"));
            preparedStatement.setString(3,data.get("deadline"));

            preparedStatement.execute();

            return new Branch(data);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean update(Dictionary<String, String> data){
        try {
            if(data.get("update").equals("deadline")) {
                Connection connection = DB.getConnection();
                String sql = "UPDATE Branches SET deadline = ? WHERE branchNum = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, data.get("value"));
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
            String sql = "DELETE FROM Branches WHERE branchNum = "+ id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Integer> getBranchesNums(){

        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT DISTINCT branchNum FROM Branches;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Integer> branchesNums = new ArrayList<>();

            while (resultSet.next())
                branchesNums.add(resultSet.getInt("branchNum"));
            return branchesNums;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}


//package Data;
//
//import Domain.Branch;
//import Domain.Worker;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Dictionary;
//import java.util.Hashtable;
//import java.util.List;
//
//public class BranchesDAO {
//
////    public Branch read(int id){
////        try {
////            Connection connection = DB.getConnection();
////            String sql = "SELECT * FROM Branches WHERE branchNum = " + id + ";";
////            PreparedStatement preparedStatement = connection.prepareStatement(sql);
////            ResultSet resultSet  = preparedStatement.executeQuery();
////
////            Dictionary<String,String> data = new Hashtable<>();
////
////            data.put("branchNum",Integer.toString(resultSet.getInt("branchNum")));
////            data.put("address",resultSet.getString("address"));
////            data.put("deadline",resultSet.getString(("deadline")));
////
////            return new Branch(data);
////        }
////        catch (Exception e){
////            System.out.println(e.getMessage());
////            return null;
////        }
////
////    }
//
//    public Branch read(int id) {
//        String sql = "SELECT * FROM Branches WHERE branchNum = ?";
//
//        try (Connection connection = DB.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setInt(1, id);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    Dictionary<String, String> data = new Hashtable<>();
//                    data.put("branchNum", Integer.toString(resultSet.getInt("branchNum")));
//                    data.put("address", resultSet.getString("address"));
//                    data.put("deadline", resultSet.getString("deadline"));
//
//                    return new Branch(data);
//                } else {
//                    System.out.println("Branch not found with branchNum: " + id);
//                    return null;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//    public Branch create(Dictionary<String, String> data){
//        try {
//            Connection connection = DB.getConnection();
//            String sql ="insert into Branches (branchNum, address, deadline) values (?, ?, ?);";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setInt(1, Integer.parseInt(data.get("branchNum")));
//            preparedStatement.setString(2, data.get("address"));
//            preparedStatement.setString(3,data.get("deadline"));
//
//            preparedStatement.execute();
//
//            return new Branch(data);
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//    public Boolean update(Dictionary<String, String> data){
//        try {
//            if(data.get("update").equals("deadline")) {
//                Connection connection = DB.getConnection();
//                String sql = "UPDATE Branches SET deadline = ? WHERE branchNum = ?;";
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//                preparedStatement.setString(1, data.get("value"));
//                preparedStatement.setInt(2, Integer.parseInt(data.get("key")));
//
//                preparedStatement.execute();
//            }
//
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//            return false;
//
//        }
//        return true;
//    }
//
//    public void delete(int id){
//        try {
//            Connection connection = DB.getConnection();
//            String sql = "DELETE FROM Branches WHERE branchNum = "+ id + ";";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.execute();
//
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public List<Integer> getBranchesNums(){
//
//        try {
//            Connection connection = DB.getConnection();
//            String sql = "SELECT DISTINCT branchNum FROM Branches;";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            List<Integer> branchesNums = new ArrayList<>();
//
//            while (resultSet.next())
//                branchesNums.add(resultSet.getInt("branchNum"));
//            return branchesNums;
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//}
