package Presentation;

import Domain.DomainController;
import Domain.Order;
import Domain.Transport;
import Domain.TransportController;

import java.util.ArrayList;
import java.util.Dictionary;

public class PresentationController {

    //private DomainController domainController;
    private TransportController transportController;


    public PresentationController()
    {
        //this.domainController = new DomainController();
        this.transportController = new TransportController();
    }

    /// order + item ///
    public Order createNewOrder() {return this.transportController.getDomain().createNewOrderDomain();}
    public int creatNewOrder(Dictionary<String, String> data1, Dictionary<Integer, ArrayList<String>> data2) {return this.transportController.getDomain().addOrder(data1, data2);}
    public void printAllOrders()
    {
//        System.out.println("Presentation controller");
//        System.out.println(this.domainController.printAllOrders());
        System.out.print(transportController.getDomain().toStringOrders());
                //this.domainController.printAllOrders();

    }
    public void getItemInOrder(int idOrder)
    {
        this.transportController.getDomain().generateOrderReport(idOrder);
    }



    /// driver ///
    public void addDriver(Dictionary<String, String> data){this.transportController.getDomain().addDriver(data);}
    public void changeDriver(Dictionary<String, String> data){this.transportController.changeDriver(data);}
    public void seeAllTransportByDriver(int id)
    {
        transportController.getTransportByIdDriver(id);
    }


    /// truck ///
    public void addTruck(Dictionary<String, String> data){this.transportController.getDomain().addTruck(data);}
    public void changeTruck(Dictionary<String, String> data){this.transportController.changeTruck(data);}

    /// site ///
    public void addSite(Dictionary<String, String> data){this.transportController.getDomain().addSite(data);}
    public void changeDestination(Dictionary<String, String> data){this.transportController.getDomain().changeDestination(data);}
    public boolean checkAddress(String source) {return this.transportController.getDomain().isAddressSiteAlreadyIn(source);}

    /// transport ///
    public int addTransport(Dictionary<String, String> data) {return this.transportController.addTransport(data);}
    public void printAllOrdersByTransport(Dictionary<String, String> data){this.transportController.printAllOrdersByTransportID(data);}
    public void getTransportReport(int transportId)
    {
        System.out.println(transportController.generateTransportReport(transportId));
    }
    public Transport createNewTransport()
    {
        return this.transportController.createNewTransport();
    }








}
