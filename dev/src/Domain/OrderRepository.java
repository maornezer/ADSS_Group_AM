package Domain;

import DAL.OrderDAO;
import DAL.OrderDTO;
import DAL.TransportDTO;
import DAL.TruckDTO;

import java.util.ArrayList;

public class OrderRepository {
    private ArrayList<Order> allOrders;
    private OrderDAO orderDAO;

    public OrderRepository() {
        allOrders = new ArrayList<>();
        orderDAO = new OrderDAO();
    }

    public boolean insert(Order order) {
        orderDAO.insert(order);
        allOrders.add(order);
        return true;
    }

    public boolean remove(int id) {
        for (Order o : allOrders)
        {
            if (o.getId() == id){
                allOrders.remove(o);
            }
        }
        orderDAO.remove(id);
        return true;
    }

    public boolean search(int id) {
        for (Order o : allOrders) {
            if (o.getId() == id){
                return true;
            }
        }
        OrderDTO temp = (OrderDTO) orderDAO.get(id);
        return temp!=null;
    }

    public Order get(int id) {
        for (Order order : allOrders) {
            if (order.getId() == id) {
                return order;
            }
        }
        Order order = new Order((OrderDTO) orderDAO.get(id));
        //allOrders.add(order);///? לא פה
        return order;
    }
    public OrderDTO helpGetFunc(int id) {return (OrderDTO) orderDAO.get(id);}


    public ArrayList<Order> getAllOrders() {return allOrders;}

    public int getSizeOrders() {return allOrders.size();}
}
