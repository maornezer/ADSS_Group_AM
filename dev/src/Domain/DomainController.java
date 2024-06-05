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

    private static DomainController instance;

    public DomainController()
    {
        this.drivers = new ArrayList<>();
        this.allOrders = new ArrayList<>();
        this.sites = new HashMap<>();
        this.trucks = new ArrayList<>();

        instance = this;
    }
    public static DomainController getInstance()
    {
        if(instance == null)
            instance = new DomainController();
        return instance;
    }

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

    public boolean addOrder(Dictionary<String,String> data, Dictionary<String,Dictionary<String,String>> dataItems)
    {
        LocalTime time = LocalTime.of(Integer.parseInt(data.get("hour")),Integer.parseInt(data.get("minute")));
        LocalDate date = LocalDate.of(Integer.parseInt(data.get("year")),Integer.parseInt(data.get("month")),Integer.parseInt(data.get("day")));
        String destination = data.get("destination");
        String source = data.get("source");
        Site destinationSite = getSiteByAddress(destination);
        Site sourceSite = getSiteByAddress(source);

        ArrayList<Item> orderItems = new ArrayList<>();
        int size = dataItems.size();
        for (int i = 0; i < size ; i++)
        {

            int id = Integer.parseInt((dataItems.get("item_"+ i)).get("id" + i));
            String name = (dataItems.get("item_"+ i).get("name" + i));
            int amount = Integer.parseInt((dataItems.get("item_"+ i)).get("amount" + i));
            Item newItem = new Item(id, name, amount);
            orderItems.add(newItem);

        }
        return addOrder(time,date,destinationSite,sourceSite,orderItems);
    }
    public boolean addOrder(LocalTime time, LocalDate date, Site destination, Site source, ArrayList<Item> ordersItem)
    {
        Order order = new Order(time,date,destination,source, ordersItem);
        return allOrders.add(order);


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
            sites.put(site.getSiteZone(), tempArraySites);
        }
        tempArraySites.add(site);
        return true;
    }
    public boolean isSiteAlreadyIn(Site site)
    {
        ArrayList<Site> isIn = sites.get(site.getSiteZone());
        return isIn.contains(site);
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
                    if (site.getAddress().equals(address)) {
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
            if (truck.getIdTruck() == id) {
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
    public String printAllOrders()
    {
        StringBuilder sb = new StringBuilder();
        for (Order order: allOrders)
        {
            sb.append(order.toStringReport());
        }
        return sb.toString();
    }

}
