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
    public Shipping creatNewOrder(Shipping.ShippingZone shippingZone, LocalDate ShippingDate, LocalTime DepartureTime)
    {
        //need list of Items?
        Shipping s = new Shipping(ShippingId, shippingZone, ShippingDate,DepartureTime);
        ShippingId++;
        newShipments.add(s);
        // add to newShipments
        return s;
    }

    public Shipping addNewShipment(int shippingId)// this is for creating new shipping by shipping manager, Need to get list of items?????
    {
        Shipping s = getShipmentByID(shippingId);
        //getTruck and getDriver that mach to this truck
        //update weight truck by add the weight of all items in this order
        //check if weight truck <= maximum truck weight
        //if false(while) : errorShipments.add(s) && Managersolution()
        //if true: activeShipments.add(s) && update status for client

        return s;
    }
    public void creatShipment()
    {
        //in shippingManager in line 880
    }
    public boolean deleteShipment(int id) {
//        if (id<=0)
//        {
//            throw new IllegalArgumentException("id cannot be 0 or lower");
//        }
        if (ShipmentIDExist(id))
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
    public boolean ShipmentIDExist(int shippingId)
    {
        Shipping s = getShipmentByID(shippingId);
        return newShipments.contains(s) || errorShipments.contains(s) || activeShipments.contains(s);
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
//        if (shipping.status == Shipping.ShippingStatus.Done)
//        {
//            if(!completedShipments.contains(shipping))
//            {
//                return completedShipments.add(shipping) && activeShipments.remove(shipping);
//            }
//        }
//        if (shipping.status == Shipping.ShippingStatus.StandBy)
//        {
//            if (!activeShipments.contains(shipping))//and need func of cheks if the shipping is ok
//                return activeShipments.add(shipping) && newShipments.remove(shipping);
//        }
        return false;
    }
    public void ManagerSolution() {
        // change truck
        // change site
        // change items
    }
    //add 3 functions of change truck / site / remove items.

    public boolean isDriverCanDrive(Driver driver, int shippingId)
    {
        //if(driver == null) return false;
       Shipping s = getShipmentByID(shippingId);
        return s.getLicenseForThisTruck() == driver.getTypeOfLicense();

    }
    public void setShipmentDriver(int shippingId, Driver driver)
    {
        if (isDriverCanDrive(driver, shippingId))
        {
            Shipping S = getShipmentByID(shippingId);
            S.setDriver(driver);
        }
    }
    public void addShippingSource(int shippingId, String source)
    {
        Site s_s = SiteController.getInstance().getSite(source);
        if (getShipmentByID(shippingId) != null)
        {
            getShipmentByID(shippingId);
        }
    }

    public boolean addShippingDestination()
    {
        return false;

    }

    public boolean isTruckOverWeight(int shippingId)
    {
        return getShipmentByID(shippingId).getTruck() != null && getShipmentByID(shippingId).getTruck().getCurrWeight() > getShipmentByID(shippingId).getTruck().getMaxWeight();
    }

    public void editShippingDate(int id, LocalDate newDate)
    {
        getShipmentByID(id).setShippingDate(newDate);
    }

    public void editShippingTime(int id, LocalTime newTime)
    {
        getShipmentByID(id).setDepartureTime(newTime);
    }

    public int getAmountOfDestination()
    {
     return 0;
    }

    //check if we needed this func>??
//    public ArrayList<Shipping> getAllShippingBySite()
//    {
//
//    }
    //we need to add list of items to shipping and get the all items by id of shipping
    public void removeItemFromShipment(int shippingId, String itemName)
    {
        // need to add remove items from truck.
    }
    public double getShipmentTotalWeight(int shippingId)
    {
        Shipping shipping = getShipmentByID(shippingId);

        return shipping.getTruck().getCurrWeight();
    }
    public String getAllItemsInShipment(int shippingId)
    {
        return null;
    }


    public String getStatusOfShipment(int shippingId)
    {
        return null;// this func for branch manager
    }
    public int getAmountOfNewShipment(){return newShipments.size();}
    public int getAmountOfErrorShipment(){return errorShipments.size();}
    public int getAmountOfActiveShipment(){return activeShipments.size();}
    public int getAllActiveShipments(){return getAmountOfActiveShipment() + getAmountOfErrorShipment() + getAmountOfNewShipment();}
    private ArrayList<Shipping> getAllActiveShipmentByDate(LocalDate lDate) //if Shipping manager wants to no active shipment by date
    {
        ArrayList <Shipping> active_shipments = new ArrayList<>();
        for (Shipping s: activeShipments)
        {
            if (s.getShippingDate() == lDate)
            {
                active_shipments.add(s);
            }
        }
        return active_shipments;
    }
    public String getStatus(int shippingId)//if the branch manager wants to know status
    {
        String s = null;
        Shipping shipping = getShipmentByID(shippingId);
        Shipping.ShippingStatus status = shipping.getStatus();
        if(!ShipmentIDExist(shippingId))
        {
            s = "There is no order with such an order number.";
        }
        else if (status == Shipping.ShippingStatus.Done)
        {
            s = "Shipment is completed";
        }

//          s = "Shipment currently in progress!"
        return s;
    }
    public String[][] getShipmentItemInfo(int shippingId) {
        Shipping s = getShipmentByID(shippingId);
        return null;//s.invManager.getItemsInfo();
    }
    ///we need to add functions that print all information about the Document items and document shipping

}