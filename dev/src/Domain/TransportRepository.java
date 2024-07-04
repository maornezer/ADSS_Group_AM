package Domain;

import DAL.TransportDAO;
import DAL.TransportDTO;
import DAL.TruckDTO;

import java.util.ArrayList;
import java.util.List;

public class TransportRepository
{
    private ArrayList<Transport> transports;
    public TransportDAO transportDAO;

    public TransportRepository()
    {
        transports = new ArrayList<>();
        transportDAO = new TransportDAO();
    }

    public boolean insert(Transport transport)
    {
        TransportDTO transportDTO = new TransportDTO(transport.getId(), transport.getTruck().getIdTruck(),transport.getDriver().getId());
        transports.add(transport);
        transportDAO.insert(transportDTO);
        return true;
    }

    public boolean remove(int id) {
        for (Transport t : transports) {
            if (t.getId() == id) {
                transports.remove(t);
                break;
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
        //Transport transport = new Transport((TransportDTO) transportDAO.get(id));
        //transports.add(transport);
        return null;
    }

    public TransportDTO helpGetFunc(int id) {return (TransportDTO) transportDAO.get(id);}

    public int countRecords() {return transportDAO.countRecords();}

    public ArrayList<Transport> getTransports() {return transports;}

    public boolean searchOrder(int id, int idOrder)
    {
        Transport transport = get(id);
        ArrayList<Order> myOrders = transport.getMyOrders();
        for (Order order: myOrders){
            if (order.getId() == idOrder)
            {
                return true;
            }
        }
        return false;
    }


    public List<Integer> getTransportIdsByTruck(int idTruck){return transportDAO.getTransportIdsByTruck(idTruck);}

    public List<Integer> getTransportIdsByDriver(int idDriver){return transportDAO.getTransportIdsByDriver(idDriver);}
    public int getMaxId() {return transportDAO.getMaxTransportId();}


}
