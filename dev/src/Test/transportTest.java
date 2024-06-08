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

    @Test
    public void changeTruck()
    {
        Dictionary<String, String> dataT1 = new Hashtable<String, String>();
        dataT1.put("idT","1001");
        dataT1.put("initialWeight","500");
        dataT1.put("maxWeight", "300");
        dataT1.put("model", "Tesla");
        boolean b = tr.getDomain().addTruck(dataT1);
        assertEquals(true,b);
        Dictionary<String, String> dataT2 = new Hashtable<String, String>();
        dataT2.put("idT","1001");
        dataT2.put("initialWeight","3500");
        dataT2.put("maxWeight", "4000");
        dataT2.put("model", "Mromoro");
        b = tr.getDomain().addTruck(dataT2);
        int size = tr.getDomain().getTrucks().size();
        assertEquals(1,size);
        assertEquals(false,b);
        Dictionary<String, String> dataT3 = new Hashtable<String, String>();
        dataT3.put("idT","1003");
        dataT3.put("initialWeight","3500");
        dataT3.put("maxWeight", "400");
        dataT3.put("model", "Mromoro");
        b = tr.getDomain().addTruck(dataT3);
        size = tr.getDomain().getTrucks().size();
        assertEquals(2,size);
        assertEquals(true,b);

        Dictionary<String, String> dataT4 = new Hashtable<String, String>();
        dataT4.put("idT","1004");
        dataT4.put("initialWeight","200");
        dataT4.put("maxWeight", "1000");
        dataT4.put("model", "Mro");
        tr.getDomain().addTruck(dataT4);
        // driver
        Dictionary<String, String> dataD1 = new Hashtable<String, String>();
        dataD1.put("name", "Ron");
        dataD1.put("id", "34");
        dataD1.put("typeOfLicense", "B");
        b = tr.getDomain().addDriver(dataD1);
        assertEquals(true,b);
        size = tr.getDomain().getDrivers().size();
        assertEquals(1,size);
        //transport
        Dictionary<String, String> dataTR1 = new Hashtable<String, String>();
        dataTR1.put("idT", "1001");
        dataTR1.put("idD", "34");
        int ans = tr.addTransport(dataTR1);
        assertEquals(1,ans);
        //change Truck - false
        Dictionary<String, String> data1 = new Hashtable<String, String>();
        data1.put("idTransport", "1");
        data1.put("idTruck", "1003");
        b = tr.changeTruck(data1);
        assertEquals(false,b);
        int idTruckOfTransport1 = tr.getTransportByID(ans).getTruck().getIdTruck();
        assertEquals(1001,idTruckOfTransport1);
        //change Truck - true
        Dictionary<String, String> data2 = new Hashtable<String, String>();
        data2.put("idTransport", "1");
        data2.put("idTruck", "1004");
        b = tr.changeTruck(data2);
        idTruckOfTransport1 = tr.getTransportByID(ans).getTruck().getIdTruck();
        assertEquals(true,b);
        assertEquals(1004,idTruckOfTransport1);
    }

    @Test //scheduling Driver - false
    public void schedulingDriver()
    {
        Dictionary<String, String> dataT1 = new Hashtable<String, String>();
        dataT1.put("idT","101");
        dataT1.put("initialWeight","600");
        dataT1.put("maxWeight", "100");
        dataT1.put("model", "Tesla");
        boolean b = tr.getDomain().addTruck(dataT1);
        assertEquals(true,b);
        // driver
        Dictionary<String, String> dataD1 = new Hashtable<String, String>();
        dataD1.put("name", "Maor");
        dataD1.put("id", "1");
        dataD1.put("typeOfLicense", "C");
        b = tr.getDomain().addDriver(dataD1);
        assertEquals(true,b);
        int size = tr.getDomain().getDrivers().size();
        assertEquals(1,size);
        //transport
        Dictionary<String, String> dataTR1 = new Hashtable<String, String>();
        dataTR1.put("idT", "101");
        dataTR1.put("idD", "1");
        int ans = tr.addTransport(dataTR1);
        assertEquals(-2,ans);

    }






//    @Test
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
//
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
