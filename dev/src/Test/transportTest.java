package Test;

import Domain.Order;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import Domain.*;

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

    @Test
    public void editOrder()
    {
        Dictionary<Integer, ArrayList<String>> itemListOrder = new Hashtable< Integer,ArrayList<String>>();
        Dictionary<String, String> orderdict= new Hashtable<String, String>();
        Site s1 = new Site("Rag", "Center", "Bibi", "552");
        Site s2 = new Site("Nora", "Center", "Sara", "0954321");
        Site s3 = new Site("Loi", "North", "Siri", "452");
        tr.getDomain().addSiteToList(s1);
        tr.getDomain().addSiteToList(s2);
        tr.getDomain().addSiteToList(s3);
        ArrayList<String> item_1 = new ArrayList<>();
        item_1.add("1");
        item_1.add("Sugar");
        item_1.add("3");
        itemListOrder.put(1,item_1);
        ArrayList<String> item_2 = new ArrayList<>();
        item_2.add("2");
        item_2.add("Bread");
        item_2.add("55");
        itemListOrder.put(2,item_2);

        orderdict.put("year", "2025");
        orderdict.put("month", "02");
        orderdict.put("day", "02");
        orderdict.put("source", "Rag");
        orderdict.put("destination", "Nora");
        int ans = tr.getDomain().addOrder(orderdict,itemListOrder);
        assertEquals(1,ans);
        int size = tr.getDomain().getAllOrders().size();
        assertEquals(1,size);
        Dictionary<String, String> data= new Hashtable<String, String>();
        data.put("id",Integer.toString(ans));
        data.put("destination","Loi");
        boolean b = tr.getDomain().changeDestination(data);
        assertEquals(false,b);
        String str = tr.getDomain().getOrderByID(ans).getDestination().getAddress();
        assertEquals("Nora",str);
    }

    @Test
    public void Overweight_changeTruckSol ()
    {
        Dictionary<String, String> d = new Hashtable<String, String>();

        Site site1 = new Site("Owl", "South","Jone", "050864");
        Site site2 = new Site("Bear", "South","Jenifer","7785");
        tr.getDomain().addSiteToList(site1);
        tr.getDomain().addSiteToList(site2);
        ArrayList<Item> list_i = new ArrayList<>();
        list_i.add(new Item(1,"honey",23));
        list_i.add(new Item(2,"spoons",14));
        LocalDate date_ = LocalDate.of(2025,8,25);
        Order o1 = new Order(date_,site1,site2,list_i);
        tr.getDomain().getAllOrders().add(o1);
        Truck truck_1 = new Truck(9,110.0,900,"RR");
        Truck truck_2 = new Truck(10,20.0,1950.0,"RZR");
        tr.getDomain().addTruck(truck_1);
        tr.getDomain().addTruck(truck_2);

        Driver driver_ = new Driver("Zoro",9,"B");
        tr.getDomain().addDriver(driver_);
        int id_trans = tr.addTransport(truck_1.getIdTruck(),driver_.getId());
        assertEquals(1,id_trans);
        Transport t = tr.getTransportByID(id_trans);
        t.addOrderToMYTransport(o1);
        d.put("transportID",Integer.toString(id_trans));
        d.put("orderID",Integer.toString(o1.getId()));
        d.put("weight","1000");
        boolean b1 = tr.loadOrderToTruck(d);
        assertEquals(false,b1);

        Dictionary<String, String> d2 = new Hashtable<String, String>();
        d2.put("transportID",Integer.toString(id_trans));
        d2.put("orderID",Integer.toString(o1.getId()));
        d2.put("truckId","10");
        boolean b2 = tr.treatmentWeightProblemChangeTruck(d2);
        b1 = tr.loadOrderToTruck(d);
        assertEquals(true,b2); // truck changed
        assertEquals(true,b1); // load to the new truck

    }

    @Test
    public void Overweight_changeItemsSol ()
    {
        Dictionary<String, String> _d = new Hashtable<String, String>();

        Site site_1 = new Site("Gilat", "North","Joo", "05083564");
        Site site_2 = new Site("Avisror", "South","Jeni","74234234");
        tr.getDomain().addSiteToList(site_1);
        tr.getDomain().addSiteToList(site_2);
        ArrayList<Item> i_list = new ArrayList<>();
        i_list.add(new Item(11,"olives",23));
        i_list.add(new Item(12,"watermelons",500));
        LocalDate date_ = LocalDate.of(2026,4,5);
        Order or1 = new Order(date_,site_1,site_2,i_list);
        tr.getDomain().getAllOrders().add(o1);
        Truck truck_1 = new Truck(9,110.0,900,"RR");
        Truck truck_2 = new Truck(10,20.0,1950.0,"RZR");
        tr.getDomain().addTruck(truck_1);
        tr.getDomain().addTruck(truck_2);

        Driver driver_ = new Driver("Zoro",9,"B");
        tr.getDomain().addDriver(driver_);
        int id_trans = tr.addTransport(truck_1.getIdTruck(),driver_.getId());
        assertEquals(1,id_trans);
        Transport t = tr.getTransportByID(id_trans);
        t.addOrderToMYTransport(o1);
        d.put("transportID",Integer.toString(id_trans));
        d.put("orderID",Integer.toString(o1.getId()));
        d.put("weight","1000");
        boolean b1 = tr.loadOrderToTruck(d);
        assertEquals(false,b1);

        Dictionary<String, String> d2 = new Hashtable<String, String>();
        d2.put("transportID",Integer.toString(id_trans));
        d2.put("orderID",Integer.toString(o1.getId()));
        d2.put("truckId","10");
        boolean b2 = tr.treatmentWeightProblemChangeTruck(d2);
        b1 = tr.loadOrderToTruck(d);
        assertEquals(true,b2); // truck changed
        assertEquals(true,b1); // load to the new truck

    }


}
