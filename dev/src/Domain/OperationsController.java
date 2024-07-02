package Domain;

import java.time.LocalDate;
import java.util.*;

public class OperationsController {
    private SiteRepository siteRepo;
    private OrderRepository orderRepo;
    private ItemRepository itemRepo;

    public OperationsController()
    {
        siteRepo = new SiteRepository();
        orderRepo = new OrderRepository();
    }

    //**********************SITE:**********************//
    public boolean addSite(Dictionary<String, String> data,String str) {
        int siteID = Integer.parseInt(data.get("siteID"));//להוסיף את המפתח הזה ב-menu
        String address = data.get("address");
        String zone = data.get("zone");
        String contactName = data.get("contactName");
        String phoneNumber = data.get("phoneNumber");
        Site newSite = new Site(address, zone, contactName, phoneNumber);
        if (str.compareTo("csv") != 0) {
            if (searchSite(siteID)) {
                return false;
            }
            Site site = siteRepo.get(siteID);
        }
        return siteRepo.insert(newSite);
    }
    public boolean removeSite(int id) {
        if (!searchSite(id))
            return false;
        return siteRepo.remove(id);
    }
    public boolean searchSite(int idSite){return siteRepo.search(idSite);}

    public Site getSite(int id){
        if (!searchSite(id)){
            return null;
        }
        return siteRepo.get(id);
    }
    public int sizeOfSites(){return siteRepo.getSizeSites();}

    public boolean isAddressSiteAlreadyIn(String address) {return siteRepo.isAddressSiteAlreadyIn(address);}//לשנות את הפונקציות שמשתמשות בזה

    public Site getSiteByAddress(String address)
    {
        return siteRepo.getSiteByAddress(address);
    }
    public String printAllAddress() {
        StringBuilder result = new StringBuilder();
        for (Site site : siteRepo.getSites()) {
            result.append(site.getAddress()).append("\n");
        }
        return result.toString();
    }
    public String toString(String zone) {
        StringBuilder result = new StringBuilder();
        for (Site site : siteRepo.getSites()) {
            if(site.getSiteZone().compareTo(zone)==0)
                result.append(site.toString()).append("\n");
        }
        return result.toString();
    }
    public boolean validMatchZone(String source, String destination )
    {
        return siteRepo.validMatchZone(source,destination);
    }
    //**********************ORDER:**********************//

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
            //צריך להוסיף את הidorder לטבלת ה-items////////////////
        }
        Order newOrder = new Order(date,destinationSite,sourceSite,orderItems);
        orderRepo.insert(newOrder);
        //if (newOrder != null)
            //allOrders.add(newOrder);
        return newOrder.getId();
    }
    public boolean remove(int id) {
        if (!searchOrder(id))
            return false;
        return orderRepo.remove(id);
    }
    public boolean searchOrder(int id) {return orderRepo.search(id);}
    public Order getOrder(int id) {
        if(!searchOrder(id))
        {
            return null;
        }
        Order order = orderRepo.get(id);
        Site source = siteRepo.get(order.getSourceID());
        Site destination = siteRepo.get(order.getDestinationID());
        order.setSource(source);
        order.setDestination(destination);
        List<Integer> items = itemRepo.getItemsByOrderId(id);
        ArrayList<Item> itemsOfOrder = new ArrayList<>();
        if(items != null)
        {
            for (Integer itemID: items)
            {
                Item item = itemRepo.get(itemID);
                itemsOfOrder.add(item);
            }
            order.setItems(itemsOfOrder);
            orderRepo.insert(order);
            return order;
        }
        return null;
    }
    public boolean changeDestination(Dictionary<String, String> data) {
        int id = Integer.parseInt(data.get("id"));
        String newAddress = data.get("destination");
        return changeDestination(id, newAddress);
    }
    public boolean changeDestination(int orderID,String address ) {
        if(isAddressSiteAlreadyIn(address)) {
            Order orderTemp = getOrder(orderID);
            if(getSiteByAddress(address).getSiteZone().compareTo(orderTemp.getDestination().getSiteZone()) != 0 ) {
                return false;
            }
            orderTemp.setDestination(getSiteByAddress(address));
            return true;
        }
        return false;
    }
    ////to String for order
    public String generateOrderReport(String orderId) {

        Order order = getOrder(Integer.parseInt(orderId));
        StringBuilder sb = new StringBuilder();
        if (order != null) {
            sb.append(order.toStringReport());
            return sb.toString();
        }
        return "Order with ID " + orderId + " not found.";
    }
    public String toStringOrders() {
        StringBuilder result = new StringBuilder();
        for (Order order : orderRepo.getAllOrders()) {
            result.append(order.toStringReport()).append("\n");
        }
        return result.toString();
    }
    public String printOrder(Dictionary<String, String> data)
    {
        int orderId = Integer.parseInt(data.get("orderID"));
        Order getOrder = getOrder(orderId);
        StringBuilder sb = new StringBuilder();
        sb.append(getOrder.toStringReport());
        return sb.toString();

    }

    //public int getSizeOrders() {return orderRepo.countRecords();}
    public int getSizeOrders() {
        return orderRepo.getSizeOrders();
    }
    //**********************ITEM:**********************//
    public Item addItem(ArrayList<String> item) {
        int id = Integer.parseInt(item.get(0));
        String name = item.get(1);
        int amount = Integer.parseInt(item.get(2));
        Item newItem = new Item(id, name, amount);
        return newItem;
    }





}
