package DAL;

import java.sql.*;

public class TruckDAO implements IDAO {
    private DB db;

    public TruckDAO() {
        this.db = new DB();
    }

    // Insert a new truck
    @Override
    public void insert(Object object) {
        TruckDTO truck = (TruckDTO) object;
        try {
            Connection connection = DB.connect();
            String sql = "INSERT INTO Truck(initialWeight, maxWeight, model, id) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, truck.initialWeight);
            ps.setDouble(2, truck.maxWeight);
            ps.setString(3, truck.model);
            ps.setInt(4, truck.id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    // Delete a truck
    public void remove(int id) {
        //Connection connection = db.getDB();
        try {
            Connection connection = DB.connect();
            String sql = "DELETE FROM Truck WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object get(int id) {
        TruckDTO truck = null;
        try {
            Connection connection = DB.connect();
            String sql = "SELECT * FROM Truck WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double initialWeight = rs.getInt("initialWeight");
                double maxWeight = rs.getInt("maxWeight");
                String model = rs.getString("model");
                int truckId = rs.getInt("id");
                truck = new TruckDTO(initialWeight, maxWeight, model, truckId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return truck;
    }
    // Count all trucks
    public int countRecords() {
        int count = 0;
        try {
            Connection connection = DB.connect();
            String sql = "SELECT COUNT(*) AS count FROM Truck";
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

//
//    // Get all trucks
//    public List<DTOTruck> getAllTrucks() {
//        List<DTOTruck> trucks = new ArrayList<>();
//        String sql = "SELECT initialWeight, maxWeight, model, id FROM Truck";
//
//        try (Connection conn = DriverManager.getConnection(URL);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                DTOTruck truck = new DTOTruck(
//                        rs.getInt("initialWeight"),
//                        rs.getInt("maxWeight"),
//                        rs.getString("model"),
//                        rs.getInt("id")
//                );
//                trucks.add(truck);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return trucks;
//    }
//
//    // Update a truck
//    public void updateTruck(DTOTruck truck) {
//        String sql = "UPDATE Truck SET initialWeight = ?, maxWeight = ?, model = ? WHERE id = ?";
//
//        try (Connection conn = DriverManager.getConnection(URL);
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, truck.getInitialWeight());
//            pstmt.setInt(2, truck.getMaxWeight());
//            pstmt.setString(3, truck.getModel());
//            pstmt.setInt(4, truck.getId());
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//


