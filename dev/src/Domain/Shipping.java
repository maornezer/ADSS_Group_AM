package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Shipping
{
    private int ShippingID;

    private LocalDate ShippingDate;
    private LocalTime DepartureTime;
    private Driver driver;
    private Truck truck;
    ShippingStatus status;
    ShippingZone zone;
    ShipmentInventoryManager invManager;


    public Shipping(Driver driver, Truck truck, LocalDate shippingDate, LocalTime departureTime) {
        this.driver = driver;
        this.truck = truck;
        this.ShippingDate = shippingDate;
        this.DepartureTime = departureTime;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public LocalDate getShippingDate() {
        return ShippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        if (shippingDate != null)
        {
            ShippingDate = shippingDate;
        }
    }

    public LocalTime getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        if(departureTime!=null)
        {
            DepartureTime = departureTime;
        }
    }
    //add destination


}
