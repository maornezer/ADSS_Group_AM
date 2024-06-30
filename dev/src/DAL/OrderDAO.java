package DAL;

import java.sql.*;

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
            Connection connection = DB.connect();
            String sql = "INSERT INTO `Order`(date, source, destination, transportId) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, order.date);
            ps.setString(2, order.source);
            ps.setString(3, order.destination);
            ps.setInt(4, order.transportId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    // Delete an order
    public void remove(int id) {
        try {
            Connection connection = DB.connect();
            String sql = "DELETE FROM `Order` WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object get(int id) {
        OrderDTO order = null;
        try {
            Connection connection = DB.connect();
            String sql = "SELECT * FROM `Order` WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String date = rs.getString("date");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                int transportId = rs.getInt("transportId");
                order = new OrderDTO(id, date, source, destination, transportId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }
}
