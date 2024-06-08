package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DomainController {
    private List<Driver> drivers;
    private ArrayList<Order> allOrders;
    private ArrayList<Site> sites;
    private List <Truck> trucks;


    public DomainController() {
        this.drivers = new ArrayList<>();
        this.allOrders = new ArrayList<>();
        this.sites = new ArrayList<>();
        this.trucks = new ArrayList<>();
    }


/// driver ///
    public boolean addDriver(String name, int id, String typeOfLicense) {
        Driver d = new Driver(name, id, typeOfLicense);
        return addDriver(d);
    }

    public boolean addDriver(Dictionary<String, String> data) {
        String name = data.get("name");
        int id = Integer.parseInt(data.get("id"));
        String typeOfLicense = data.get("typeOfLicense");
        Driver newDriver = new Driver(name,id,typeOfLicense);
        return addDriver(newDriver);
    }

    public boolean addDriver(Driver d) {
        if (isDriverExists(d)) {
            System.out.println("RON");
            return false;
        }
        return drivers.add(d);
    }

    public boolean isDriverExists(Driver d)
    {
        return drivers.contains(d);
    }

    public boolean isDriverExists(String type)
    {
        if (drivers !=null)
        {
            for (Driver d : drivers)
            {
                if (d.getTypeOfLicense().compareTo(type)==0)
                {
                    return true;
                }

            }
        }
        return false;
    }

    public Driver getDriverByID(int id) {
        for (Driver d : drivers) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }
    public int getAmountOfDrivers(){return drivers.size();}

    public List<Driver> getDrivers() {
        return drivers;
    }

    public String toStringDrivers() {
        StringBuilder sb = new StringBuilder();
        for (Driver driver : drivers) {
            sb.append(driver.toString()).append("\n");
        }
        return sb.toString();
    }
    public String printDriversByLicenseType(String licenseType) {
        StringBuilder sb = new StringBuilder();
        for (Driver driver : drivers) {
            if (driver.getTypeOfLicense().equalsIgnoreCase(licenseType)) {
                sb.append(driver.toString()).append("\n");
            }
        }
        if (sb.length() == 0)
        {
            System.out.println("There are no drivers with a license type in the system " + licenseType);
        }
        return sb.toString();
    }


/// order + item ///

    public boolean validMatchZone(String source, String destination )
    {
        Site s1 = getSiteByAddress(source);
        Site s2 = getSiteByAddress(destination);
        if (s1!=null && s2!=null)
        {
            return s1.getSiteZone().compareTo(s2.getSiteZone())==0;
        }
        return false;
    }
    public Item addItem(ArrayList<String> item) {
        int id = Integer.parseInt(item.get(0));
        String name = item.get(1);
        int amount = Integer.parseInt(item.get(2));
        Item newItem = new Item(id, name, amount);
        return newItem;
    }

    public int addOrder(Dictionary<String,String> data1,  Dictionary<Integer, ArrayList<String>> data2) {
        LocalDate date = LocalDate.of(Integer.parseInt(data1.get("year")),Integer.parseInt(data1.get("month")),Integer.parseInt(data1.get("day")));
        String destination = data1.get("destination");
        String source = data1.get("source");
        Site destinationSite = getSiteByAddress(destination);
        Site sourceSite = getSiteByAddress(source);
        if(destinationSite == null)
        {
            System.out.println("The address of the destination: "+ destination+" is not registered in the system");
            return -2;
        }
        if(source == null)
        {
            System.out.println("The address of the source is not registered in the system");
            return -1;
        }
        ArrayList<Item> orderItems = new ArrayList<>() ;
        for (Map.Entry<Integer, ArrayList<String>> key : ((Hashtable<Integer, ArrayList<String>>) data2).entrySet()) {
            ArrayList<String> itemData = key.getValue();
            Item item = addItem(itemData);
            orderItems.add(item);
        }
        Order newOrder = new Order(date,destinationSite,sourceSite,orderItems);
        if (newOrder != null)
            allOrders.add(newOrder);
        //System.out.println("Your order number is: "+ newOrder.getId());
        return newOrder.getId();
    }

    public Order getOrderByID(int id) {
        for (Order order : allOrders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public String generateOrderReport(int orderId) {
        Order order = getOrderByID(orderId);
        StringBuilder sb = new StringBuilder();
        if (order != null) {
            sb.append(order.toStringReport());
            return sb.toString();
        }
        return "Order with ID " + orderId + " not found.";
    }
    public String toStringOrders() {
        StringBuilder result = new StringBuilder();
        for (Order order : allOrders) {
            result.append(order.toStringReport()).append("\n");
        }
        return result.toString();
    }
//    public void printAllOrders()
//    {
//        for (Order order: allOrders)
//        {
//            order.toStringReport();
//        }
////        System.out.println("domain controller");
////        System.out.println(sb.toString());
//    }

/// site ///
//    public boolean addNewSite(String address, String zone, String contactName, String phoneNumber) {
//        if (address == null) {
//            return false;
//        }
//        Site newSite = new Site(address, zone, contactName, phoneNumber);
//        return  addSite(newSite,"domain");
//    }
    public boolean changeDestination(Dictionary<String, String> data) {
        int id = Integer.parseInt(data.get("id"));
        String newAddress = data.get("destination");
        return changeDestination(id, newAddress);
    }
    public boolean changeDestination(int orderID,String address ) {
        if(isAddressSiteAlreadyIn(address)) {
            Order orderTemp = getOrderByID(orderID);
            if(getSiteByAddress(address).getSiteZone().compareTo(orderTemp.getDestination().getSiteZone()) != 0 ) {
                return false;
            }
            orderTemp.setDestination(getSiteByAddress(address));
            return true;
        }
        return false;
    }
    public boolean addSite(Dictionary<String, String> data,String str) {
        String address = data.get("address");
        String zone = data.get("zone");
        String contactName = data.get("contactName");
        String phoneNumber = data.get("phoneNumber");
        Site newSite = new Site(address, zone, contactName, phoneNumber);
        if (str.compareTo("csv") != 0) {
            if (isSiteAlreadyIn(newSite)) {
                return false;
            }
        }
        return addSiteToList(newSite);
    }
    public boolean addSiteToList(Site site) {return sites.add(site);}

    public boolean isSiteAlreadyIn(Site site) {
        for (Site s : sites)
        {
            if (s.getAddress().equals(site.getAddress()))
            {
                return true;
            }
        }
        return false;
    }


    public boolean isAddressSiteAlreadyIn(String address) {
        Site site = getSiteByAddress(address);
        return site != null;
    }

    public Site getSiteByAddress(String address)
    {
        for (Site site : sites)
        {
            if (site.getAddress().compareTo(address)==0)
                return site;
        }
        return null;
    }
    public String printAllAddress() {
        StringBuilder result = new StringBuilder();
        for (Site site : sites) {
            result.append(site.getAddress()).append(" - " + site.getSiteZone()).append("\n");
        }
        return result.toString();
    }
    public String toString(String zone) {
        StringBuilder result = new StringBuilder();
        for (Site site : sites) {
            if(site.getSiteZone().compareTo(zone)==0)
                result.append(site.toString()).append("\n");
        }
        return result.toString();
    }

/// truck /////
    public boolean addTruck(int idT,double initialWeight, double maxWeight, String model) {
        Truck t = new Truck( idT,  initialWeight,  maxWeight,  model);
        return addTruck(t);
    }

    public boolean addTruck(Dictionary<String, String> data) {
        int idT = Integer.parseInt(data.get("idT"));
        double initialWeight = Double.parseDouble(data.get("initialWeight"));
        double maxWeight = Double.parseDouble(data.get("maxWeight"));
        String model = data.get("model");
        Truck t = new Truck( idT,  initialWeight,  maxWeight,  model);
        return addTruck(t);
    }

    public boolean addTruck(Truck t) {
        if (isTruckExists(t))
        {
            return false;
        }
        return trucks.add(t);
    }

    public boolean isTruckExists(Truck truck) {
        return trucks.contains(truck);
    }

//    public Truck getTruckByLicenceType(TypeOfLicense type )
//    {
//        for (Truck truck : trucks)
//        {
//            if (truck.getTypeOfLicense().equals(type)) {
//                return truck;
//            }
//        }
//        return null;
//    }

    public Truck getTruckByID(int id) {
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id) {
                return truck;
            }
        }
        return null;
    }
    public int getAmountOfTrucks(){return trucks.size();}

    public List<Truck> getTrucks() {return trucks;}

    public String toStringTrucks() {
        StringBuilder allTrucksInfo = new StringBuilder();
        for (Truck truck : trucks) {
            allTrucksInfo.append(truck.toString()).append("\n");
        }
        return allTrucksInfo.toString();
    }
    public String printTruckByID(int id) {
        Truck truck = getTruckByID(id);
        if (truck != null) {
            return truck.toString();
        }

        return "Truck with ID " + id + " not found.";
    }

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public String printOrder(Dictionary<String, String> data)
    {
        int orderId = Integer.parseInt(data.get("orderID"));
        Order getOrder = getOrderByID(orderId);
        StringBuilder sb = new StringBuilder();
        sb.append(getOrder.toStringReport());
        return sb.toString();

    }
}
