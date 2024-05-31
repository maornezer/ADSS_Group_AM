package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class DeliveryManager
{
    //The list of sites in order of delivery.
    // The sites in the list represent the stations on the shipping route from the source to the final destinations.
    ArrayList<Site> siteOrder;
    Hashtable<Site, Hashtable<String,Integer>> siteToItemsTable;
    //key - site
    //value - hashtable ( key - item name, item amount)
    Hashtable<String,Double> itemToWeightTable;
    //This table is used to manage the inventory of items on each site.
    // Each site contains a table that indicates the amount of items that are received or sent from it.

    public DeliveryManager() {
        this.siteOrder = new ArrayList<>();
        this.siteToItemsTable = new Hashtable<>();
        this.itemToWeightTable = new Hashtable<>();
    }
    public DeliveryManager(ArrayList<Site> siteOrder, Hashtable<Site, Hashtable<String, Integer>> siteToItemsTable, Hashtable<String, Double> itemToWeightTable) {
        this.siteOrder = siteOrder;
        this.siteToItemsTable = siteToItemsTable;
        this.itemToWeightTable = itemToWeightTable;
    }

    public void addSite(Site site)
    {
        siteOrder.add(site);
        siteToItemsTable.put(site,new Hashtable<>());
    }

    public void removeSite(Site site)
    {
        siteToItemsTable.remove(site);
        siteOrder.remove(site);
    }
    public ArrayList<Site> getSiteOrder()
    {
        return siteOrder;
    }
    public Hashtable<String, Double> getItemToWeightTable()
    {
        return itemToWeightTable;
    }
    public Hashtable<Site, Hashtable<String, Integer>> getSiteToItemsTable()
    {
        return siteToItemsTable;
    }


}
