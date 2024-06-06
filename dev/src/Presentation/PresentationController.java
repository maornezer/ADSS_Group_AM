package Presentation;

import Domain.DomainController;
import Domain.Order;
import Domain.TransportController;

import java.util.ArrayList;
import java.util.Dictionary;

public class PresentationController {

    private DomainController domainController;
    private TransportController transportController;


    public PresentationController()
    {
        this.domainController = new DomainController();
        this.transportController = new TransportController();
    }

    /// order + item ///
    public Order createNewOrder() {return this.domainController.createNewOrderDomain();}
    public int creatNewOrder(Dictionary<String, String> data1, Dictionary<Integer, ArrayList<String>> data2) {return this.domainController.addOrder(data1, data2);}
    public void printAllOrders() {this.domainController.printAllOrders();}


    /// driver ///
    public void addDriver(Dictionary<String, String> data){this.domainController.addDriver(data);}
    public void changeDriver(Dictionary<String, String> data){this.transportController.changeDriver(data);}

    /// truck ///
    public void addTruck(Dictionary<String, String> data){this.domainController.addTruck(data);}
    public void changeTruck(Dictionary<String, String> data){this.transportController.changeTruck(data);}

    /// site ///
    public void addSite(Dictionary<String, String> data){this.domainController.addSite(data);}
    public void changeDestination(Dictionary<String, String> data){this.domainController.changeDestination(data);}
    public boolean checkAddress(String source) {return this.domainController.isAddressSiteAlreadyIn(source);}

    /// transport ///
    public int addTransport(Dictionary<String, String> data) {return this.transportController.addTransport(data);}
    public void printAllOrdersByTransport(Dictionary<String, String> data){this.transportController.printAllOrdersByTransportID(data);}








}
