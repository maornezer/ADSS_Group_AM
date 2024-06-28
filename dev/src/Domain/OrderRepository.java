package Domain;

import java.util.ArrayList;

public class OrderRepository {
    private ArrayList<Order> allOrders;

    public OrderRepository()
    {
        allOrders = new ArrayList<>();
    }

    public Order getOrderByID(int id) {
        for (Order order : allOrders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public int getSizeOrders() {
        return allOrders.size();
    }
}
