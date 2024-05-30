package Domain;

import java.util.ArrayList;

public class ShippingController {
    private static int ShippingId = 1;
    private ArrayList<Shipping> newShipments;
    private ArrayList<Shipping> errorShipments;

    private ArrayList<Shipping> activeShipments;
    private ArrayList<Shipping> completedShipments;


    public Shipping addNewShipment() {
        Shipping shipment = new Shipping(ShippingId);
        ShippingId++;
        // add to newShipments
        return shipment;
    }

    public boolean deleteShipment(int id) {
        Shipping shipment = getShipmentID(id);
    }

    public Shipping getShipmentID(int id) {

    }

    public boolean switchStatus(Shipping shipment) {

    }

    public void Managersolution() {
        // change truck
        // change site
        // change items
    }

    public boolean isDriverCanDrive() {

    }

    public void setShippingSource() {

    }

    public boolean addShippingDestination() {

    }
    public boolean isTruckOverweight()
    {

    }

}