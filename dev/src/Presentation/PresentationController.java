package Presentation;

import Domain.Order;
import Domain.TransportController;

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
    public void addDriver(Dictionary<String, String> data){this.transportController.getLogistics().addDriver(data);}
    public void changeDriver(Dictionary<String, String> data){this.transportController.changeDriver(data);}
    public void seeAllTransportByDriver(String id) {System.out.println(transportController.getTransportByIdDriver(id));}
    public boolean checkIfDriverExistsByLicence(String type){return transportController.getLogistics().checkIfDriverExistsByLicence(type);
    }
    public void printallDriversByLicense(String licenseType) {System.out.println(transportController.getLogistics().printDriversByLicenseType(licenseType));}

    public boolean isIdDriverExists(String id){return transportController.getLogistics().isIdDriverExists(id);
    }


    /// truck ///
    public void addTruck(Dictionary<String, String> data){this.transportController.getLogistics().addTruck(data);}
    public void changeTruck(Dictionary<String, String> data){this.transportController.changeTruck(data);}
    public void printAllTrucks() {System.out.println(transportController.getLogistics().toStringTrucks());}
    public String getTypeOfLicense(int idT) {return transportController.getLogistics().getTruck(idT).getTypeOfLicense();}
    public boolean existTruck(int idTruck) {
        return transportController.getLogistics().isTruckExists(idTruck);
    }


    /// site ///
    public void addSite(Dictionary<String, String> data,String str) {this.transportController.getOperations().addSite(data,str);}
    public boolean changeDestination(Dictionary<String, String> data){return this.transportController.getOperations().changeDestination(data);}
    public boolean checkAddress(String source) {return this.transportController.getOperations().isAddressSiteAlreadyIn(source);}
    public void printAllAddress() {System.out.println(transportController.getOperations().printAllAddress());}
    public boolean validMatchZone(String source, String destination ) {return transportController.getOperations().validMatchZone( source, destination );}


    /// order + item ///
    public int creatNewOrder(Dictionary<String, String> data1, Dictionary<Integer, ArrayList<String>> data2) {return this.transportController.getOperations().addOrder(data1, data2);}
    public void printAllOrders() {System.out.print(transportController.getOperations().toStringOrders());}
    public void getItemInOrder(String idOrder) {System.out.println(this.transportController.getOperations().generateOrderReport(idOrder));}
    public void printOrder(Dictionary<String, String> data) {System.out.println(transportController.getOperations().printOrder(data));}















    /// transport ///
    public int addTransport(Dictionary<String, String> data) {return this.transportController.addTransport(data);}
    public void printAllOrdersByTransport(Dictionary<String, String> data){System.out.println(this.transportController.printAllOrdersByTransportID(data));}
    public void getTransportReport(String transportId) {System.out.println(transportController.generateTransportReport(transportId));}
    public void printAllTransports(){System.out.println(transportController.printAllTransport());}
    public boolean addOrderToTransport(Dictionary<String, String> data){return transportController.addOrderToTransport(data);}
    public boolean loadOrderToTruck(Dictionary<String, String> data) {return transportController.loadOrderToTruck(data);}

    public boolean treatmentWeightProblemChangeTruck(Dictionary<String, String> data) {return transportController.treatmentWeightProblemChangeTruck(data);}

    public boolean UnloadingItems(Dictionary<String, String> data) {return transportController.treatmentWeightProblemUnloadingItems(data);}

    public ArrayList<Order> getAllOrdersByTransport(String transportID) {
        return transportController.getTransportByID(transportID).getMyOrders();}

    public int getSizeOfListTrucks() {
        return transportController.getSizeOfListTrucks();
    }
    public int getSizeOfListDrivers() {
        return transportController.getSizeOfListDrivers();
    }
    public int getSizeOfListOrders() {
        return transportController.getSizeOfListOrders();
    }
    public int getSizeOfListTransports() {
        return transportController.getSizeOfListTransports();
    }


    public boolean orderExist(String parseInt, String transportID) {
        return transportController.orderExist(parseInt, transportID);
    }
    public boolean isTransportExist(String transID) {return transportController.isTransportExist(transID);}
    public boolean listSizeIsEmpty() {
        return transportController.listSizeIsEmpty();
    }
    public boolean treatmentWeightProblemChangeDestination(Dictionary<String, String> data) {return transportController.treatmentWeightProblemChangeDestination(data);}
}
