package DAL;

import java.sql.*;

public class DriverDAO implements IDAO {
    private DB db;

    public DriverDAO() {
        this.db = new DB();
    }

    // Insert a new driver
    @Override
    public void insert(Object object) {
        DriverDTO driver = (DriverDTO) object;
        try {
            Connection connection = DB.connect();
            String sql = "INSERT INTO Driver(name, typeOflicence) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, driver.name);
            ps.setString(2, driver.typeOflicence);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    // Delete a driver
    public void remove(int id) {
        try {
            Connection connection = DB.connect();
            String sql = "DELETE FROM Driver WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object get(int id) {
        DriverDTO driver = null;
        try {
            Connection connection = DB.connect();
            String sql = "SELECT * FROM Driver WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String typeOflicence = rs.getString("typeOflicence");
                int driverId = rs.getInt("id");
                driver = new DriverDTO(name, typeOflicence, driverId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }
    // Check if a driver with a specific type of licence exists
    public boolean checkIfDriverExistsByLicence(String type) {
        boolean exists = false;
        try {
            Connection connection = DB.connect();
            String sql = "SELECT COUNT(*) AS count FROM Driver WHERE typeOflicence = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }
    // Count all drivers
    public int countRecords() {
        int count = 0;
        try {
            Connection connection = DB.connect();
            String sql = "SELECT COUNT(*) AS count FROM Driver";
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