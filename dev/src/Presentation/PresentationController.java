package Presentation;

import Domain.DomainController;
import Domain.Order;
import Domain.TransportController;

import java.util.ArrayList;
import java.util.Dictionary;

public class PresentationController
{

    private DomainController domainController;
    private TransportController transportController;


    public PresentationController()
    {
        this.domainController = new DomainController();
        this.transportController = new TransportController();
    }
    //methods that use the controllers to make changes in the system.
    // this controller will be saved in the menu and be used from there/.




    /// order  + item ///
    public Order createNewOrder() {return this.domainController.createNewOrderDomain();}
    public int creatNewOrder(Dictionary<String, String> data1, Dictionary<Integer, ArrayList<String>> data2)  {return this.domainController.addOrder(data1, data2);}
    public void printAllOrders() {this.domainController.printAllOrders();}
    public void printAllOrdersByTransport(Dictionary<String, String> data){this.transportController.printAllOrdersByTransportID(data);}

    /// Transport ///
    public void addTransport(Dictionary<String, String> data)
    {
        this.transportController.addTransport(data);
    }


    /// Site ///
    public void addSite(Dictionary<String, String> data){
        this.domainController.addSite(data);
    }
    public void changeDestination(Dictionary<String, String> data){ this.domainController.changeDestination(data);}
    public boolean checkAddress(String source) {return this.domainController.isAddressSiteAlreadyIn(source);}


    /// Truck ///
    public void addTruck(Dictionary<String, String> data){
        this.domainController.addTruck(data);
    }
    public void changeTruck(Dictionary<String, String> data){this.transportController.changeTruck(data);}


    /// Driver ///
    public void addDriver(Dictionary<String, String> data){this.domainController.addDriver(data);}
    public void changeDriver(Dictionary<String, String> data){this.transportController.changeDriver(data);}



}
