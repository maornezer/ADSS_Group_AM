package Domain;

import DAL.TransportDAO;
import DAL.TransportDTO;

import java.util.ArrayList;

public class TransportRepository
{
    private ArrayList<Transport> transports;
    public TransportDAO transportDAO;

    public TransportRepository()
    {
        transports = new ArrayList<>();
        transportDAO = new TransportDAO();
    }

    public boolean insert (Transport transport)
    {
        transports.add(transport);
        transportDAO.insert(transport);
        return true;
    }

    public boolean remove(int id) {
        for (Transport t : transports) {
            if (t.getId() == id) {
                transports.remove(t);
            }
        }
        transportDAO.remove(id);
        return true;
    }

    public boolean search(int id) {
        for (Transport t : transports) {
            if (t.getId() == id){
                return true;
            }
        }
        TransportDTO temp = (TransportDTO) transportDAO.get(id);
        return temp != null;
    }


    public Transport get(int id){
        for (Transport transport : transports){
            if (transport.getId() == id){
                return transport;
            }
        }
        return null;
    }

    public TransportDTO helpGetFunc(int id) {return (TransportDTO) transportDAO.get(id);}

    public int countRecords() {return transportDAO.countRecords();}

    public ArrayList<Transport> getTransports() {return transports;}


}
//???
//    public boolean orderExist(String parseInt, String transportID) {
//        ArrayList<Order> myOrders = getTransportByID(Integer.parseInt(transportID)).getMyOrders();
//        for (Order order: myOrders){
//            if (order.getId() == Integer.parseInt(parseInt))
//            {
//                return true;
//            }
//        }
//        return false;
//    }
//}
