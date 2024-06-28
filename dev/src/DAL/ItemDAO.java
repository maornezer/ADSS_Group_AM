package DAL;

import java.sql.*;

public class ItemDAO implements IDAO {
    private DB db;

    public ItemDAO() {
        this.db = new DB();
    }

    // Insert a new item
    @Override
    public void insert(Object object) {
        ItemDTO item = (ItemDTO) object;
        try {
            Connection connection = DB.connect();
            String sql = "INSERT INTO Item(name, amount, id0) VALUES(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, item.name);
            ps.setInt(2, item.amount);
            ps.setInt(3, item.id0);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    // Delete an item
    public void remove(int id) {
        try {
            Connection connection = DB.connect();
            String sql = "DELETE FROM Item WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object get(int id) {
        ItemDTO item = null;
        try {
            Connection connection = DB.connect();
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
}
