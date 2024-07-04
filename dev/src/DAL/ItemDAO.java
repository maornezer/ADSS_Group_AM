package DAL;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAO implements IDAO {
//    private DB db;
//
//    public ItemDAO() {
//        this.db = new DB();
//    }

    // Insert a new item
    @Override
    public void insert(Object object) {
        ItemDTO item = (ItemDTO) object;
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "INSERT INTO Item(id,name, amount, id0) VALUES(?,?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, item.id);
            ps.setString(2, item.name);
            ps.setInt(3, item.amount);
            ps.setInt(4, item.id0);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("lock item");

        }
    }

    @Override
    // Delete an item
    public void remove(int id) {
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM Item WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object get(int id) {
        ItemDTO item = null;
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM Item WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int amount = rs.getInt("amount");
                int id0 = rs.getInt("id0");
                item = new ItemDTO(id, name, amount, id0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }

    // Get list of item IDs by order ID
    public ArrayList<Integer> getItemIdsByOrderId(int idOrder) {
        ArrayList<Integer> itemIds = new ArrayList<>();
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "SELECT id FROM Item WHERE id0 = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idOrder);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                itemIds.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return itemIds;
    }
}
//package DAL;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class ItemDAO implements IDAO {
//    private DB db;
//
//    public ItemDAO() {
//        this.db = new DB();
//    }
//
//    // Insert a new item
//    @Override
//    public void insert(Object object) {
//        ItemDTO item = (ItemDTO) object;
//        Connection connection = null;
//        try {
//            connection = db.getDB();
//            String sql = "INSERT INTO Item(name, amount, id0) VALUES(?, ?, ?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, item.name);
//            ps.setInt(2, item.amount);
//            ps.setInt(3, item.id0);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    // Delete an item
//    public void remove(int id) {
//        Connection connection = null;
//        try {
//            connection = db.getDB();
//            String sql = "DELETE FROM Item WHERE id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public Object get(int id) {
//        ItemDTO item = null;
//        Connection connection = null;
//        try {
//            connection = db.getDB();
//            String sql = "SELECT * FROM Item WHERE id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                String name = rs.getString("name");
//                int amount = rs.getInt("amount");
//                int id0 = rs.getInt("id0");
//                item = new ItemDTO(id, name, amount, id0);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return item;
//    }
//
//    // Get list of item IDs by order ID
//    public ArrayList<Integer> getItemIdsByOrderId(int idOrder) {
//        ArrayList<Integer> itemIds = new ArrayList<>();
//        Connection connection = null;
//        try {
//            connection = db.getDB();
//            String sql = "SELECT id FROM Item WHERE id0 = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, idOrder);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                itemIds.add(rs.getInt("id"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return itemIds;
//    }
//}
