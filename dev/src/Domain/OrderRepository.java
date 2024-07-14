package Domain;

import DAL.OrderDAO;
import DAL.OrderDTO;
import DAL.TransportDTO;
import DAL.TruckDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderRepository implements IRepository{
    private ArrayList<Order> allOrders;
    private OrderDAO orderDAO;


    public OrderRepository() {
        allOrders = new ArrayList<>();
        orderDAO = new OrderDAO();
    }

    public boolean insert(Object o) {
        Order order = (Order) o;
        OrderDTO orderDTO  = new OrderDTO(order.getId(),order.getDate().toString(), order.getSource().getAddress(), order.getDestination().getAddress(), order.getSourceID(),order.getDestinationID(), -1);
        orderDAO.insert(orderDTO);
        allOrders.add(order);
        return true;
    }

    public boolean remove(int id) {
        for (Order o : allOrders)
        {
            if (o.getId() == id){
                allOrders.remove(o);
                break;
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
        allOrders.add(order);
        return order;
    }

    public int getSizeOrders() {return allOrders.size();}

    public int getMaxId() {return orderDAO.getMaxOrderId();}

    public void updateIDTransport(int idOrder,int idTransport){orderDAO.updateIDTransport(idOrder,idTransport);}

    public List<Integer> getOrderIdsByTransportId(int idTransport) {return orderDAO.getOrderIdsByTransportId(idTransport);}

    public void updateSiteDest(Order orderTemp, Branch site) {
        orderTemp.setDestinationID(site.getBranchNum());
        orderTemp.setDestination(site);
        orderDAO.updateDestination(orderTemp.getId(), site.getBranchNum(), site.getAddress());
    }
}
