package Domain;

import java.sql.Time;
import java.util.ArrayList;


public record Transport (
        int transportID,
        Time departureTime,
        TransportZone zone,
        Driver driver,
        Truck truck,
        ArrayList<Order> orders
        ) {
        public enum  TransportZone
        {
                NORTH,
                SOUTH,
                CENTER;
        }
}

