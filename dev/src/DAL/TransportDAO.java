package DAL;

import java.sql.*;

public class TransportDAO implements IDAO {
    private DB db;

    public TransportDAO() {
        this.db = new DB();
    }

    // Insert a new transport
    @Override
    public void insert(Object object) {
        TransportDTO transport = (TransportDTO) object;
        try {
            Connection connection = DB.connect();
            String sql = "INSERT INTO Transport(idT, idD, idO) VALUES(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, transport.idT);
            ps.setInt(2, transport.idD);
            ps.setInt(3, transport.idO);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    // Delete a transport
    public void remove(int id) {
        try {
            Connection connection = DB.connect();
            String sql = "DELETE FROM Transport WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object get(int id) {
        TransportDTO transport = null;
        try {
            Connection connection = DB.connect();
            String sql = "SELECT * FROM Transport WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idT = rs.getInt("idT");
                int idD = rs.getInt("idD");
                int idO = rs.getInt("idO");
                transport = new TransportDTO(id, idT, idD, idO);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transport;
    }
}
