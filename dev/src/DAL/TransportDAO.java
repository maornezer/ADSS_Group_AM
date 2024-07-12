package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportDAO implements IDAO {

    // Insert a new transport
    @Override
    public void insert(Object object) {
        TransportDTO transport = (TransportDTO) object;
        try {
            Connection connection = DB.getConnection();
            String sql = "INSERT INTO Transport(id,idT, idD,complete) VALUES(?,?, ?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, transport.id);
            ps.setInt(2, transport.idT);
            ps.setInt(3, transport.idD);
            ps.setString(4,"false");
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
    // Function to get transport IDs by truck ID
    public List<Integer> getTransportIdsByTruck(int idTruck) {
        List<Integer> transportIds = new ArrayList<>();
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT id FROM Transport WHERE idT = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idTruck);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transportIds.add(rs.getInt("id"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transportIds;
    }
    // Function to get transport IDs by driver ID
    public List<Integer> getTransportIdsByDriver(int idDriver) {
        List<Integer> transportIds = new ArrayList<>();
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT id FROM Transport WHERE idD = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idDriver);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transportIds.add(rs.getInt("id"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transportIds;
    }

    public int getMaxTransportId()
    {
        int maxId = -1;
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT MAX(id) FROM `Transport`";
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

    public void updateTruck(int idTruck, int idTransport) {
        try {
            Connection connection = DB.getConnection();
            String sql = "UPDATE Transport SET idT = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idTruck);
            ps.setInt(2, idTransport);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateDriver(int idDriver, int idTransport) {
        try {
            Connection connection = DB.getConnection();
            String sql = "UPDATE Transport SET idD = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idDriver);
            ps.setInt(2, idTransport);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateComplete(int transportID)
    {
        try {
            Connection connection = DB.getConnection();
            String sql = "UPDATE Transport SET complete = 'true' WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, transportID);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean getStatus(int idT) {
        boolean status = false;
        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT complete FROM Transport WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getString("complete").equalsIgnoreCase("true");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    //FOR NOA!!!
    public List<String[]> getTransportDetails() {
        List<String[]> transportDetails = new ArrayList<>();

        try {
            Connection connection = DB.getConnection();
            String sql = "SELECT t.idD AS idDriver, o.idDestination, o.date " +
                    "FROM `Order` o " +
                    "JOIN Transport t ON o.idT = t.id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int driverId = rs.getInt("idDriver");
                int destinationId = rs.getInt("idDestination");
                String date = rs.getString("date");

                // Extract year, month, and day from the date
                String[] dateParts = date.split("-");
                String year = dateParts[0];
                String month = dateParts[1];
                String day = dateParts[2];

                // Get the driver's name
                String driverName = getDriverNameById(driverId, connection);

                // Create an array and add it to the list
                String[] detail = {driverName, String.valueOf(destinationId), year, month, day};
                transportDetails.add(detail);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transportDetails;
    }

    private String getDriverNameById(int driverId, Connection connection) {
        String driverName = "";
        try {
            String sql = "SELECT name FROM Driver WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, driverId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                driverName = rs.getString("name");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return driverName;
    }
}
