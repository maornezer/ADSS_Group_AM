package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class DeliveryManager
{
    //The list of sites in order of delivery.
    // The sites in the list represent the stations on the shipping route from the source to the final destinations.
    ArrayList<Site> shipmentRoute;
    //key - site
    //value - hashtable ( key - item name, item amount)
    //This table is used to manage the inventory of items on each site.
    //Each site contains a table that indicates the amount of items.
    Hashtable<Site, Hashtable<String,Integer>> siteInventoryMap;
    Hashtable<String,Double> itemWeightMap;
    //A table that maps each item name to its weight.
    //This table is used to manage the weight of each item, which is important for calculating the total shipping weight and managing the truck load.
    public DeliveryManager() {
        this.shipmentRoute = new ArrayList<>();
        this.siteInventoryMap = new Hashtable<>();
        this.itemWeightMap = new Hashtable<>();
    }
    public DeliveryManager(ArrayList<Site> siteOrder, Hashtable<Site, Hashtable<String, Integer>> siteToItemsTable, Hashtable<String, Double> itemToWeightTable) {
        this.shipmentRoute = siteOrder;
        this.siteInventoryMap = siteToItemsTable;
        this.itemWeightMap = itemToWeightTable;
    }
    public Site getSite(int siteID){
        return shipmentRoute.get(siteID);
    }

    public ArrayList<Site> getShipmentRoute()
    {
        return shipmentRoute;
    }
    public Hashtable<String, Double> getSiteInventoryMap()
    {
        return itemWeightMap;
    }
    public Hashtable<Site, Hashtable<String, Integer>> getItemWeightMap()
    {
        return siteInventoryMap;
    }
    public void addSite(Site site)
    {
        shipmentRoute.add(site);
        siteInventoryMap.put(site,new Hashtable<>());
    }
    public void removeSite(Site site)
    {
        siteInventoryMap.remove(site);
        shipmentRoute.remove(site);
    }
     public void editItemAmount(Site site, String item, int amount) throws Exception
     {
         if (!itemWeightMap.containsKey(item))
         {
             throw new Exception("Shipment does not contain info on given item- please enter new item!");
         }
         if(amount < 1 )
         {
             throw new Exception("Amount need to bigger then 0");
         }
         Hashtable <String, Integer> destinationItems = siteInventoryMap.get(site);//Brings all the items that exist for the site dest
         destinationItems.put(item,amount);//Updates the quantities of the items in the shipment
         siteInventoryMap.put(site,destinationItems);//Return the updated table of items into siteInventoryMap
     }
     public void addItemToShipment(Site site, String item, int amount, double weight) throws Exception {
         if (itemWeightMap.containsKey(item)) {
             throw new Exception("Item already exist!");
         }
         if (amount < 1) {
             throw new IllegalArgumentException("Amount need to bigger then 0");
         }
         if (weight <= 0 ){
             throw new IllegalArgumentException(" Weight of item must be < 0");
         }
         itemWeightMap.put(item,weight);
         Hashtable<String, Integer> destinationItems = siteInventoryMap.get(site);
         destinationItems.put(item, amount);
         siteInventoryMap.put(site,destinationItems);
     }

    public void removeItem(Site site, String item)
    {
        if (!siteInventoryMap.containsKey(site))
        {
        throw new IllegalArgumentException("Site " + site.getSiteName() + " is not a part of this shipment!");
        }
        if (!itemWeightMap.containsKey(item))
        {
            throw new IllegalArgumentException("Item does not exist!");
        }

        Hashtable<String, Integer> destinationItems = siteInventoryMap.get(site);
        destinationItems.remove(item);
        siteInventoryMap.put(site,destinationItems);

    }
    public int validShipmentWeight(double weight)
    {
        double sumWeight=0;
        for (int i = 0; i < shipmentRoute.size(); i++)
        {
            Site site = shipmentRoute.get(i);
            Hashtable<String, Integer> items =  siteInventoryMap.get(site);
            if (items != null){
                for (String item: items.keySet())
                {
                    sumWeight += itemWeightMap.get(item) * items.get(item);
                }
                if (sumWeight > weight)
                {
                    return i;
                }
            }
        }
        return -1;
    }
    public double getWeightUntilSiteX(int siteNumber){
        double sumWeight = 0;
        for (int i = 0; i <= siteNumber; i++)
        {
            for(Site site: shipmentRoute)
            {
                Hashtable<String, Integer> items =  siteInventoryMap.get(site);
                if(items == null)
                {
                    items = new Hashtable<>();
                }
                for (String item: items.keySet()){
                    sumWeight += itemWeightMap.get(item) * items.get(item);
                }
            }
        }
        return sumWeight;
    }
    public void setOrder(ArrayList<Site> shipmentRoute) {
        this.shipmentRoute = shipmentRoute;
    }


    public boolean isShipmentInfoValid(){
        if (shipmentRoute.isEmpty()){
            return false;
        }
        for (Site site: shipmentRoute){
            if (site == null || !site.validSite() | !doesSiteHaveItems(site)){
                return false;
            }
        }
        return true;
    }
    public boolean doesSiteHaveItems(Site site)
    {
        if(siteInventoryMap.get(site) == null||siteInventoryMap.get(site).isEmpty())
        {
            return false;
        }
        return true;
    }





}
