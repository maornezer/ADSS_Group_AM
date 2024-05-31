package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ShippingController {
    private static int ShippingId = 1;
    private ArrayList<Shipping> newShipments;
    private ArrayList<Shipping> errorShipments;
    private ArrayList<Shipping> activeShipments;
    private ArrayList<Shipping> completedShipments;
    private static ShippingController instance;
    private TruckController tm;

    private ShippingController() {
        newShipments = new ArrayList<>();
        errorShipments = new ArrayList<>();
        activeShipments = new ArrayList<>();
        completedShipments = new ArrayList<>();
        tm = TruckController.getInstance();
        instance = this;

//        Shipping newShipping = new Shipping(ShippingId);
//        ShippingId++;
    }
    public static ShippingController getInstance()
    {
        if (instance == null)
            instance = new ShippingController();
        return instance;
    }

    //A new order made by a branch manager
    public Shipping addNewShipment(Shipping.ShippingZone shippingZone, LocalDate ShippingDate, LocalTime DepartureTime) {
        Shipping s = new Shipping(ShippingId, shippingZone, ShippingDate,DepartureTime);
        ShippingId++;
        newShipments.add(s);
        // add to newShipments
        return s;
    }

    //public Shipping addInfoToNewShipment(int shippingId) - this is for creating new shipping by Transport manager.
    public boolean deleteShipment(int id) {
//        if (id<=0)
//        {
//            throw new IllegalArgumentException("id cannot be 0 or lower");
//        }
        if ((ShipmentIDExist(newShipments,id)) || ShipmentIDExist(errorShipments, id) || ShipmentIDExist(activeShipments, id) || ShipmentIDExist(completedShipments, id))
        {
            Shipping s = getShipmentByID(id);
            if (newShipments.contains(s))
            {
                return newShipments.remove(s);
            }
            if (errorShipments.contains(s))
            {
                return errorShipments.remove(s);
            }
            if (activeShipments.contains(s))
            {
                return activeShipments.remove(s);
            }
        }
        return false;
//        throw new IllegalArgumentException("no shipment with id: "+id + " was found");
    }
    public boolean ShipmentIDExist(ArrayList<Shipping> arrayList, int shippingId)
    {
        for (Shipping shipment : arrayList)
        {
            if(shippingId == shipment.getShippingID())
                return true;
        }
        return false;
    }



    public Shipping getShipmentByID(int id)
    {
        //if (id <= 0)
            //throw new IllegalArgumentException("id cannot be 0 or lower");
        for (Shipping shipment : newShipments)
        {
            if(id == shipment.getShippingID())
                return shipment;
        }
        for (Shipping shipment : completedShipments)
        {
            if(id == shipment.getShippingID())
                return shipment;
        }
        for (Shipping shipment : errorShipments)
        {
            if(id == shipment.getShippingID())
                return shipment;
        }
        for (Shipping shipment : activeShipments)
        {
            if(id == shipment.getShippingID())
                return shipment;
        }
        return null;
    }


//    public boolean switchStatus(Shipping shipment)
//    {
//
//    }
    public boolean updateStatus(Shipping shipping)
    {
        if (shipping.status == Shipping.ShippingStatus.Done)
        {
            if(!completedShipments.contains(shipping))
            {
                return completedShipments.add(shipping) && activeShipments.remove(shipping);
            }
        }
        if (shipping.status == Shipping.ShippingStatus.StandBy)
        {
            if (!activeShipments.contains(shipping))//and need func of cheks if the shipping is ok
                return activeShipments.add(shipping) && newShipments.remove(shipping);
        }
        return false;
    }
    public void Managersolution() {
        // change truck
        // change site
        // change items
    }

    public boolean isDriverCanDrive()
    {
        return false;
    }

    public void setShippingSource() {

    }

    public boolean addShippingDestination() {
        return false;

    }
    public boolean isTruckOverweight()
    {
        return false;
    }

    public void editShippingDate(int id, LocalDate newDate)
    {
        getShipmentByID(id).setShippingDate(newDate);
    }

    public void editShippingTime(int id, LocalTime newTime)
    {
        getShipmentByID(id).setDepartureTime(newTime);
    }



}