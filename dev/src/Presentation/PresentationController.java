package Presentation;

import Domain.DomainController;
import Domain.Order;
import Domain.Transport;
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

    /// order + item ///
    public int creatNewOrder(Dictionary<String, String> data1, Dictionary<Integer, ArrayList<String>> data2) {return this.transportController.getDomain().addOrder(data1, data2);}
    public void printAllOrders() {System.out.print(transportController.getDomain().toStringOrders());}
    public void getItemInOrder(int idOrder) {System.out.println(this.transportController.getDomain().generateOrderReport(idOrder));}

    /// driver ///
    public void addDriver(Dictionary<String, String> data){this.transportController.getDomain().addDriver(data);}
    public void changeDriver(Dictionary<String, String> data){this.transportController.changeDriver(data);}
    public void seeAllTransportByDriver(int id) {System.out.println(transportController.getTransportByIdDriver(id));}

    /// truck ///
    public void addTruck(Dictionary<String, String> data){this.transportController.getDomain().addTruck(data);}
    public void changeTruck(Dictionary<String, String> data){this.transportController.changeTruck(data);}

    /// site ///
    public void addSite(Dictionary<String, String> data,String str) {this.transportController.getDomain().addSite(data,str);}
    public void changeDestination(Dictionary<String, String> data){this.transportController.getDomain().changeDestination(data);}
    public boolean checkAddress(String source) {return this.transportController.getDomain().isAddressSiteAlreadyIn(source);}

    /// transport ///
    public int addTransport(Dictionary<String, String> data) {return this.transportController.addTransport(data);}
    public void printAllOrdersByTransport(Dictionary<String, String> data){System.out.println(this.transportController.printAllOrdersByTransportID(data));}
    public void getTransportReport(int transportId) {System.out.println(transportController.generateTransportReport(transportId));}
    public void printAllTransports(){System.out.println(transportController.printAllTransport());}
    public void addOrderToTransport(Dictionary<String, String> data){transportController.addOrderToTransport(data);}

    public void printAllTrucks() {
        System.out.println(transportController.getDomain().toStringTrucks());
    }

    public String getTypeOfLicense(int idT) {
        return transportController.getDomain().getTruckByID(idT).getTypeOfLicense();
    }

    public void printallDriversByLicense(String licenseType)
    {
        System.out.println(transportController.getDomain().printDriversByLicenseType(licenseType));
    }

    public boolean loadOrderToTruck(Dictionary<String, String> data) {
        return transportController.loadOrderToTruck(data);
    }

    public boolean treatmentWeightProblemChangeTruck(Dictionary<String, String> data) {
       return transportController.treatmentWeightProblemChangeTruck(data);
    }

    public void printOrder(Dictionary<String, String> data)
    {
        System.out.println(transportController.getDomain().printOrder(data));
    }

    public boolean UnloadingItems(Dictionary<String, String> data) {
        return transportController.treatmentWeightProblemUnloadingItems(data);

    }
}
