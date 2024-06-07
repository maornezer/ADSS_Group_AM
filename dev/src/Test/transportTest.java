package Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import Domain.*;
//import org.junit.jupiter.api.Assertions;



public class transportTest
{
    private TransportController tr;

    
    public void setUp()
    {
        tr = new TransportController();
        // Adding some initial data
        tr.getDomain().addTruck(new Truck(1, 1000, 2000, "Model A"));
        tr.getDomain().addDriver(new Driver("John Doe", 1, "B"));
        tr.getDomain().addSite(new Site("123 Main St", "Zone A", "Alice", "1234567890"));
        tr.getDomain().addSite(new Site("456 Elm St", "Zone A", "Bob", "0987654321"));

    }




}
