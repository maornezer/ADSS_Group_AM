package Domain;

import DAL.TransportDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class TransportController
{
    private OperationsController operations;
    private LogisticsController logistics;
    private TransportRepository transportRepo;

    public TransportController()
    {
        this.operations = new OperationsController();
        this.logistics = new LogisticsController();
        transportRepo = new TransportRepository();
    }

    public TransportRepository getTransportRepo() {
        return transportRepo;
    }

    public OperationsController getOperations() {
        return operations;
    }

    public LogisticsController getLogistics() {return logistics;}

    public int addTransport(Dictionary<String, String> data) {
        int idT = Integer.parseInt(data.get("idT"));
        int idD = Integer.parseInt(data.get("idD"));
        int idO = Integer.parseInt(data.get("idO"));
        Truck truck = logistics.getTruck(idT);
        Driver driver = logistics.getDriver(idD);
        Order order = operations.getOrder(idO);
        int maxID = transportRepo.getMaxId() + 1;
        Transport transport = new Transport(maxID,truck, driver, order);
        order.setTransportAssociation(transport.getId());
        operations.updateIDTransport(idO,maxID);
        transportRepo.insert(transport);
        return transport.getId();
    }

    public boolean remove(int id) {
        if (!searchTransport(id))
            return false;
        return transportRepo.remove(id);
    }

    public boolean searchTransport(int id) {return transportRepo.search(id);}

    public Transport getTransport(int id){
        if (!searchTransport(id))
            return null;
        if(transportRepo.get(id) == null)
        {
            TransportDTO tDTO = transportRepo.helpGetFunc(id);
            Truck truck = logistics.getTruck(tDTO.idT);
            Driver driver = logistics.getDriver(tDTO.idD);
            List<Integer> ordersID = operations.getOrderIdsByTransportId(id);
            ArrayList<Order> orders = new ArrayList<>();

            for (Integer idO : ordersID)
            {
                Order o = operations.getOrder(idO);
                orders.add(o);
            }
            Transport t = new Transport(truck,driver,tDTO.id);//order
            t.setDate(orders.get(0).getDate());
            t.setMyOrders(orders);
            t.setZone(orders.get(0).getSource().getSiteZone());
            transportRepo.getTransports().add(t);
            return t;
        }
        return transportRepo.get(id);
    }

    public boolean addOrderToTransport(Dictionary<String,String> data){
        int transportID = Integer.parseInt(data.get("transportID"));
        int orderID = Integer.parseInt(data.get("orderID"));
        Transport transport = getTransport(transportID);
        Order order = operations.getOrder(orderID);
        if(order.getSource().getSiteZone().compareTo(transport.getZone()) == 0) {
            if(transport.getDate().isEqual(order.getDate())) {
                transport.getMyOrders().add(order);
                operations.updateIDTransport(orderID,transportID);
                return true;
            }
        }
        return false;
    }

    public boolean changeTruck(Dictionary<String,String> data) {
        int idTransport = Integer.parseInt(data.get("idTransport"));
        int idTruck = Integer.parseInt(data.get("idTruck"));
        return changeTruck(idTransport, idTruck);
    }

    public boolean changeTruck(int idTransport,int idTruck) {
        Transport tempTransport = getTransport(idTransport);
        Truck tempTruck = logistics.getTruck(idTruck);
        if (tempTransport.getDriver().getTypeOfLicense().compareTo(tempTruck.getTypeOfLicense())==0) {
            if(freeTruck(idTruck,tempTransport.getMyOrders().get(0).getId()))
            {
                transportRepo.updateTruck(idTruck,idTransport);
                tempTransport.setTruck(tempTruck);
                return true;
            }
        }
        return false;
    }

    /// driver///
    public boolean changeDriver(int transportID, int newDriverID)
    {
        Transport tempTransport = getTransport(transportID);
        Driver tempDriver = logistics.getDriver(newDriverID);

        if (tempTransport.getTruck().getTypeOfLicense().compareTo(tempDriver.getTypeOfLicense())==0) {
            if(freeDriver(newDriverID,tempTransport.getMyOrders().get(0).getId()))
            {
                transportRepo.updateDriver(newDriverID,transportID);
                tempTransport.setDriver(tempDriver);
                return true;
            }
        }
        return false;
    }
    public String generateTransportReport(String id) {
        int idT = Integer.parseInt(id);
        Transport transport = getTransport(idT);
        StringBuilder sb = new StringBuilder();
        sb.append(transport.toStringTransportReport());
        return sb.toString();
    }
    public String generateTransportReport() {
        StringBuilder report = new StringBuilder();
        for (Transport transport : transportRepo.getTransports()) {
            report.append(transport.toString()).append("\n");
        }
        return report.toString();
    }
    public boolean loadOrderToTruck(Dictionary<String,String> data)
    {
        int orderWeight = Integer.parseInt(data.get("weight"));
        int orderID = Integer.parseInt(data.get("orderID"));
        int transportID = Integer.parseInt(data.get("transportID"));
        Transport transportTemp = getTransport(transportID);
        Order orderTemp = operations.getOrder(orderID);
        orderTemp.setOrderWeight(orderWeight);
        Truck truckTemp = transportTemp.getTruck();
        if (truckTemp.getCurrWeight() + orderWeight > truckTemp.getMaxWeight())
        {
            return false;
        }
        truckTemp.setAddToCurrWeight(orderWeight);
        return true;


    }
    public boolean unloadOrderFromTruck(double orderWeight,int orderID, int transportID)
    {
        Transport transportTemp = getTransport(transportID);
        Order orderTemp = operations.getOrder(orderID);
        if (transportTemp != null && orderTemp != null)
        {
            if (transportTemp.getMyOrders().contains(orderTemp))
            {
                orderTemp.setOrderWeight(orderWeight);
                int truckIDTemp = transportTemp.getTruck().getIdTruck();
                Truck truckTemp = logistics.getTruck(truckIDTemp);

                truckTemp.setSubFromCurrWeight(orderWeight);
                return true;
            }
        }
        return false;
    }

    public boolean treatmentWeightProblemUnloadingItems(Dictionary<String,String> data)
    {
        int orderID = Integer.parseInt(data.get("orderID"));
        int itemID = Integer.parseInt(data.get("itemID"));
        int amount = Integer.parseInt(data.get("amount"));
        int transportID = Integer.parseInt(data.get("transportID"));
        Order orderTemp = operations.getOrder(orderID);
        Item itemTemp = operations.getItem(itemID);
        if (amount < 0 || !orderTemp.getItems().contains(itemTemp))
        {
            return false;
        }
        else
        {
            boolean b = orderTemp.changeAmount(itemID,amount);
            if (b)
                {
                    operations.getItemRepo().updateAmount(itemID, amount);
                    getTransport(transportID).setUnloadingItems();
                }
        return true;
        }
    }

    public boolean changeDriver(Dictionary<String,String> data)
    {
        int idTransport = Integer.parseInt(data.get("idTransport"));
        int idDriver = Integer.parseInt(data.get("idDriver"));
        return changeDriver(idTransport, idDriver);
    }





















    public String printAllTransport()
    {
        StringBuilder allTransports = new StringBuilder();
        for (Transport transport: transportRepo.getTransports())
        {
            allTransports.append(transport.toStringTransportReport());
        }
        return allTransports.toString();
    }

    public String getTransportByIdDriver(String id)
    {
        StringBuilder sb = new StringBuilder();
        for (Transport transport : transportRepo.getTransports())
        {
            if (transport.getDriver().getId() == Integer.parseInt(id))
            {
                sb.append(transport.toStringTransportReport());
            }
        }
        int idDriver = Integer.parseInt(id);
        if (!(logistics.searchDriver(idDriver)))
        {
            System.out.println("Driver with ID "+ id+  " not found");
        }
        if (sb.length() == 0)
        {
            System.out.println("You do not have scheduled transports");
        }

        return sb.toString();
    }
    public String printAllOrdersByTransportID(int transportID)
    {
        Transport tempTransport = getTransport(transportID);
        StringBuilder sb = new StringBuilder();
        for (Order order: tempTransport.getMyOrders())
        {
            sb.append(order.toStringReport());
            //sb.append("\n");
        }
        return sb.toString();
    }
    public String printAllOrdersByTransportID(Dictionary<String,String> data) {
        int id = Integer.parseInt(data.get("id"));
        StringBuilder sb = new StringBuilder();
        sb.append(printAllOrdersByTransportID(id));
        return sb.toString();
    }

    public boolean freeTruck(int idTruck, int idOrder)
    {
        Order order = getOperations().getOrder(idOrder);
        //Truck truck = getLogistics().getTruck(idTruck);
        List<Integer> transportIds = transportRepo.getTransportIdsByTruck(idTruck);
        if(transportIds.isEmpty())
            return true;
        for (Integer idTransport: transportIds)
        {
            Transport transport = getTransport(idTransport);
            if(transport.getDate().isEqual(order.getDate()))
            {
                return false;
            }
        }
        return true;
    }

    public boolean freeDriver(int idD, int idO) {
        Order order = getOperations().getOrder(idO);
        //Driver driver = getLogistics().getDriver(idD);
        List<Integer> transportIds = transportRepo.getTransportIdsByDriver(idD);
        if(transportIds.isEmpty())
            return true;
        for (Integer idTransport: transportIds)
        {
            Transport transport = getTransport(idTransport);
            if(transport.getDate().isEqual(order.getDate()))
            {
                return false;
            }
        }
        return true;
    }

    public void updateComplete(int transportID) {transportRepo.updateComplete(transportID);}

    public boolean getStatus(int idT) {return transportRepo.getStatus(idT);}
}
