package Test;

import Domain.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import Domain.*;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;


public class transportTest
{
    private TransportController tr = new TransportController();

    @Test
    public void addSite() //add 4 site, 1 false
    {
        Dictionary<String, String> dataSite = new Hashtable<String, String>();

        dataSite.put("address", "Rager");
        dataSite.put("zone", "Center");
        dataSite.put("contactName", "Maor");
        dataSite.put("phoneNumber", "0508260837");
        tr.getDomain().addSite(dataSite,"test");
        int size = tr.getDomain().getSites().size();
        assertEquals(size, 1);
        dataSite.put("address", "Rager");
        dataSite.put("zone", "Center");
        dataSite.put("contactName", "Mor");
        dataSite.put("phoneNumber", "0507630837");
        assertFalse(tr.getDomain().addSite(dataSite,"test"));
        size = tr.getDomain().getSites().size();
        assertEquals(size, 1);
        dataSite.put("address", "Block");
        dataSite.put("zone", "Center");
        dataSite.put("contactName", "Mri");
        dataSite.put("phoneNumber", "050123457");
        tr.getDomain().addSite(dataSite,"test");
        dataSite.put("address", "Bialik");
        dataSite.put("zone", "South");
        dataSite.put("contactName", "Bialik");
        dataSite.put("phoneNumber", "05678457");
        tr.getDomain().addSite(dataSite,"test");
        size = tr.getDomain().getSites().size();
        assertEquals(size, 3);
    }
    //Site s3 = new Site("Daisy  ", "South", "Barbara", "09261"));
    //tr.getDomain().addSiteToList(s3);

    @Test
    public void testAddOrder()
    {
        Dictionary<Integer, ArrayList<String>> data2 = new Hashtable< Integer,ArrayList<String>>();
        Dictionary<String, String> data1= new Hashtable<String, String>();
        Site s1 = new Site("Rose", "North", "Bob", "0987654321");
        Site s2 = new Site("Lili", "North", "Bob", "0987654321");
        tr.getDomain().addSiteToList(s1);
        tr.getDomain().addSiteToList(s2);
        ArrayList<String> item1 = new ArrayList<>();
        item1.add("10");
        item1.add("Miliki");
        item1.add("3");
        data2.put(1,item1);
        ArrayList<String> item2 = new ArrayList<>();
        item2.add("11");
        item2.add("Banana");
        item2.add("13");
        data2.put(2,item2);

        data1.put("year", "2024");
        data1.put("month", "06");
        data1.put("day", "06");
        data1.put("source", "Rose");
        data1.put("destination", "Lili");
        int ans = tr.getDomain().addOrder(data1,data2);
        assertEquals(1,ans);
        int size = tr.getDomain().getAllOrders().size();
        assertEquals(1,size);
    }
    @Test
    public void testFailAddOrder()
    {
        Dictionary<Integer, ArrayList<String>> data2 = new Hashtable< Integer,ArrayList<String>>();
        Dictionary<String, String> data1= new Hashtable<String, String>();
        Site s1 = new Site("Tulip", "North", "Bob", "0987654321");
        Site s2 = new Site("Daisy", "North", "Bob", "0987654321");
        tr.getDomain().addSiteToList(s1);
        tr.getDomain().addSiteToList(s2);
        ArrayList<String> item1 = new ArrayList<>();
        item1.add("13");
        item1.add("Orange");
        item1.add("6");
        data2.put(1,item1);

        data1.put("year", "2023");
        data1.put("month", "04");
        data1.put("day", "02");
        data1.put("source", "Daisy");
        data1.put("destination", "Ababa");
        int ans = tr.getDomain().addOrder(data1,data2);
        assertEquals(-2,ans);
        int size = tr.getDomain().getAllOrders().size();
        assertEquals(0,size);
    }

//    @Test
//    public void AddAndChangeTruckS()
//    {
//        Dictionary<String, String> data1 = new Hashtable<String, String>();
//        data1.put("idT","1001");
//        data1.put("initialWeight","2500");
//        data1.put("maxWeight", "3000");
//        data1.put("model", "Tesla");
//        boolean b = tr.getDomain().addTruck(data1);
//        assertEquals(true,b);
//        Dictionary<String, String> data2 = new Hashtable<String, String>();
//        data1.put("idT","1001");
//        data1.put("initialWeight","3500");
//        data1.put("maxWeight", "4000");
//        data1.put("model", "Mromoro");
//        b = tr.getDomain().addTruck(data1);
//        int size = tr.getDomain().getTrucks().size();
//        assertEquals(1,size);
//        assertEquals(false,b);
//        tr.changeTruck(1001,1002);
//        size = tr.getDomain().getTrucks().size();
//        assertEquals(2,size);
//    }
  //  @Test
//    public void addTransport()
//    {
//
//        Dictionary<String, String> data = new Hashtable<String, String>();
//        data.put("idT", "100");
//        data.put("idD", "1001");
//
//        int ans = controller.addTransport(data);
//
//    }

//    public void localData()
//    {
//        //tr = new TransportController();
//
//        Truck t1 = new Truck(1, 10000, 20000, "D");
//        Truck t2 = new Truck(2, 3000, 7000, "C");
//        Truck t3 = new Truck(3, 1000, 1500, "B");
//        tr.getDomain().addTruck(t1);
//        tr.getDomain().addTruck(t2);
//        tr.getDomain().addTruck(t3);
//
//        Driver d1 = new Driver("Maor", 1, "D");
//        Driver d2 = new Driver("Ron", 2, "D");
//        Driver d3 = new Driver("Sahar", 3, "A");
//        Driver d4 = new Driver("Noa", 4, "A");
//        Driver d5 = new Driver("Lee", 5, "B");
//        tr.getDomain().addDriver(d1);
//        tr.getDomain().addDriver(d2);
//        tr.getDomain().addDriver(d3);
//        tr.getDomain().addDriver(d4);
//        tr.getDomain().addDriver(d5);
//
//        tr.getDomain().addSiteToList(new Site("Narcissus ", "North", "Alice", "1234567890"));
//        tr.getDomain().addSiteToList(new Site("Rose", "North", "Bob", "0987654321"));
//        tr.getDomain().addSiteToList(new Site("Daisy", "South", "Barbara", "09261"));
//        tr.getDomain().addSiteToList(new Site("Tulip", "Center", "Marsel", "508068"));
//        tr.getDomain().addSiteToList(new Site("Trumpeldor   ", "Center", "Trumpeldor", "1000"));
//
//
//
//    }
}
