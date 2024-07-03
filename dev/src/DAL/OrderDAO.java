//package DAL;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class OrderDAO implements IDAO {
//    private DB db;
//
//    public OrderDAO() {
//        this.db = new DB();
//    }
//
//    // Insert a new order
//    @Override
//    public void insert(Object object) {
//        OrderDTO order = (OrderDTO) object;
//        try {
//            Connection connection = DB.connect();
//            String sql = "INSERT INTO `Order`(date, source, destination, transportId) VALUES(?, ?, ?, ?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, order.date);
//            ps.setString(2, order.source);
//            ps.setString(3, order.destination);
//            ps.setInt(4, order.transportId);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    // Delete an order
//    public void remove(int id) {
//        try {
//            Connection connection = DB.connect();
//            String sql = "DELETE FROM `Order` WHERE id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public Object get(int id) {
//        OrderDTO order = null;
//        try {
//            Connection connection = DB.connect();
//            String sql = "SELECT * FROM `Order` WHERE id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                String date = rs.getString("date");
//                String source = rs.getString("source");
//                String destination = rs.getString("destination");
//                int transportId = rs.getInt("transportId");
//                order = new OrderDTO(id, date, source, destination, transportId);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return order;
//    }
//
//
//
//    private HashMap<Integer, List<Integer>> getOrderItems(int orderId) {
//        HashMap<Integer, List<Integer>> itemsMap = new HashMap<>();
//        try {
//            Connection connection = DB.connect();
//            String sql = "SELECT id FROM `Item` WHERE id0 = ?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, orderId);
//            ResultSet rs = stmt.executeQuery();
//            List<Integer> itemsList = new ArrayList<>();
//            while (rs.next()) {
//                itemsList.add(rs.getInt("id"));
//            }
//            itemsMap.put(orderId, itemsList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return itemsMap;
//    }
//}
package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDAO implements IDAO {
    private DB db;

    public OrderDAO() {
        this.db = new DB();
    }

    // Insert a new order
    @Override
    public void insert(Object object) {
        OrderDTO order = (OrderDTO) object;
        try {
            //Connection connection = DB.connect();
            Connection connection = db.getDB();
            String sql = "INSERT INTO `Order`(date,idSource ,source, idDestination,destination) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.date);
            ps.setInt(2, order.sourceID);
            ps.setString(3, order.source);
            ps.setInt(4, order.destinationID);
            ps.setString(5, order.destination);
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.id = generatedKeys.getInt(1);
            }

            // Insert items
            //insertOrderItems(order.id, order.items);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Insert order items
    private void insertOrderItems(int orderId, HashMap<Integer, List<Integer>> items) {
        try {
            //Connection connection = DB.connect();
            Connection connection = db.getDB();
            String sql = "INSERT INTO `Item` (id, name, amount, id0) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            for (Integer itemId : items.get(orderId)) {
                ps.setInt(1, itemId);
                // Assuming you have the item name and amount to set here
                ps.setString(2, "itemName"); // Replace with actual item name
                ps.setInt(3, 0); // Replace with actual item amount
                ps.setInt(4, orderId);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    // Delete an order
    public void remove(int id) {
        try {
            //Connection connection = DB.connect();
            Connection connection = db.getDB();
            // Delete items first
            String sqlItems = "DELETE FROM `Item` WHERE id0 = ?";
            PreparedStatement psItems = connection.prepareStatement(sqlItems);
            psItems.setInt(1, id);
            psItems.executeUpdate();

            // Delete order
            String sqlOrder = "DELETE FROM `Order` WHERE id = ?";
            PreparedStatement psOrder = connection.prepareStatement(sqlOrder);
            psOrder.setInt(1, id);
            psOrder.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object get(int id) {
        OrderDTO order = null;
        try {
            //Connection connection = DB.connect();
            Connection connection = db.getDB();
            String sql = "SELECT * FROM `Order` WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String date = rs.getString("date");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                int transportId = rs.getInt("idT");
                HashMap<Integer, List<Integer>> items = getOrderItems(id);
                //order = new OrderDTO(id, date, source, destination, transportId, items);
                //order = new OrderDTO(id, date, source, destination, transportId);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    private HashMap<Integer, List<Integer>> getOrderItems(int orderId) {
        HashMap<Integer, List<Integer>> itemsMap = new HashMap<>();
        try {
            //Connection connection = DB.connect();
            Connection connection = db.getDB();
            String sql = "SELECT id FROM `Item` WHERE id0 = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            List<Integer> itemsList = new ArrayList<>();
            while (rs.next()) {
                itemsList.add(rs.getInt("id"));
            }
            itemsMap.put(orderId, itemsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemsMap;
    }

}
