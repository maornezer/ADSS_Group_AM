package Presentation;

import Domain.*;

import java.util.ArrayList;
import java.util.Dictionary;

public class PresentationController {

    private TransportController transportController;


    public PresentationController()
    {
        this.transportController = new TransportController();
    }

    public TransportController getTransportController() {
        return transportController;
    }

    /// driver ///
    public boolean addDriver(Dictionary<String, String> data){return this.transportController.getLogistics().addDriver(data);}
    public boolean changeDriver(Dictionary<String, String> data){return this.transportController.changeDriver(data);}
    public void seeAllTransportByDriver(String id) {System.out.println(transportController.getTransportByIdDriver(id));}
    public boolean checkIfDriverExistsByLicence(String type){return transportController.getLogistics().checkIfDriverExistsByLicence(type);
    }
    public void printallDriversByLicense(String licenseType) {System.out.println(transportController.getLogistics().printDriversByLicenseType(licenseType));}

    public boolean searchDriver(int id){return transportController.getLogistics().searchDriver(id);
    }
    public boolean removeDriver(Dictionary<String, String> data) {return transportController.getLogistics().removeDriver(data);
    }
    public Driver getDriver(Dictionary<String, String> data) {return transportController.getLogistics().getDriver(data);
    }
    public boolean freeDriver(int idD, int idO) {return transportController.freeDriver(idD,idO);}
    public boolean checkMatchLicense(String idT, String idD) {return transportController.getLogistics().checkMatchLicense(idT,idD);
    }
    /// truck ///
    public boolean addTruck(Dictionary<String, String> data){ return this.transportController.getLogistics().addTruck(data);}
    public boolean changeTruck(Dictionary<String, String> data){return this.transportController.changeTruck(data);}
    public void printAllTrucks() {System.out.println(transportController.getLogistics().toStringTrucks());}
    public String getTypeOfLicense(int idT) {return transportController.getLogistics().getTruck(idT).getTypeOfLicense();}
    public boolean searchTruck(int idTruck) {return transportController.getLogistics().searchTruck(idTruck);}
    public boolean removeTruck(Dictionary<String, String> data) {return transportController.getLogistics().remove(data);}
    public Truck getTruck(Dictionary<String, String> data) {return transportController.getLogistics().getTruck(data);}
    public boolean freeTruck(int idT, int idOrder) {return transportController.freeTruck(idT,idOrder);}


    /// site ///
    public boolean addSite(Dictionary<String, String> data,String str) {return this.transportController.getOperations().addSite(data,str);}
    public boolean changeDestination(Dictionary<String, String> data){return this.transportController.getOperations().changeDestination(data);}
    public boolean checkAddress(String source) {return this.transportController.getOperations().isAddressSiteAlreadyIn(source);}
    public void printAllAddress() {System.out.println(transportController.getOperations().printAllAddress());}
    public boolean validMatchZone(String source, String destination ) {return transportController.getOperations().validMatchZone( source, destination );}
    public boolean removeSite(Dictionary<String, String> data) {return transportController.getOperations().removeSite(data);}
    public Site getSite(Dictionary<String, String> data) { return transportController.getOperations().getSite(data);}
    public boolean searchSite(String sourceID) {return transportController.getOperations().searchSite(Integer.parseInt(sourceID));}


    /// order + item ///
    public int creatNewOrder(Dictionary<String, String> data1, Dictionary<Integer, ArrayList<String>> data2) {return this.transportController.getOperations().addOrder(data1, data2);}
    public void printAllOrders() {System.out.print(transportController.getOperations().toStringOrders());}
    public void getItemInOrder(String idOrder) {System.out.println(this.transportController.getOperations().generateOrderReport(idOrder));}
    public void printOrder(Dictionary<String, String> data) {System.out.println(transportController.getOperations().printOrder(data));}
    public boolean removeOrder(Dictionary<String, String> data) {return transportController.getOperations().removeOrder(data);}
    public Order getOrder(String data) {return transportController.getOperations().getOrder(Integer.parseInt(data));}
    public boolean checkOrder(String idOrder) {return transportController.getOperations().searchOrder(Integer.parseInt(idOrder));}
    public boolean associatedTransport(String idOrder) {return transportController.getOperations().associatedTransport(Integer.parseInt(idOrder));}
    public boolean searchItem(int id) {return transportController.getOperations().searchItem(id);}










    /// transport ///
    public int addTransport(Dictionary<String, String> data) {return this.transportController.addTransport(data);}
    public void printAllOrdersByTransport(Dictionary<String, String> data){System.out.println(this.transportController.printAllOrdersByTransportID(data));}
    public void getTransportReport(String transportId) {System.out.println(transportController.generateTransportReport(transportId));}
    public void printAllTransports(){System.out.println(transportController.printAllTransport());}
    public boolean addOrderToTransport(Dictionary<String, String> data){return transportController.addOrderToTransport(data);}
    public boolean loadOrderToTruck(Dictionary<String, String> data) {return transportController.loadOrderToTruck(data);}

//public boolean treatmentWeightProblemChangeTruck(Dictionary<String, String> data) {return transportController.changeTruck(data);}

    public boolean UnloadingItems(Dictionary<String, String> data) {return transportController.treatmentWeightProblemUnloadingItems(data);}

    public ArrayList<Order> getAllOrdersByTransport(int transportID) {return transportController.getTransport(transportID).getMyOrders();}
    public int getSizeOfListTrucks() {return transportController.getSizeOfListTrucks();}
    public int getSizeOfListDrivers() {
        return transportController.getSizeOfListDrivers();
    }
    public int getSizeOfListOrders() {
        return transportController.getSizeOfListOrders();
    }
    public int getSizeOfListTransports() {
        return transportController.getSizeOfListTransports();
    }


    public boolean searchOrder(String transportID, String idOrder) {return transportController.searchOrder(transportID, idOrder);}
    public boolean isTransportExist(int transID) {return transportController.searchTransport(transID);}
    public boolean listSizeIsEmpty() {
        return transportController.listSizeIsEmpty();
    }
    public boolean treatmentWeightProblemChangeDestination(Dictionary<String, String> data) {return transportController.treatmentWeightProblemChangeDestination(data);}


    public void updateComplete(int transportID) {transportController.updateComplete(transportID);}

    public boolean getStatus(int idT) {return transportController.getStatus(idT);}
}
