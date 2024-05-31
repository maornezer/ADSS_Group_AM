package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Shipping {
    private int ShippingID;

    private LocalDate ShippingDate;
    private LocalTime DepartureTime;
    private Driver driver;
    private Truck truck;
    ShippingStatus status;
    ShippingZone zone;
    //ShipmentInventoryManager invManager;


    public Shipping(int shippingID, ShippingZone s_zone, LocalDate ShippingDate, LocalTime DepartureTime) {
        setShippingID(shippingID);
        setShippingDate(ShippingDate);
        setDepartureTime(DepartureTime);
        setZone(s_zone);
        status = ShippingStatus.StandBy;
    }

    public Shipping(int ID, Driver driver, Truck truck, LocalDate shippingDate, LocalTime departureTime, ShippingZone shippingZone) {
        setShippingID(ID);
        setDriver(driver);
        setTruck(truck);
        setShippingDate(shippingDate);
        setDepartureTime(departureTime);
        setStatus(ShippingStatus.StandBy);
        setZone(shippingZone);
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
        if (shippingDate != null) {
            ShippingDate = shippingDate;
        }
    }

    public LocalTime getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        if (departureTime != null) {
            DepartureTime = departureTime;
        }
    }

    public int getShippingID() {
        return ShippingID;
    }

    public void setShippingID(int shippingID) {
        ShippingID = shippingID;
    }
    //add destination


    public ShippingStatus getStatus() {
        return status;
    }

    public void setStatus(ShippingStatus status) {
        this.status = status;
    }

    public ShippingZone getZone() {
        return zone;
    }

    public void setZone(ShippingZone zone) {
        this.zone = zone;
    }

    public boolean isShippingInProgress()
    {
        return status == ShippingStatus.InProgress;
    }
    public boolean isShippingIsComplete()
    {
        return status == ShippingStatus.Done;
    }
    public boolean isShippingError()
    {
        return (status == ShippingStatus.ItemError || status == ShippingStatus.DriverError || status == ShippingStatus.ShipmentError);
    }

    public TypeOfLicense getLicenseForThisTruck(){
        return truck.getTypeOfLicense();
    }
    public enum ShippingZone
    {
        North, South, East, West, Center;
    }
    public enum ShippingStatus
    {
        TruckError, DriverError, ShipmentError, ItemError, SiteError, StandBy, InProgress, Done;
    }


    }
