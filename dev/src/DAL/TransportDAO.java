package DAL;

import java.sql.*;

public class TransportDAO implements IDAO {
//    private DB db;
//
//    public TransportDAO() {
//        this.db = new DB();
//    }

    // Insert a new transport
    @Override
    public void insert(Object object) {
        TransportDTO transport = (TransportDTO) object;
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "INSERT INTO Transport(idT, idD) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, transport.idT);
            ps.setInt(2, transport.idD);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    // Delete a transport
    public void remove(int id) {
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM Transport WHERE id = ?";
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
        TransportDTO transport = null;
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM Transport WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idT = rs.getInt("idT");
                int idD = rs.getInt("idD");
                transport = new TransportDTO(id, idT, idD);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transport;
    }

    // Count all transports
    public int countRecords() {
        int count = 0;
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "SELECT COUNT(*) AS count FROM Transport";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
