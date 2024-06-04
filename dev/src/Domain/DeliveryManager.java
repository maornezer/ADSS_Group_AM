//package Domain;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Hashtable;
//
//public class DeliveryManager
//{
//    //The list of sites in order of delivery.
//    // The sites in the list represent the stations on the shipping route from the source to the final destinations.
//    ArrayList<Site> shipmentRoute;
//    //key - site
//    //value - hashtable ( key - item name, item amount)
//    //This table is used to manage the inventory of items on each site.
//    //Each site contains a table that indicates the amount of items.
//    Hashtable<Site, Hashtable<String,Integer>> siteInventoryMap;
//    Hashtable<String,Double> itemWeightMap;
//    //A table that maps each item name to its weight.
//    //This table is used to manage the weight of each item, which is important for calculating the total shipping weight and managing the truck load.
//    public DeliveryManager() {
//        this.shipmentRoute = new ArrayList<>();
//        this.siteInventoryMap = new Hashtable<>();
//        this.itemWeightMap = new Hashtable<>();
//    }
//    public DeliveryManager(ArrayList<Site> siteOrder, Hashtable<Site, Hashtable<String, Integer>> siteToItemsTable, Hashtable<String, Double> itemToWeightTable) {
//        this.shipmentRoute = siteOrder;
//        this.siteInventoryMap = siteToItemsTable;
//        this.itemWeightMap = itemToWeightTable;
//    }
//
//
//
//    public int validShipmentWeight(double weight)
//    {
//        double sumWeight=0;
//        for (int i = 0; i < shipmentRoute.size(); i++)
//        {
//            Site site = shipmentRoute.get(i);
//            Hashtable<String, Integer> items =  siteInventoryMap.get(site);
//            if (items != null){
//                for (String item: items.keySet())
//                {
//                    sumWeight += itemWeightMap.get(item) * items.get(item);
//                }
//                if (sumWeight > weight)
//                {
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
//
//
//    public double getWeightUntilSiteX(int siteNumber){
//        double sumWeight = 0;
//        for (int i = 0; i <= siteNumber; i++)
//        {
//            for(Site site: shipmentRoute)
//            {
//                Hashtable<String, Integer> items =  siteInventoryMap.get(site);
//                if(items == null)
//                {
//                    items = new Hashtable<>();
//                }
//                for (String item: items.keySet()){
//                    sumWeight += itemWeightMap.get(item) * items.get(item);
//                }
//            }
//        }
//        return sumWeight;
//    }
//
//    public boolean ensureShipmentWeight()
//    {
//        if (deliveryManager.validShipmentWeight(truck.getMaxWeight()) < 0)
//        {
//            return false;
//        }
//        return true;
//    }
//
//
//
//    public double getShipmentRouteWeight()
//    {
//        int lastSiteIndex = countDestinations();// maybe -1
//        return deliveryManager.getWeightUntilSiteX(lastSiteIndex);
//    }
//
//
//
//}
