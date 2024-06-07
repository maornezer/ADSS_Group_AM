package Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import Domain.*;
import org.junit.jupiter.api.Assertions;

import java.util.Dictionary;
import java.util.Hashtable;


public class transportTest
{
    private TransportController tr;

    @BeforeEach
    public void setUp()
    {
        tr = new TransportController();
        tr.getDomain().addTruck(new Truck(1, 10000, 20000, "D"));
        tr.getDomain().addTruck(new Truck(2, 3000, 7000, "C"));
        tr.getDomain().addTruck(new Truck(3, 1000, 1500, "B"));

        tr.getDomain().addDriver(new Driver("Maor", 1, "D"));
        tr.getDomain().addDriver(new Driver("Ron", 2, "D"));
        tr.getDomain().addDriver(new Driver("Sahar", 3, "A"));
        tr.getDomain().addDriver(new Driver("Noa", 4, "A"));
        tr.getDomain().addDriver(new Driver("Lee", 5, "B"));


        tr.getDomain().addSiteToList(new Site("Narcissus ", "North", "Alice", "1234567890"));
        tr.getDomain().addSiteToList(new Site("Rose ", "North", "Bob", "0987654321"));
        tr.getDomain().addSiteToList(new Site("Daisy  ", "South", "Barbara", "09261"));
        tr.getDomain().addSiteToList(new Site("Tulip   ", "Center", "Marsel", "508068"));
        tr.getDomain().addSiteToList(new Site("Trumpeldor   ", "Center", "Trumpeldor", "1000"));

        tr.addTransport(1,1);
    }

    @Test
    public void testAddTransport1() {

        int result = tr.addTransport(1,1);
        assertEquals(0, result, "Expected addTransport to return 0 for successful addition.");
    }
    @Test
    public void testAddTransport2() {

        int result = tr.addTransport(1,2);
        assertEquals(1, result, "Expected addTransport to return 0 for successful addition.");
    }


    }
//    @AfterEach
//    public void delete(){}