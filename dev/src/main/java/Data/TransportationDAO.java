package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class TransportationDAO {
    public List<String[]> read(LocalDate date){
        try {
            Connection connection = DB.getConnection();
            List<String[]> res = new ArrayList<>();
            String sql = "SELECT * FROM Transportation WHERE year = " + date.getYear() + " AND month = "+date.getMonthValue()+ " AND day = "+ date.getDayOfMonth() +";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] temp = new String[7];
                for (int i = 0; i < 7; i++) {
                    temp[i] = resultSet.getString(i + 1);
                }
                res.add(temp);
            }

            return res;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}

//package Data;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Dictionary;
//import java.util.List;
//
//public class TransportationDAO {
//    public List<String[]> read(LocalDate date){
//        try {
//            Connection connection = DB.getConnection();
//            List<String[]> res = new ArrayList<>();
//                String sql = "SELECT * FROM Transportation WHERE year = " + date.getYear() + " AND month = "+date.getMonthValue()+ " AND day = "+ date.getDayOfMonth() +";";
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                while (resultSet.next()) {
//                    String[] temp = new String[7];
//                    for (int i = 0; i < 7; i++) {
//                        temp[i] = resultSet.getString(i + 1);
//                    }
//                    res.add(temp);
//                }
//
//            return res;
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//}
