package Presentation;

import Domain.DomainController;
import Domain.TransportController;

import java.util.Dictionary;

public class PresentationController {

    private DomainController domainController;
    private TransportController transportController;


    public PresentationController()
    {
        this.domainController = new DomainController();
        this.transportController = new TransportController();
    }
    //methods that use the controllers to make changes in the system.
    // this controller will be saved in the menu and be used from there/.




    public void creatNewOrder(Dictionary<String, String> data, Dictionary<String, Dictionary<String, String>> dataItems){
        this.domainController.addOrder(data, dataItems);
    }
    public void addTransport(Dictionary<String, String> data)
    {
        this.transportController.addTransport(data);
    }

    public void addSite(Dictionary<String, String> data){
        this.domainController.addSite(data);
    }
    public void addTruck(Dictionary<String, String> data){
        this.domainController.addTruck(data);
    }

    public void addDriver(Dictionary<String, String> data){this.domainController.addDriver(data);}

    public void changeTruck(Dictionary<String, String> data){this.transportController.changeTruck(data);}

    public void changeDriver(Dictionary<String, String> data){this.transportController.changeDriver(data);}
    public void changeDestination(Dictionary<String, String> data){
        this.domainController.changeDestination(data);}
    public void printAllOrders() {this.domainController.printAllOrders();}
   public void printAllOrdersByTransport(Dictionary<String, String> data){
        this.transportController.printAllOrdersByTransportID(data);
   }


}
