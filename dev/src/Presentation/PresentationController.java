package Presentation;

import Domain.*;

import java.time.LocalDate;
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
    public boolean searchDriver(int id){return transportController.getLogistics().searchDriver(id);}
    public boolean removeDriver(Dictionary<String, String> data) {return transportController.getLogistics().removeDriver(data);}
    public Driver getDriver(Dictionary<String, String> data) {return transportController.getLogistics().getDriver(data);}
    public boolean freeDriver(int idD, int idO) {return transportController.freeDriver(idD,idO);}
    public boolean checkMatchLicense(String idT, String idD) {return transportController.getLogistics().checkMatchLicense(idT,idD);}

    /// truck ///
    public boolean addTruck(Dictionary<String, String> data){ return this.transportController.getLogistics().addTruck(data);}
    public boolean changeTruck(Dictionary<String, String> data){return this.transportController.changeTruck(data);}
    public String getTypeOfLicense(int idT) {return transportController.getLogistics().getTruck(idT).getTypeOfLicense();}
    public boolean searchTruck(int idTruck) {return transportController.getLogistics().searchTruck(idTruck);}
    public boolean removeTruck(Dictionary<String, String> data) {return transportController.getLogistics().remove(data);}
    public Truck getTruck(Dictionary<String, String> data) {return transportController.getLogistics().getTruck(data);}
    public boolean freeTruck(int idT, int idOrder) {return transportController.freeTruck(idT,idOrder);}

    /// site ///
    public boolean addSite(Dictionary<String, String> data) {return this.transportController.getOperations().addSite(data);}
    public boolean changeDestination(Dictionary<String, String> data){return this.transportController.getOperations().changeDestination(data);}
    public boolean validMatchZone(String source, String destination ) {return transportController.getOperations().validMatchZone( source, destination );}
    public boolean removeSite(Dictionary<String, String> data) {return transportController.getOperations().removeSite(data);}
    public Site getSite(Dictionary<String, String> data) { return transportController.getOperations().getSite(data);}
    public boolean searchSite(String sourceID) {return transportController.getOperations().searchSite(Integer.parseInt(sourceID));}

    /// order + item ///
    public int creatNewOrder(Dictionary<String, String> data1, Dictionary<Integer, ArrayList<String>> data2) {return this.transportController.getOperations().addOrder(data1, data2);}
    public String getItemInOrder(String idOrder) {return this.transportController.getOperations().generateOrderReport(idOrder);}
    public void printOrder(Dictionary<String, String> data) {System.out.println(transportController.getOperations().printOrder(data));}
    public boolean removeOrder(Dictionary<String, String> data) {return transportController.getOperations().removeOrder(data);}
    public Order getOrder(String data) {return transportController.getOperations().getOrder(Integer.parseInt(data));}
    public boolean checkOrder(String idOrder) {return transportController.getOperations().searchOrder(Integer.parseInt(idOrder));}
    public boolean associatedTransport(String idOrder) {return transportController.getOperations().associatedTransport(Integer.parseInt(idOrder));}
    public boolean searchItem(int id) {return transportController.getOperations().searchItem(id);}

    /// transport ///
    public int addTransport(Dictionary<String, String> data) {return this.transportController.addTransport(data);}
    public String getTransportReport(String transportId) {return transportController.generateTransportReport(transportId);}
    public boolean addOrderToTransport(Dictionary<String, String> data){return transportController.addOrderToTransport(data);}
    public boolean loadOrderToTruck(Dictionary<String, String> data) {return transportController.loadOrderToTruck(data);}
    public boolean UnloadingItems(Dictionary<String, String> data) {return transportController.treatmentWeightProblemUnloadingItems(data);}
    public ArrayList<Order> getAllOrdersByTransport(int transportID) {return transportController.getTransport(transportID).getMyOrders();}
    public boolean isTransportExist(int transID) {return transportController.searchTransport(transID);}
    public void updateComplete(int transportID) {transportController.updateComplete(transportID);}
    public boolean getStatus(int idT) {return transportController.getStatus(idT);}


    public String getTransportDetails(LocalDate date) {
        return transportController.getTransportDetails( date);
    }
}
