package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DomainController
{
    private List<Driver> drivers;
    private ArrayList<Order> allOrders;
    private HashMap<String, ArrayList<Site>> sites;
    private List <Truck> trucks;

    //private static DomainController instance;

    public DomainController()
    {
        this.drivers = new ArrayList<>();
        this.allOrders = new ArrayList<>();
        this.sites = new HashMap<>();
        this.trucks = new ArrayList<>();

        //instance = this;
    }
//    public static DomainController getInstance()
//    {
//        if(instance == null)
//            instance = new DomainController();
//        return instance;
//    }

    public boolean addDriver(String name, int id, String typeOfLicense)
    {
        Driver d = new Driver(name, id, typeOfLicense);
        return addDriver(d);
    }

    public boolean addDriver(Dictionary<String, String> data)
    {
        String name = data.get("name");
        int id = Integer.parseInt(data.get("id"));
        String typeOfLicense = data.get("typeOfLicense");
        Driver newDriver = new Driver(name,id,typeOfLicense);
        return addDriver(newDriver);
    }

    public boolean addDriver(Driver d)
    {
        if (isDriverExists(d))
        {
            System.out.println("RON");
            return false;
        }
        return drivers.add(d);
    }

    public boolean isDriverExists(Driver d)
    {
        return drivers.contains(d);
    }


    public Driver getDriverByID(int id)
    {
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

    public String toStringDrivers()
    {
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
        return sb.toString();
    }
    ///// Order /////


    public Order createNewOrderDomain()
    {
        Order order = new Order();
        allOrders.add(order);
        return order;
    }





    public Item addItem(ArrayList<String> item)
    {
        int id = Integer.parseInt(item.get(0));
        String name = item.get(1);
        int amount = Integer.parseInt(item.get(2));
        Item newItem = new Item(id, name, amount);
        return newItem;
    }



    public int addOrder(Dictionary<String,String> data1,  Dictionary<Integer, ArrayList<String>> data2)
    {
        int orderID = Integer.parseInt(data1.get("orderID"));
        LocalDate date = LocalDate.of(Integer.parseInt(data1.get("year")),Integer.parseInt(data1.get("month")),Integer.parseInt(data1.get("day")));
        String destination = data1.get("destination");
        String source = data1.get("source");
        Site destinationSite = getSiteByAddress(destination);
        Site sourceSite = getSiteByAddress(source);
        if(destinationSite == null)
        {
            //System.out.println("Domain controller");
            System.out.println("The address of the destination is not registered in the system");
            return -2;
        }
        if(source == null)
        {
            //System.out.println("Domain controller");
            System.out.println("The address of the source is not registered in the system");
            return -1;
        }
        ArrayList<Item> orderItems = new ArrayList<>() ;
        ///
        for (Map.Entry<Integer, ArrayList<String>> key : ((Hashtable<Integer, ArrayList<String>>) data2).entrySet())
        {
            ArrayList<String> itemData = key.getValue();
            Item item = addItem(itemData);
            orderItems.add(item);
        }
        if (!addOrder(orderID,date,destinationSite,sourceSite,orderItems))
            return -3 ;

        return 0;
    }


    public boolean addOrder(int orderId,LocalDate date, Site destination, Site source, ArrayList<Item> ordersItem)
    {
        if(source.getSiteZone().compareTo(destination.getSiteZone())!=0)
        {
            System.out.println("Source zone and destination zone are not match");
            return false;

        }
        Order order = getOrderByID(orderId);
        if (order != null)
        {
            //allOrders.add(order);
            order.createOrder(date,destination,source, ordersItem);
            return true;
        }

        System.out.println("This order " + orderId + "not in the system");
        return false;


    }
    public boolean changeDestination(Dictionary<String, String> data)
    {
        int id = Integer.parseInt(data.get("id"));
        String newAddress = data.get("destination");
        return changeDestination(id, newAddress);
    }

    public Order getOrderByID(int id)
    {
        for (Order order : allOrders)
        {
            if (order.getId() == id)
            {
                return order;
            }
        }
        return null;
    }
    public boolean changeDestination(int orderID,String address )
    {
        if(isAddressSiteAlreadyIn(address))
        {
            Order orderTemp = getOrderByID(orderID);
            if(getSiteByAddress(address).getSiteZone().compareTo(orderTemp.getDestination().getSiteZone()) != 0 )
            {
                return false;
            }
            orderTemp.setDestination(getSiteByAddress(address));
            return true;
        }
        return false;
    }
    public String generateOrderReport(int orderId) {
        Order order = getOrderByID(orderId);
        if (order != null) {
            return order.toString();
        }
        return "Order with ID " + orderId + " not found.";
    }

    //// Site ////
    public boolean addNewSite(String address, String zone, String contactName, String phoneNumber) {
        if (address == null) {
            return false;
        }
        Site newSite = new Site(address, zone, contactName, phoneNumber);
        return  addSite(newSite);
    }
    public boolean addSite(Dictionary<String, String> data)
    {
        String address = data.get("address");
        String zone = data.get("zone");
        String contactName = data.get("contactName");
        String phoneNumber = data.get("phoneNumber");
        Site newSite = new Site(address, zone, contactName, phoneNumber);
        return addSite(newSite);


    }
    public boolean addSite(Site site) {
        if (isSiteAlreadyIn(site)) {
            return false;
        }
        ArrayList<Site> tempArraySites = sites.get(site.getSiteZone());
        if (tempArraySites == null)
        {
            tempArraySites = new ArrayList<>();
            tempArraySites.add(site);
            sites.put(site.getSiteZone(), tempArraySites);
        }
        return true;
    }
    public boolean isSiteAlreadyIn(Site site)
    {
        ArrayList<Site> isIn = sites.get(site.getSiteZone());

        if (isIn == null)
            return false;
        else
            return isIn.contains(site) ;
    }


    public boolean isAddressSiteAlreadyIn(String address)
    {
        Site site = getSiteByAddress(address);
        return site != null;
    }


    public Site getSiteByAddress(String address) {
        for (String zone : sites.keySet())
        {
            ArrayList<Site> sitesInZone = sites.get(zone);
            if (sitesInZone != null) {
                for (Site site : sitesInZone) {
                    if (site.getAddress().trim().compareTo(address.trim())==0)
                    {
                        return site;
                    }
                }
            }
        }
        return null;
    }
    public String toStringSites()
    {
        StringBuilder result = new StringBuilder();
        for (String zone : sites.keySet()) {
            ArrayList<Site> sitesInZone = sites.get(zone);
            if (sitesInZone != null && !sitesInZone.isEmpty()) {
                result.append("Zone: ").append(zone).append("\n");
                for (Site site : sitesInZone) {
                    result.append(site.toString()).append("\n");
                }
            }
        }
        return result.toString();
    }
    public String toString(String zone) {
        StringBuilder result = new StringBuilder();
        ArrayList<Site> sitesInZone = sites.get(zone);
        if (sitesInZone == null || sitesInZone.isEmpty()) {
            result.append("No sites found in the zone: ").append(zone).append("\n");
        } else {
            result.append("Sites in the zone: ").append(zone).append("\n");
            for (Site site : sitesInZone) {
                result.append(site.toString()).append("\n");
            }
        }
        return result.toString();
    }


    //// truck //////
    public boolean addTruck(int idT,double initialWeight, double maxWeight, String model)
    {
        Truck t = new Truck( idT,  initialWeight,  maxWeight,  model);
        return addTruck(t);
    }

    public boolean addTruck(Dictionary<String, String> data)
    {
        int idT = Integer.parseInt(data.get("idT"));
        double initialWeight = Double.parseDouble(data.get("initialWeight"));
        double maxWeight = Double.parseDouble(data.get("maxWeight"));
        String model = data.get("model");
        Truck t = new Truck( idT,  initialWeight,  maxWeight,  model);
        return addTruck(t);
    }

    public boolean addTruck(Truck t)
    {
        if (isTruckExists(t))
        {
            return false;
        }
        return trucks.add(t);
    }

    public boolean isTruckExists(Truck truck)
    {
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

    public Truck getTruckByID(int id)
    {
        for (Truck truck : trucks) {
            if (truck.getIdTruck() == id)
            {
                return truck;
            }
        }
        return null;
    }
    public int getAmountOfTrucks(){return trucks.size();}

    public List<Truck> getTrucks() {
        return trucks;
    }

    public String toStringTrucks()
    {
        StringBuilder allTrucksInfo = new StringBuilder();
        for (Truck truck : trucks) {
            allTrucksInfo.append(truck.toString()).append("\n");
        }
        return allTrucksInfo.toString();
    }
    public String printTruckByID(int id) {
        Truck truck = getTruckByID(id);
        if (truck != null)
        {
            return truck.toString();
        }

        return "Truck with ID " + id + " not found.";
    }
    public String printDriversByLicenseType(Truck truck) {
        StringBuilder sb = new StringBuilder();
        for (Driver driver : drivers) {
            if (driver.getTypeOfLicense().equalsIgnoreCase(truck.getTypeOfLicense())) {
                sb.append(driver.toString()).append("\n");
            }
        }
        return sb.toString();
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

}
