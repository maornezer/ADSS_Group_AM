package DAL;

import java.sql.*;

public class SiteDAO implements IDAO {
//    private DB db;
//
//    public SiteDAO() {
//        this.db = new DB();
//    }

    // Insert a new site
    @Override
    public void insert(Object object) {
        SiteDTO site = (SiteDTO) object;
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "INSERT INTO Site(address, zone, contactName, phoneNumber,id) VALUES(?, ?, ?, ?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, site.address);
            ps.setString(2, site.zone);
            ps.setString(3, site.contactName);
            ps.setString(4, site.phoneNumber);
            ps.setInt(5, site.id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    // Delete a site
    public void remove(int id) {
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "DELETE FROM Site WHERE id = ?";
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
        SiteDTO site = null;
        try {
            //Connection connection = DB.connect();
            //Connection connection = db.getDB();
            Connection connection = DB.getConnection();
            String sql = "SELECT * FROM Site WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String address = rs.getString("address");
                String zone = rs.getString("zone");
                String contactName = rs.getString("contactName");
                String phoneNumber = rs.getString("phoneNumber");
                site = new SiteDTO(address, zone, contactName, phoneNumber, id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return site;
    }
}