package Presentation;

import Domain.ShippingController;
import Domain.SiteController;
import Domain.Truck;
import Domain.TruckController;

import java.util.Dictionary;

public class PresentationController {

    private SiteController siteController;
    private TruckController truckController;
    private ShippingController shippingController;


    public PresentationController()
    {
        this.siteController = new SiteController();
        this.shippingController = new ShippingController();
        this.truckController = new TruckController();
    }
    //methods that use the controllers to make changes in the system.
    // this controller will be saved in the menu and be used from there/.




//    public void creatNewOrder(Dictionary<String, String> data){
//        this.shippingController.creatNewOrder(data);
//    }
    public void addTruck(Dictionary<String, String> data){
        this.truckController.addTruck(data);
    }
}
