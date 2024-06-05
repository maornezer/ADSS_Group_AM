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
    public void addTruck(Dictionary<String, String> data){
        this.domainController.addTruck(data);
    }
}
