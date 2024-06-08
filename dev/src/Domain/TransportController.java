package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class TransportController
{
    private ArrayList<Transport> transports;
    //private static TransportController instance;
    private DomainController domain;

    public TransportController()
    {
        this.domain = new DomainController();
        transports = new ArrayList<>();

        //instance = this;

    }

    public ArrayList<Transport> getTransports() {
        return transports;
    }

    public DomainController getDomain() {
        return domain;
    }

    public int addTransport(Dictionary<String, String> data)
    {
        int idT = Integer.parseInt(data.get("idT"));
        int idD = Integer.parseInt(data.get("idD"));
        return addTransport(idT, idD);

    }
    public int addTransport(int idTruck, int idDriver)
    {

        Truck truck = domain.getTruckByID(idTruck);
        Driver driver = domain.getDriverByID(idDriver);
        if (truck == null)
        {
            return -1;
        }
        if(driver == null)
        {
            return -2;
        }
        Transport transport = new Transport(truck, driver);
        transports.add(transport);
        return transport.getId();
    }
//    public static TransportController getInstance()
//    {
//        if(instance == null)
//            instance = new TransportController();
//        return instance;
//    }
    public boolean addOrderToTransport(Dictionary<String,String> data){
        int transportID = Integer.parseInt(data.get("transportID"));
        int orderID = Integer.parseInt(data.get("orderID"));
        Order order = domain.getOrderByID(orderID);
        if(order == null)
        {
            System.out.println("There is no order with id "+ orderID + " in the system");
            return false;
        }
        return addOrderToTransport(transportID,orderID);
    }

    public boolean addOrderToTransport(int transportID, int orderID)
    {
        Transport tempTransport = getTransportByID(transportID);
        Order tempOrder = domain.getOrderByID(orderID);
        if(tempTransport != null)
        {
            if(!tempTransport.getMyOrders().isEmpty())
            {
                if(tempOrder.getSource().getSiteZone().compareTo(tempTransport.getZone()) == 0)
                {

                    if(tempTransport.getDate().isEqual(tempOrder.getDate()))
                    {
                        tempTransport.addOrderToMYTransport(tempOrder);
                        return true;
                    }
                    else
                    {
                        System.out.println("The order date you wanted to add does not match the shipping date");

                    }
                }
                else
                {
                    System.out.println("The order zone you wanted to add does not match the shipping zone");
                }
            }
            else
            {
                tempTransport.addOrderToMYTransport(tempOrder);
                return true;
            }
        }
        return false;
    }
//    public boolean addTransport(int id, Truck truck,Driver driver)
//    {
//        if (driver != null && truck!=null)
//        {
//            Transport transport = getTransportByID(id);
//            if(transport != null)
//                transport.createTransport(id, truck,driver);
//            return transports.add(transport);
//        }
//        return false;
//
//    }
    public boolean changeTruck(int transportID, int newTruckID)
    {
        Transport tempTransport = getTransportByID(transportID);
        if(tempTransport !=null)
        {
            Truck tempTruck = domain.getTruckByID(newTruckID);
            if (tempTransport.getDriver().getTypeOfLicense().compareTo(tempTruck.getTypeOfLicense())==0)
            {
                if(truckAvailability(tempTransport.getDate(),tempTruck))
                {
                    tempTransport.setTruck(tempTruck);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean truckAvailability (LocalDate date,Truck truck)
    {
        for (Transport tempTransport : transports)
        {
            if (tempTransport.getTruck() == truck && tempTransport.getDate()== date)
            {
                return false;
            }
        }
        return true;
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


    /// driver///
    public boolean changeDriver(int transportID, int newDriverID)
    {
        Transport tempTransport = getTransportByID(transportID);
        if(tempTransport !=null)
        {
            Driver tempDriver = domain.getDriverByID(newDriverID);
            if (tempTransport.getTruck().getTypeOfLicense().compareTo(tempDriver.getTypeOfLicense())==0)
            {
                if(driverAvailability(tempTransport.getDate(),tempDriver))
                {
                    tempTransport.setDriver(tempDriver);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean driverAvailability (LocalDate date,Driver driver)
    {
        for (Transport tempTransport : transports)
        {
            if (tempTransport.getDriver() == driver && tempTransport.getDate()== date)
            {
                return false;
            }
        }
        return true;
    }
    public String generateTransportReport(int id) {
        Transport transport = getTransportByID(id);
        StringBuilder sb = new StringBuilder();
        if (transport == null){
            System.out.println("Transport with ID "+ id +  " not found");
        }
        else {
            sb.append(transport.toStringTransportReport());
        }

        return sb.toString();
    }
    public String generateTransportReport() {
        StringBuilder report = new StringBuilder();
        for (Transport transport : transports) {
            report.append(transport.toString()).append("\n");
        }
        return report.toString();
    }
    public boolean loadOrderToTruck(Dictionary<String,String> data)
    {
        int orderWeight = Integer.parseInt(data.get("weight"));
        int orderID = Integer.parseInt(data.get("orderID"));
        int transportID = Integer.parseInt(data.get("transportID"));
        return loadOrderToTruck(orderWeight,orderID,transportID);
    }

    public boolean loadOrderToTruck(double orderWeight,int orderID, int transportID)
    {
        Transport transportTemp = getTransportByID(transportID);
        Order orderTemp = domain.getOrderByID(orderID);
        if (transportTemp != null && orderTemp != null)
        {
            if (transportTemp.getMyOrders().contains(orderTemp))
            {
                orderTemp.setOrderWeight(orderWeight);
                int truckIDTemp = transportTemp.getTruck().getIdTruck();
                Truck truckTemp = domain.getTruckByID(truckIDTemp);
                if (truckTemp.getCurrWeight() + orderWeight > truckTemp.getMaxWeight())
                {
                    System.out.println("Unsuccessful loading! The weight of the truck is greater than its maximum weight");
                    return false;
                }
                truckTemp.setAddToCurrWeight(orderWeight);
                return true;
            }
            System.out.println("Error! The order " + orderID + " is not in transport " + transportID);
        }
        return false;
    }
    public boolean unloadOrderFromTruck(double orderWeight,int orderID, int transportID)
    {
        Transport transportTemp = getTransportByID(transportID);
        Order orderTemp = domain.getOrderByID(orderID);
        if (transportTemp != null && orderTemp != null)
        {
            if (transportTemp.getMyOrders().contains(orderTemp))
            {
                orderTemp.setOrderWeight(orderWeight);
                int truckIDTemp = transportTemp.getTruck().getIdTruck();
                Truck truckTemp = domain.getTruckByID(truckIDTemp);

                truckTemp.setSubFromCurrWeight(orderWeight);
                return true;
            }
            System.out.println("Error! The order " + orderID + " is not in transport " + transportID);
        }
        return false;
    }
    public boolean treatmentWeightProblemChangeTruck(Dictionary<String,String> data)
    {
        int transportID = Integer.parseInt(data.get("transportID"));
        int truckId = Integer.parseInt(data.get("truckId"));
        return treatmentWeightProblemChangeTruck(transportID, truckId);
    }
    public boolean treatmentWeightProblemChangeTruck(int transportID,int truckId)
    {
        boolean b = changeTruck(transportID,truckId);
        if (b)
        {
            getTransportByID(transportID).setChangeTruck();
            return true;
        }
        return false;
    }
    public boolean treatmentWeightProblemUnloadingItems(Dictionary<String,String> data)
    {
        int orderID = Integer.parseInt(data.get("orderID"));
        int itemID = Integer.parseInt(data.get("itemID"));
        int amount = Integer.parseInt(data.get("amount"));
        int transportID = Integer.parseInt(data.get("transportID"));
        return treatmentWeightProblemUnloadingItems(orderID,itemID, amount, transportID);
    }

    public boolean treatmentWeightProblemUnloadingItems(int orderID,int itemID,int amount, int transportID) {
        Order orderTemp = domain.getOrderByID(orderID);
        Item itemTemp = orderTemp.getItemByID(itemID);
        if (amount < 0 || !orderTemp.getItems().contains(itemTemp))
        {
            System.out.println("Error! The order " + orderID + " is not in contains " + itemTemp.getName());
            return false;
        }
        else
        {
            boolean b = orderTemp.changeAmount(itemID,amount);
            if (b)
                {
                    getTransportByID(transportID).setUnloadingItems();
                }
        return true;
        }
    }
    public boolean treatmentWeightProblemChangeDestination(Dictionary<String,String> data)
    {
        int orderID = Integer.parseInt(data.get("orderID"));
        String address = data.get("address");
        int transportID = Integer.parseInt(data.get("transportID"));
        return treatmentWeightProblemChangeDestination(orderID, address, transportID );
    }

    public boolean treatmentWeightProblemChangeDestination(int orderID, String address, int transportID)
    {
        boolean b = domain.changeDestination(orderID,address);
        if (b)
        {
            getTransportByID(transportID).setChangeDestination();
            return true;
        }
        return false;
    }
    public boolean changeTruck(Dictionary<String,String> data)
    {
        int idTransport = Integer.parseInt(data.get("idTransport"));
        int idTruck = Integer.parseInt(data.get("idTruck"));

//        Transport tempTransport = getTransportByID(idTransport)
//        Truck tempTruck = domain.getTruckByID(idTruck);
        return changeTruck(idTransport, idTruck);
    }

    public boolean changeDriver(Dictionary<String,String> data)
    {
        int idTransport = Integer.parseInt(data.get("idTransport"));
        int idDriver = Integer.parseInt(data.get("idDriver"));

//        Transport tempTransport = getTransportByID(idTransport)
//        Truck tempTruck = domain.getTruckByID(idTruck);
        return changeDriver(idTransport, idDriver);
    }

    public String printAllOrdersByTransportID(int transportID)
    {
        Transport tempTransport = getTransportByID(transportID);
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

    public String getTransportByIdDriver(int id)
    {
        StringBuilder sb = new StringBuilder();
        for (Transport transport : transports)
           {
               if (transport.getDriver().getId() == id)
               {
                   sb.append(transport.toStringTransportReport());
               }
           }
        if (!(domain.isDriverExists(domain.getDriverByID(id))))
           {
               System.out.println("Driver with ID "+ id+  " not found");
           }
        if (sb.length() == 0)
        {
            System.out.println("You do not have scheduled transports");
        }

        return sb.toString();
    }
    public String printAllTransport()
    {
        StringBuilder allTransports = new StringBuilder();
        for (Transport transport: transports)
        {
            allTransports.append(transport.toStringTransportReport());
        }
        return allTransports.toString();
    }

    public boolean isTransportExist(int transID) {
        Transport transport = getTransportByID(transID);
        return transports.contains(transport);
    }
}
