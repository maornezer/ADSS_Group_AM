package Domain;

import java.util.ArrayList;

public class TransportRepository
{
    private ArrayList<Transport> transports;

    public TransportRepository()
    {
        transports = new ArrayList<>();

    }

    public ArrayList<Transport> getTransports() {
        return transports;
    }


    public void addTransport(Transport transport) {
        transports.add(transport);
    }
    public Transport getTransportByID(int transportID)
    {
        for (Transport transport : transports)
        {
            if (transport.getId() == transportID)
            {
                return transport;
            }
        }
        return null;
    }
    public boolean isTransportExist(int transID) {
        Transport transport = getTransportByID(transID);
        return transports.contains(transport);
    }
    public boolean orderExist(String parseInt, String transportID) {
        ArrayList<Order> myOrders = getTransportByID(Integer.parseInt(transportID)).getMyOrders();
        for (Order order: myOrders){
            if (order.getId() == Integer.parseInt(parseInt))
            {
                return true;
            }
        }
        return false;
    }
}
