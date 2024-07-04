package DAL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IDAO {

    // Insert a new order
    @Override
    public void insert(Object object) {
        OrderDTO order = (OrderDTO) object;
        try {
            Connection connection = DB.getConnection();
            //Connection connection = db.getDB();
            String sql = "INSERT INTO `Order`(date,idSource ,source, idDestination,destination,idT) VALUES(?, ?, ?, ?, ?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, order.date);
            ps.setInt(2, order.sourceID);
            ps.setString(3, order.source);
            ps.setInt(4, order.destinationID);
            ps.setString(5, order.destination);
            ps.setInt(6,-1);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("lock order");
        }
    }


    @Override
    // Delete an order
    public void remove(int id) {
        try {
            // Delete items first
            Connection connection = DB.getConnection();
            String sqlItems = "DELETE FROM `Item` WHERE id0 = ?";
            PreparedStatement psItems = connection.prepareStatement(sqlItems);
            psItems.setInt(1, id);
            psItems.executeUpdate();
            psItems.close();

            // Delete order
            String sqlOrder = "DELETE FROM `Order` WHERE id = ?";
            PreparedStatement psOrder = connection.prepareStatement(sqlOrder);
            psOrder.setInt(1, id);
            psOrder.executeUpdate();
            psOrder.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public Object get(int id) {
        OrderDTO order = null;
        try {
            Connection connection = DB.getConnection();
            //Connection connection = db.getDB();
            String sql = "SELECT * FROM `Order` WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String date = rs.getString("date");
                int idSource = rs.getInt("idSource");
                String source = rs.getString("source");
                int idDestination = rs.getInt("idDestination");
                String destination = rs.getString("destination");
                int transportId = rs.getInt("idT");
                order = new OrderDTO(id,date,source,destination,idSource,idDestination,transportId);
                ps.executeBatch();
                ps.close();
            }
            rs.close();
            ps.close();
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public int getMaxOrderId() {
        int maxId = -1;
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT MAX(id) FROM `Order`";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maxId;
    }
    public void updateIDTransport(int idOrder, int idTransport) {
        try {
            Connection connection = DB.getConnection();
            String sql = "UPDATE `Order` SET idT = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idTransport);
            ps.setInt(2, idOrder);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Integer> getOrderIdsByTransportId(int idTransport) {
        List<Integer> orderIds = new ArrayList<>();
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT id FROM `Order` WHERE idT = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idTransport);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orderIds.add(rs.getInt("id"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderIds;
    }


}
