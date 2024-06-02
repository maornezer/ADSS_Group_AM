package Domain;

import java.sql.Time;


public record Transport
        (     int transportID,
              Time departureTime,
              Shipping.ShippingZone zone,
              Driver driver,
              Truck truck
        ){}