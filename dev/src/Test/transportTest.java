package Test;

import Domain.Order;
//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import Domain.*;


public class transportTest
{
    private  TransportController tr = new TransportController();


//    @Before
//    public  void setUp() {
//        tr = new TransportController();
//    }
//
//    @After
//    public void tearDown()
//    {
//        tr.getDomain().getTrucks().clear();
//        tr.getDomain().getSites().clear();
//        tr.getDomain().getAllOrders().clear();
//        tr.getDomain().getDrivers().clear();
//        tr.getTransports().clear();
//    }

//    @Test
//    public void addSite() //add 4 site, 1 false
//    {
//        Dictionary<String, String> dataSite = new Hashtable<String, String>();
//
//        dataSite.put("address", "Rager");
//        dataSite.put("zone", "Center");
//        dataSite.put("contactName", "Maor");
//        dataSite.put("phoneNumber", "0508260837");
//        d
//        tr.getOperations().addSite(dataSite);
//        int size = tr.getOperations().getSiteRepo().getSites().size();
//        assertEquals(size, 1);
//        dataSite.put("address", "Rager");
//        dataSite.put("zone", "Center");
//        dataSite.put("contactName", "Mor");
//        dataSite.put("phoneNumber", "0507630837");
//        assertFalse(tr.getOperations().addSite(dataSite));
//        size = tr.getOperations().getSiteRepo().getSites().size();
//        assertEquals(size, 1);
//        dataSite.put("address", "Block");
//        dataSite.put("zone", "Center");
//        dataSite.put("contactName", "Mri");
//        dataSite.put("phoneNumber", "050123457");
//        tr.getOperations().addSite(dataSite);
//        dataSite.put("address", "Bialik");
//        dataSite.put("zone", "South");
//        dataSite.put("contactName", "Bialik");
//        dataSite.put("phoneNumber", "05678457");
//        tr.getOperations().addSite(dataSite);
//        size = tr.getOperations().getSiteRepo().getSites().size();
//        assertEquals(size, 3);
//
//    }

//    @Test
//    public void testAddOrder()
//    {
//        Dictionary<Integer, ArrayList<String>> data2 = new Hashtable< Integer,ArrayList<String>>();
//        Dictionary<String, String> data1= new Hashtable<String, String>();
//        Site s1 = new Site("Rose", "North", "Bob", "0987654321");
//        Site s2 = new Site("Lili", "North", "Bob", "0987654321");
//        tr.getOperations().getSiteRepo().getSites().add(s1);
//        tr.getDomain().addSiteToList(s2);
//        ArrayList<String> item1 = new ArrayList<>();
//        item1.add("10");
//        item1.add("Miliki");
//        item1.add("3");
//        data2.put(1,item1);
//        ArrayList<String> item2 = new ArrayList<>();
//        item2.add("11");
//        item2.add("Banana");
//        item2.add("13");
//        data2.put(2,item2);
//
//        data1.put("year", "2024");
//        data1.put("month", "06");
//        data1.put("day", "06");
//        data1.put("source", "Rose");
//        data1.put("destination", "Lili");
//        int ans = tr.getDomain().addOrder(data1,data2);
//        assertEquals(1,ans);
//        int size = tr.getDomain().getAllOrders().size();
//        assertEquals(1,size);
//
//    }
//    @Test
//    public void testFailAddOrder() // address destination not on the system
//    {
//        Dictionary<Integer, ArrayList<String>> data2 = new Hashtable< Integer,ArrayList<String>>();
//        Dictionary<String, String> data1= new Hashtable<String, String>();
//        Site s1 = new Site("Tulip", "North", "Bob", "0987654321");
//        Site s2 = new Site("Daisy", "North", "Bob", "0987654321");
//        tr.getDomain().addSiteToList(s1);
//        tr.getDomain().addSiteToList(s2);
//        ArrayList<String> item1 = new ArrayList<>();
//        item1.add("13");
//        item1.add("Orange");
//        item1.add("6");
//        data2.put(1,item1);
//
//        data1.put("year", "2023");
//        data1.put("month", "04");
//        data1.put("day", "02");
//        data1.put("source", "Daisy");
//        data1.put("destination", "Ababa");
//        int ans = tr.getDomain().addOrder(data1,data2);
//        assertEquals(-2,ans);
//        int size = tr.getDomain().getAllOrders().size();
//        assertEquals(0,size);
//
//    }
//
//    @Test
//    public void changeTruck()
//    {
//        Dictionary<String, String> dataT1 = new Hashtable<String, String>();
//        dataT1.put("idT","1001");
//        dataT1.put("initialWeight","500");
//        dataT1.put("maxWeight", "300");
//        dataT1.put("model", "Tesla");
//        boolean b = tr.getDomain().addTruck(dataT1);
//        assertEquals(true,b);
//        Dictionary<String, String> dataT2 = new Hashtable<String, String>();
//        dataT2.put("idT","1001");
//        dataT2.put("initialWeight","3500");
//        dataT2.put("maxWeight", "4000");
//        dataT2.put("model", "Mromoro");
//        b = tr.getDomain().addTruck(dataT2);
//        int size = tr.getDomain().getTrucks().size();
//        assertEquals(1,size);
//        assertEquals(false,b);
//        Dictionary<String, String> dataT3 = new Hashtable<String, String>();
//        dataT3.put("idT","1003");
//        dataT3.put("initialWeight","3500");
//        dataT3.put("maxWeight", "400");
//        dataT3.put("model", "Mromoro");
//        b = tr.getDomain().addTruck(dataT3);
//        size = tr.getDomain().getTrucks().size();
//        assertEquals(2,size);
//        assertEquals(true,b);
//
//        Dictionary<String, String> dataT4 = new Hashtable<String, String>();
//        dataT4.put("idT","1004");
//        dataT4.put("initialWeight","200");
//        dataT4.put("maxWeight", "1000");
//        dataT4.put("model", "Mro");
//        tr.getDomain().addTruck(dataT4);
//        // driver
//        Dictionary<String, String> dataD1 = new Hashtable<String, String>();
//        dataD1.put("name", "Ron");
//        dataD1.put("id", "34");
//        dataD1.put("typeOfLicense", "B");
//        b = tr.getDomain().addDriver(dataD1);
//        assertEquals(true,b);
//        size = tr.getDomain().getDrivers().size();
//        assertEquals(1,size);
//        //transport
//        Dictionary<String, String> dataTR1 = new Hashtable<String, String>();
//        dataTR1.put("idT", "1001");
//        dataTR1.put("idD", "34");
//        int ans = tr.addTransport(dataTR1);
//        assertEquals(1,ans);
//        //change Truck - false
//        Dictionary<String, String> data1 = new Hashtable<String, String>();
//        data1.put("idTransport", "1");
//        data1.put("idTruck", "1003");
//        b = tr.changeTruck(data1);
//        assertEquals(false,b);
//        int idTruckOfTransport1 = tr.getTransportByID(ans).getTruck().getIdTruck();
//        assertEquals(1001,idTruckOfTransport1);
//        //change Truck - true
//        Dictionary<String, String> data2 = new Hashtable<String, String>();
//        data2.put("idTransport", "1");
//        data2.put("idTruck", "1004");
//        b = tr.changeTruck(data2);
//        idTruckOfTransport1 = tr.getTransportByID(ans).getTruck().getIdTruck();
//        assertEquals(true,b);
//        assertEquals(1004,idTruckOfTransport1);
//
//    }
//
//    @Test //scheduling Driver - false
//    public void schedulingDriver()
//    {
//        Dictionary<String, String> dataT1 = new Hashtable<String, String>();
//        dataT1.put("idT","101");
//        dataT1.put("initialWeight","600");
//        dataT1.put("maxWeight", "100");
//        dataT1.put("model", "Tesla");
//        boolean b = tr.getDomain().addTruck(dataT1);
//        assertEquals(true,b);
//        // driver
//        Dictionary<String, String> dataD1 = new Hashtable<String, String>();
//        dataD1.put("name", "Maor");
//        dataD1.put("id", "1");
//        dataD1.put("typeOfLicense", "C");
//        b = tr.getDomain().addDriver(dataD1);
//        assertEquals(true,b);
//        int size = tr.getDomain().getDrivers().size();
//        assertEquals(1,size);
//        //transport
//        Dictionary<String, String> dataTR1 = new Hashtable<String, String>();
//        dataTR1.put("idT", "101");
//        dataTR1.put("idD", "1");
//        int ans = tr.addTransport(dataTR1);
//        assertEquals(-2,ans);
//    }
//
//    @Test
//    public void editOrder()
//    {
//        Dictionary<Integer, ArrayList<String>> itemListOrder = new Hashtable< Integer,ArrayList<String>>();
//        Dictionary<String, String> orderdict= new Hashtable<String, String>();
//        Site s1 = new Site("Rag", "Center", "Bibi", "552");
//        Site s2 = new Site("Nora", "Center", "Sara", "0954321");
//        Site s3 = new Site("Loi", "North", "Siri", "452");
//        tr.getDomain().addSiteToList(s1);
//        tr.getDomain().addSiteToList(s2);
//        tr.getDomain().addSiteToList(s3);
//        ArrayList<String> item_1 = new ArrayList<>();
//        item_1.add("1");
//        item_1.add("Sugar");
//        item_1.add("3");
//        itemListOrder.put(1,item_1);
//        ArrayList<String> item_2 = new ArrayList<>();
//        item_2.add("2");
//        item_2.add("Bread");
//        item_2.add("55");
//        itemListOrder.put(2,item_2);
//
//        orderdict.put("year", "2025");
//        orderdict.put("month", "02");
//        orderdict.put("day", "02");
//        orderdict.put("source", "Rag");
//        orderdict.put("destination", "Nora");
//        int ans = tr.getDomain().addOrder(orderdict,itemListOrder);
//        assertEquals(1,ans);
//        int size = tr.getDomain().getAllOrders().size();
//        assertEquals(1,size);
//        Dictionary<String, String> data= new Hashtable<String, String>();
//        data.put("id",Integer.toString(ans));
//        data.put("destination","Loi");
//        boolean b = tr.getDomain().changeDestination(data);
//        assertEquals(false,b);
//        String str = tr.getDomain().getOrderByID(ans).getDestination().getAddress();
//        assertEquals("Nora",str);
//
//    }
//
//    @Test
//    public void Overweight_changeTruckSol ()
//    {
//        Dictionary<String, String> d = new Hashtable<String, String>();
//
//        Site site1 = new Site("Owl", "South","Jone", "050864");
//        Site site2 = new Site("Bear", "South","Jenifer","7785");
//        tr.getDomain().addSiteToList(site1);
//        tr.getDomain().addSiteToList(site2);
//        ArrayList<Item> list_i = new ArrayList<>();
//        list_i.add(new Item(1,"honey",23));
//        list_i.add(new Item(2,"spoons",14));
//        LocalDate date_ = LocalDate.of(2025,8,25);
//        Order o1 = new Order(date_,site1,site2,list_i);
//        tr.getDomain().getAllOrders().add(o1);
//        Truck truck_1 = new Truck(9,110.0,900,"RR");
//        Truck truck_2 = new Truck(10,20.0,1950.0,"RZR");
//        tr.getDomain().addTruck(truck_1);
//        tr.getDomain().addTruck(truck_2);
//
//        Driver driver_ = new Driver("Zoro",9,"B");
//        tr.getDomain().addDriver(driver_);
//        int id_trans = tr.addTransport(truck_1.getIdTruck(),driver_.getId());
//        assertEquals(1,id_trans);
//        Transport t = tr.getTransportByID(id_trans);
//        t.addOrderToMYTransport(o1);
//        d.put("transportID",Integer.toString(id_trans));
//        d.put("orderID",Integer.toString(o1.getId()));
//        d.put("weight","1000");
//        boolean b1 = tr.loadOrderToTruck(d);
//        assertEquals(false,b1);
//
//        Dictionary<String, String> d2 = new Hashtable<String, String>();
//        d2.put("transportID",Integer.toString(id_trans));
//        d2.put("orderID",Integer.toString(o1.getId()));
//        d2.put("truckId","10");
//        boolean b2 = tr.treatmentWeightProblemChangeTruck(d2);
//        b1 = tr.loadOrderToTruck(d);
//        assertEquals(true,b2); // truck changed
//        assertEquals(true,b1); // load to the new truck
//    }
//
//    @Test
//    public void Overweight_changeItemsSol ()
//    {
//        Dictionary<String, String> _d = new Hashtable<String, String>();
//
//        Site site_1 = new Site("Gilat", "North","Joo", "05083564");
//        Site site_2 = new Site("Avisror", "North","Jeni","74234234");
//        tr.getDomain().addSiteToList(site_1);
//        tr.getDomain().addSiteToList(site_2);
//
//        Item i_1 = new Item(11,"olives",23);
//        Item i_2 = new Item(12,"watermelons",500);
//        ArrayList<Item> i_list = new ArrayList<>();
//        i_list.add(i_2);
//        i_list.add(i_1);
//
//        LocalDate date_ = LocalDate.of(2026,4,5);
//        Order _order = new Order(date_,site_1,site_2,i_list);
//        tr.getDomain().getAllOrders().add(_order);
//
//        Truck truck1 = new Truck(99,1000,4000,"RR");
//        tr.getDomain().addTruck(truck1);
//        Driver _driver_ = new Driver("Zorozoro",8,"C");
//        tr.getDomain().addDriver(_driver_);
//        int id_transport_ = tr.addTransport(truck1.getIdTruck(),_driver_.getId());
//        assertEquals(1,id_transport_);
//        Transport t = tr.getTransportByID(id_transport_);
//        t.addOrderToMYTransport(_order);
//        _d.put("transportID",Integer.toString(id_transport_));
//        _d.put("orderID",Integer.toString(_order.getId()));
//        _d.put("weight","4110");
//        boolean b_1 = tr.loadOrderToTruck(_d);
//        assertEquals(false,b_1);
//        Dictionary<String, String> d_2 = new Hashtable<String, String>();
//        d_2.put("transportID",Integer.toString(id_transport_));
//        d_2.put("orderID",Integer.toString(_order.getId()));
//        d_2.put("itemID",Integer.toString(i_list.get(1).getId()));
//        d_2.put("amount","300");
//        boolean b_2 = tr.treatmentWeightProblemUnloadingItems(d_2);
//        assertEquals(true,b_2); // Unloading Items
//        Dictionary<String, String> _d_ = new Hashtable<String, String>();
//        _d_.put("transportID",Integer.toString(id_transport_));
//        _d_.put("orderID",Integer.toString(_order.getId()));
//        _d_.put("weight","2000");
//        b_1 = tr.loadOrderToTruck(_d_);
//        assertEquals(true,b_1); // second weight
//
//    }
//    @Test
//    public void addAnotherOrderToTransport() //false by unmatched zone
//    {
//        //site
//        Site ashdod = new Site("Ashdod", "North","ron", "0504483564");
//        Site haifa = new Site("Haifa", "North","maor","32434");
//        tr.getDomain().addSiteToList(ashdod);
//        tr.getDomain().addSiteToList(haifa);
//        //item
//        Item popcorn = new Item(101,"popcorn",5);
//        Item pretzels = new Item(102,"pretzels",7);
//        ArrayList<Item> items_order = new ArrayList<>();
//        items_order.add(popcorn);
//        items_order.add(pretzels);
//        // order
//        LocalDate date_order = LocalDate.of(2027,1,28);
//        Order newOrder1 = new Order(date_order,ashdod,haifa,items_order);
//        tr.getDomain().getAllOrders().add(newOrder1);
//        //truck
//        Truck newTruck = new Truck(1009,100,400,"SS");
//        tr.getDomain().addTruck(newTruck);
//        //driver
//        Driver newDriver = new Driver("Tiltil",20,"B");
//        tr.getDomain().addDriver(newDriver);
//        int newTransport = tr.addTransport(newTruck.getIdTruck(),newDriver.getId());
//        boolean bool = tr.getTransportByID(newTransport).addOrderToMYTransport(newOrder1);
//        assertEquals(1,newTransport);
//        assertEquals(1,tr.getTransportByID(newTransport).getMyOrders().size());
//        assertEquals(true,bool);
//        //site
//        Site telAviv = new Site("telAviv", "Center","david", "1");
//        Site Jerusalem = new Site("Jerusalem", "Center","gadi","2");
//        tr.getDomain().addSiteToList(telAviv);
//        tr.getDomain().addSiteToList(Jerusalem);
//        //item
//        Item chocolate = new Item(101,"chocolate",5);
//        Item toffee = new Item(102,"toffee",7);
//        ArrayList<Item> items_order2 = new ArrayList<>();
//        items_order2.add(chocolate);
//        items_order2.add(toffee);
//        // order
//        LocalDate date_order2 = LocalDate.of(2027,1,28);
//        Order newOrder2 = new Order(date_order2,Jerusalem,telAviv,items_order2);
//        tr.getDomain().getAllOrders().add(newOrder2);
//        bool = tr.getTransportByID(newTransport).addOrderToMYTransport(newOrder2);
//        assertEquals(false,bool);
//        assertEquals(1,tr.getTransportByID(newTransport).getMyOrders().size());
//
//    }
//
//    @Test
//    public void addAnotherOrderToTransport2() //false by unmatched Date
//    {
//        //site
//        Site BeerSheva = new Site("BeerSheva", "South","roni", "826057");
//        Site Eilat = new Site("Eilat", "South","maori","123465");
//        tr.getDomain().addSiteToList(BeerSheva);
//        tr.getDomain().addSiteToList(Eilat);
//        //item
//        Item Sunscreen = new Item(1001,"Sunscreen",2);
//        Item Towel = new Item(1002,"Towel",7);
//        ArrayList<Item> items_order = new ArrayList<>();
//        items_order.add(Sunscreen);
//        items_order.add(Towel);
//        // order
//        LocalDate date_order = LocalDate.of(2025,9,24);
//        Order newOrder1 = new Order(date_order,BeerSheva,Eilat,items_order);
//        tr.getDomain().getAllOrders().add(newOrder1);
//        //truck
//        Truck newTruck = new Truck(1004,200,5000,"Bimba");
//        tr.getDomain().addTruck(newTruck);
//        //driver
//        Driver newDriver = new Driver("rami",20,"C");
//        tr.getDomain().addDriver(newDriver);
//        int newTransport = tr.addTransport(newTruck.getIdTruck(),newDriver.getId());
//        boolean bool = tr.getTransportByID(newTransport).addOrderToMYTransport(newOrder1);
//        assertEquals(1,newTransport);
//        assertEquals(1,tr.getTransportByID(newTransport).getMyOrders().size());
//        assertEquals(true,bool);
//        ///site
//        Site PetahTikva = new Site("PetahTikva", "South","marsel", "1");
//        Site NessZiona = new Site("NessZiona", "South","roee","2");
//        tr.getDomain().addSiteToList(NessZiona);
//        tr.getDomain().addSiteToList(PetahTikva);
//        //item
//        Item chocolate = new Item(109,"Umbrella",5);
//        Item toffee = new Item(109,"Bottle",7);
//        ArrayList<Item> items_order2 = new ArrayList<>();
//        items_order2.add(chocolate);
//        items_order2.add(toffee);
//        // order
//        LocalDate date_order2 = LocalDate.of(2025,1,28);
//        Order newOrder2 = new Order(date_order2,NessZiona,PetahTikva,items_order2);
//        tr.getDomain().getAllOrders().add(newOrder2);
//        bool = tr.getTransportByID(newTransport).addOrderToMYTransport(newOrder2);
//        assertEquals(false,bool);
//        assertEquals(1,tr.getTransportByID(newTransport).getMyOrders().size());
//
//    }
    @Test
    public void addSite1()
    {
        Dictionary<String, String> addSite1 = new Hashtable<String, String>();
        addSite1.put("id", "1");
        addSite1.put("address", "Rager");
        addSite1.put("zone", "Central");
        addSite1.put("contactName", "Maor");
        addSite1.put("phoneNumber", "0508260837");
        boolean b = tr.getOperations().addSite(addSite1);
        assertFalse(b);
    }

    @Test
    public void addSite2()
    {
        Dictionary<String, String> addSite2 = new Hashtable<String, String>();
        addSite2.put("id", "1000");
        addSite2.put("address", "R");
        addSite2.put("zone", "Central");
        addSite2.put("contactName", "M");
        addSite2.put("phoneNumber", "057");
        boolean b = tr.getOperations().addSite(addSite2);
        assertTrue(b);
    }
    @Test
    public void addSite3()
    {
        Dictionary<String, String> addSite3 = new Hashtable<String, String>();
        addSite3.put("id", "1001");
        addSite3.put("address", "RR");
        addSite3.put("zone", "Central");
        addSite3.put("contactName", "MM");
        addSite3.put("phoneNumber", "0557");
        boolean b = tr.getOperations().addSite(addSite3);
        assertTrue(b);
    }
    @Test
    public void addTruck()
    {
        Dictionary<String, String> addTruck = new Hashtable<String, String>();
        addTruck.put("idT", "1001");
        addTruck.put("initialWeight", "200");
        addTruck.put("maxWeight", "4000");
        addTruck.put("model", "MMM");
        boolean b = tr.getLogistics().addTruck(addTruck);
        assertTrue(b);
    }
    @Test
    public void addDriver()
    {
        Dictionary<String, String> addDriver = new Hashtable<String, String>();
        addDriver.put("id", "1001");
        addDriver.put("name", "Bobi");
        addDriver.put("typeOfLicense", "C");
        boolean b = tr.getLogistics().addDriver(addDriver);
        assertTrue(b);
    }

    @Test
    public void addOrder()
    {
        Dictionary<String, String> data1 = new Hashtable<String, String>();
        data1.put("year", "2028");
        data1.put("month", "06");
        data1.put("day", "08");
        data1.put("source", "1000");
        data1.put("destination", "1001");

        Dictionary<Integer, ArrayList<String>> data2 = new Hashtable< Integer,ArrayList<String>>();
        ArrayList<String> item1 = new ArrayList<>();
        item1.add("1000");
        item1.add("Banana");
        item1.add("4");
        data2.put(1,item1);
        ArrayList<String> item2 = new ArrayList<>();
        item2.add("1001");
        item2.add("Apple");
        item2.add("4");
        data2.put(2,item2);

        int idOrder = tr.getOperations().addOrder(data1, data2);
        boolean b=tr.getOperations().searchOrder(idOrder);
        assertTrue(b);
    }

    @Test
    public void addTransport()
    {
        Dictionary<String, String> addTransport = new Hashtable<String, String>();
        addTransport.put("idT", "1001");
        addTransport.put("idD", "1001");
        int idOrder = tr.getOperations().getOrderRepo().getMaxId();
        addTransport.put("idO",Integer.toString(idOrder));
        int idTransport = tr.addTransport(addTransport);
        boolean bt=tr.searchTransport(idTransport);
        assertTrue(bt);

    }

    @Test
    public void removeTruck()
    {
        Dictionary<String, String> removeTruck = new Hashtable<String, String>();
        removeTruck.put("id", "1001");
        boolean b = tr.getLogistics().remove(removeTruck);
        assertTrue(b);
    }
    @Test
    public void removeDriver()
    {
        Dictionary<String, String> removeDriver = new Hashtable<String, String>();
        removeDriver.put("id", "1001");
        boolean b = tr.getLogistics().removeDriver(removeDriver);
        assertTrue(b);
    }



    @Test
    public void removeSite1()
    {
        Dictionary<String, String> removeSite = new Hashtable<String, String>();
        removeSite.put("id", "100");

        boolean b = tr.getOperations().removeSite(removeSite);
        assertFalse(b);
    }
    @Test
    public void removeSite2()
    {
        Dictionary<String, String> removeSite2 = new Hashtable<String, String>();
        removeSite2.put("id", "1000");
        boolean b = tr.getOperations().removeSite(removeSite2);
        assertTrue(b);
    }
    @Test
    public void removeSite3()
    {
        Dictionary<String, String> removeSite3 = new Hashtable<String, String>();
        removeSite3.put("id", "1001");
        boolean b = tr.getOperations().removeSite(removeSite3);
        assertTrue(b);
    }
    @Test
    public void removeOrder()
    {
        Dictionary<String, String> removeOrder = new Hashtable<String, String>();
        int idOrder = tr.getOperations().getOrderRepo().getMaxId();
        removeOrder.put("id", Integer.toString(idOrder));
        boolean b = tr.getOperations().removeOrder(removeOrder);
        assertTrue(b);
    }
    @Test
    public void removeTransport()
    {
        int idTransport = tr.getTransportRepo().getMaxId();
        boolean b = tr.remove(idTransport);
        assertTrue(b);
    }



}
