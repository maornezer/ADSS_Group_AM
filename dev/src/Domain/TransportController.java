package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class TransportController
{
    private ArrayList<Transport> transports;
    private static TransportController instance;
    private DomainController domain;

    public TransportController()
    {
        domain = new DomainController();
        transports = new ArrayList<>();
        instance = this;
    }
    public Transport createNewTransport()
    {
        Transport transport = new Transport();
        transports.add(transport);
        return transport;
    }

    public int addTransport(Dictionary<String, String> data)
    {
        int transportID = Integer.parseInt(data.get("transportID"));
        int idT = Integer.parseInt(data.get("idT"));
        int idD = Integer.parseInt(data.get("idD"));

        Truck truck = domain.getTruckByID(idT);
        Driver driver = domain.getDriverByID(idD);
        if (truck == null)
        {
            return -1;
        }
        if(driver == null)
        {
            return -2;
        }
        Transport transport = getTransportByID(transportID);
        if (transport != null)
        {
            //allOrders.add(order);
            transport.createTransport(transportID,truck,driver);
            return 0;
        }
        return 0;
    }
    public static TransportController getInstance()
    {
        if(instance == null)
            instance = new TransportController();
        return instance;
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
                    if(tempTransport.getDate() == tempOrder.getDate())
                    {
                        tempTransport.addOrderToMYTransport(tempOrder);
                        return true;
                    }
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
    public boolean addTransport(int id, Truck truck,Driver driver)
    {
        if (driver != null && truck!=null)
        {
            Transport transport = getTransportByID(id);
            if(transport != null)
                transport.createTransport(id, truck,driver);
            return transports.add(transport);
        }
        return false;

    }
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
        return transport.toStringTransportReport();
    }
    public String generateTransportReport() {
        StringBuilder report = new StringBuilder();
        for (Transport transport : transports) {
            report.append(transport.toString()).append("\n");
        }
        return report.toString();
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

    public boolean treatmentWeightProblemChangeTruck(int transportID,int newID)
    {
        boolean b = changeTruck(transportID,newID);
        if (b)
        {
            getTransportByID(transportID).setChangeTruck();
            return true;
        }
        return false;
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
    public boolean treatmentWeightProblemChangeDestination(int orderID,String address,int transportID)
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
        return tempTransport.toString();
    }
    public void printAllOrdersByTransportID(Dictionary<String,String> data) {
        int id = Integer.parseInt(data.get("id"));
        printAllOrdersByTransportID(id);
    }

    public void getTransportByIdDriver(int id)
    {
           for (Transport transport : transports)
           {
               if (transport.getDriver().getId() == id)
               {
                   transport.toString();
               }
           }
    }

}
