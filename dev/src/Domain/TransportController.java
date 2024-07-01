package Domain;

import DAL.TransportDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;

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

    public ArrayList<Transport> getTransports() {return transportRepo.getTransports();}

    public OperationsController getOperations() {
        return operations;
    }

    public LogisticsController getLogistics() {return logistics;}

    public int addTransport(Dictionary<String, String> data) {
        int idT = Integer.parseInt(data.get("idT"));
        int idD = Integer.parseInt(data.get("idD"));
        return addTransport(idT, idD);
    }
    public int addTransport(int idTruck, int idDriver) {
        Truck truck = logistics.getTruck(idTruck);
        Driver driver = logistics.getDriver(idDriver);
        if (truck == null) {
            return -1;
        }
        if(driver == null || driver.getTypeOfLicense().compareTo(truck.getTypeOfLicense()) != 0 ) {
            return -2;
        }
        Transport transport = new Transport(truck, driver);
        transportRepo.insert(transport);
        return transport.getId();
    }

    public boolean remove(int id) {
        if (!searchTransport(id))
            return false;
        return transportRepo.remove(id);
    }
    //int idT = Integer.parseInt(transportID);

    public boolean searchTransport(int id) {return transportRepo.search(id);}

    public Transport getTransport(int id){
        if (!searchTransport(id))
            return null;
        if(transportRepo.get(id) == null)
        {
            TransportDTO tDTO = transportRepo.helpGetFunc(id);
            Truck truck = logistics.getTruck(tDTO.idT);
            Driver driver = logistics.getDriver(tDTO.idD);
            //Order order = operations.getOrderByID(tDTO.idO);
            Transport t = new Transport(truck,driver,tDTO.id);//order
            transportRepo.getTransports().add(t);
            return t;
        }
        return transportRepo.get(id);
    }

    public int getSizeOfListTransports() {return transportRepo.countRecords();}

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    public boolean addOrderToTransport(Dictionary<String,String> data){
        int transportID = Integer.parseInt(data.get("transportID"));
        int orderID = Integer.parseInt(data.get("orderID"));
        Order order = operations.getOrder(orderID);
        if(order == null) {
            System.out.println("There is no order with id "+ orderID + " in the system");
            return false;
        }
        return addOrderToTransport(transportID,orderID);
    }

    public boolean addOrderToTransport(int transportID, int orderID)
    {
        Transport tempTransport = getTransport(transportID);
        Order tempOrder = operations.getOrder(orderID);
        if(tempTransport != null) {
            if(!tempTransport.getMyOrders().isEmpty()) {
                if(tempOrder.getSource().getSiteZone().compareTo(tempTransport.getZone()) == 0) {
                    if(tempTransport.getDate().isEqual(tempOrder.getDate())) {
                        tempTransport.addOrderToMYTransport(tempOrder);
                        return true;
                    }
                    else {
                        System.out.println("The order date you wanted to add does not match the shipping date");
                    }
                }
                else {
                    System.out.println("The order zone you wanted to add does not match the shipping zone");
                }
            }
            else {
                tempTransport.addOrderToMYTransport(tempOrder);
                return true;
            }
        }
        return false;
    }



    public boolean listSizeIsEmpty() {return operations.sizeOfSites() == 0;}

    public int getSizeOfListTrucks() {
        return logistics.getSizeTrucks();
    }

    public int getSizeOfListDrivers() {return logistics.getSizeDrivers();}

    public int getSizeOfListOrders() {return operations.getSizeOrders();}


   // public boolean orderExist(String parseInt, String transportID) {return transportRepo.orderExist(parseInt, transportID);}

























    public boolean changeTruck(int transportID, int newTruckID) {
        Transport tempTransport = getTransport(transportID);
        if(tempTransport !=null) {
            Truck tempTruck = logistics.getTruck(newTruckID);
            if (tempTransport.getDriver().getTypeOfLicense().compareTo(tempTruck.getTypeOfLicense())==0) {
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
        if(tempTransport !=null)
        {
            Driver tempDriver = logistics.getDriver(newDriverID);
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
    ///it will be change:
    public boolean driverAvailability (LocalDate date,Driver driver)
    {
        for (Transport tempTransport : transportRepo.getTransports())
        {
            if (tempTransport.getDriver() == driver && tempTransport.getDate()== date)
            {
                return false;
            }
        }
        return true;
    }
    public String generateTransportReport(String id) {
        int idT = Integer.parseInt(id);
        Transport transport = getTransport(idT);
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
        return loadOrderToTruck(orderWeight,orderID,transportID);
    }

    public boolean loadOrderToTruck(double orderWeight,int orderID, int transportID)
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
                if (truckTemp.getCurrWeight() + orderWeight > truckTemp.getMaxWeight())
                {
//                    System.out.println("Unsuccessful loading! The weight of the truck is greater than its maximum weight");
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
            getTransport(transportID).setChangeTruck();
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
        Order orderTemp = operations.getOrder(orderID);
        Item itemTemp = orderTemp.getItemByID(itemID);
        if (amount < 0 || !orderTemp.getItems().contains(itemTemp))
        {
            System.out.println("Error! The order " + orderID + " is not in contains this id item" );
            return false;
        }
        else
        {
            boolean b = orderTemp.changeAmount(itemID,amount);
            if (b)
                {
                    getTransport(transportID).setUnloadingItems();
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
        boolean b = operations.changeDestination(orderID,address);
        if (b)
        {
            getTransport(transportID).setChangeDestination();
            return true;
        }
        return false;
    }
    public boolean changeTruck(Dictionary<String,String> data)
    {
        int idTransport = Integer.parseInt(data.get("idTransport"));
        int idTruck = Integer.parseInt(data.get("idTruck"));
        return changeTruck(idTransport, idTruck);
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

}
