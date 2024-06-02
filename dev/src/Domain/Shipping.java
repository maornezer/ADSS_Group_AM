package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Shipping {
    private int shippingID;
    private LocalDate shippingDate;
    private LocalTime departureTime;
    private Driver driver;
    private Truck truck;
    private ShippingStatus status;
    private ShippingZone zone;
    private DeliveryManager deliveryManager;//?


    //without truck and driver
    public Shipping(int shippingID, ShippingZone s_zone, LocalDate ShippingDate, LocalTime DepartureTime) {
        setShippingID(shippingID);
        setShippingDate(ShippingDate);
        setDepartureTime(DepartureTime);
        setZone(s_zone);
        status = ShippingStatus.StandBy;
        deliveryManager = new DeliveryManager();
    }
    //with truck and driver
    public Shipping(int shippingID, ShippingZone s_zone, LocalDate shippingDate, LocalTime departureTime,Driver driver, Truck truck) {
        setShippingID(shippingID);
        setZone(s_zone);
        setShippingDate(shippingDate);
        setDepartureTime(departureTime);
        setDriver(driver);
        setTruck(truck);
        setStatus(ShippingStatus.StandBy);
        deliveryManager = new DeliveryManager();
        //checkAndSetErrorStatus();
    }


    //get//
    public int getShippingID() {
        return shippingID;
    }
    public LocalDate getShippingDate() {
        return shippingDate;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public Driver getDriver() {
        return driver;
    }
    public Truck getTruck() {
        return truck;
    }
    public ShippingStatus getStatus() {
        return status;
    }
    public ShippingZone getZone() {
        return zone;
    }
    public TypeOfLicense getLicenseForThisTruck(){
        return truck.getTypeOfLicense();
    }
    public Site getDestination(int siteId) {
        return deliveryManager.getSite(siteId);
    }
    public int countDestinations() {
        return deliveryManager.shipmentRoute.size();
    }
    public int getDestinationIndex(Site site){
        return deliveryManager.getShipmentRoute().indexOf(site);
    }


    //set//
    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public void setShippingDate(LocalDate shippingDate)//to continue -ShiftController
    {
        if (shippingDate != null) {
            this.shippingDate = shippingDate;
        }
    }
    //    public void setShippingDate(LocalDate newDate) {
    //        if(!status.isViableToEdit()){
    //            throw new InvalidShipmentEditingException("",status);}
    //        setDriver(null);
    //        if(shippingDate!=null&&getShippingStartTime()!=null)
    //            ShiftController.getInstance().removeDriverNeededToShift(getShippingDate(),getShippingStartTime());
    //        shippingDate = newDate;
    //        if(shippingDate!=null&&shippingStartTime!=null) {
    //            ShiftController.getInstance().addDriverNeededToShift(getShippingDate(), shippingStartTime);}
    //        checkAndSetErrorStatus();}


    public void setDepartureTime(LocalTime departureTime)//to continue -ShiftController
    {
        if (departureTime != null) {
            this.departureTime = departureTime;
        }
    }
    //    public void setShippingStartTime(LocalTime newStartTime) {
    //        if(!status.isViableToEdit()){
    //            throw new InvalidShipmentEditingException("",status);}
    //        if(shippingStartTime!=null&&getShippingStartTime()!=null)
    //            try{
    //                ShiftController.getInstance().removeDriverNeededToShift(getShippingDate(),getShippingStartTime());}
    //            catch (Exception ex){}
    //        setDriver( null);
    //        shippingStartTime = newStartTime;
    //        if(shippingDate!=null&&shippingStartTime!=null)
    //            ShiftController.getInstance().addDriverNeededToShift(shippingDate,shippingStartTime);
    //        checkAndSetErrorStatus();}

    public void setDriver(Driver driver)//to continue -ShiftController
    {
        this.driver = driver;
    }
    //    public void setDriver(Driver newDriver)  {
    //        if(!status.isViableToEdit()){
    //            throw new InvalidShipmentEditingException("",status);}
    //        if(driver!=null&&getShippingDate()!=null&getShippingStartTime() !=null && driver != newDriver){
    //            ShiftController.getInstance().removeDriverFromShift(driver.getId(),getShippingDate(),getShippingStartTime());
    //            ShiftController.getInstance().addDriverNeededToShift(getShippingDate(),getShippingStartTime());}
    //        driver = newDriver;
    //        if(driver!=null){
    //            ShiftController.getInstance().addDriverToShift(newDriver.id,shippingDate,shippingStartTime);
    //            ShiftController.getInstance().removeDriverNeededToShift(shippingDate,shippingStartTime);}
    //        checkAndSetErrorStatus();}
    public void setTruck(Truck truck)
    {
        this.truck = truck;
        //checkAndSetErrorStatus();
    }
    public void setStatus(ShippingStatus status)
    {
        this.status = status;
    }
    public void setZone(ShippingZone zone)
    {
        this.zone = zone;
    }

    //אין הוספות אחראיות הקונטרולר
    public void addDes(Site site)
    {
        deliveryManager.addSite(site);
    }

    public void removeDes(Site site)
    {
        deliveryManager.removeSite(site);
        //checkAndSetErrorStatus();
    }

    public boolean addItem(int siteID, String name, int amount, double weight) throws Exception {
        deliveryManager.addItemToShipment(getDestination(siteID),name,amount,weight);
        //checkAndSetErrorStatus();
        return true;
    }
    public boolean editItemAmount(int siteID, String name, int amount) throws Exception{
        deliveryManager.editItemAmount(getDestination(siteID),name,amount);
        //checkAndSetErrorStatus();
        return true;
    }
    public boolean removeItem(int siteID,String name){
        deliveryManager.removeItem(getDestination(siteID),name);
        //checkAndSetErrorStatus();
        return true;
    }

    public boolean ensureShipmentWeight()
    {
        if (deliveryManager.validShipmentWeight(truck.getMaxWeight()) < 0)
        {
            return false;
        }
        return true;
    }
    public boolean isDriverCanDrive(Driver driver)
    {
        if(driver == null || truck == null ){
            return false;
        }
        if (!ensureShipmentWeight()){
            return false;
        }
        return true;
    }

    @Override//why override? and check if this need to be in controller
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null || getClass() != other.getClass())
        {
            return false;
        }
        Shipping shipment = (Shipping) other;
        return shippingID == shipment.shippingID;
    }
    @Override
    public int hashCode() {
        return Objects.hash(shippingID);
    }

    public boolean ensureShipmentValid(){
        if (shippingDate == null || departureTime == null || driver == null || truck ==null) {
            return false;
        }
        return deliveryManager.isShipmentInfoValid();
    }
    public double getShipmentRouteWeight()
    {
        int lastSiteIndex = countDestinations();// maybe -1
        return deliveryManager.getWeightUntilSiteX(lastSiteIndex);
    }
    public TypeOfLicense getRequiredLicence() {
        double weight;
        if (truck == null) {
            weight = 0;
        }
        else
        {
            weight = truck.getCurrWeight();
        }

        double maxShipmentWeight = getShipmentRouteWeight();
        return TypeOfLicense.WhichTypeOfLicense(maxShipmentWeight);
    }

    public boolean checkAndSetErrorStatus(){
        if (status == ShippingStatus.Done || status == ShippingStatus.InProgress){
            return false;
        }
        if(!ensureShipmentValid()){
            if(status!=ShippingStatus.ShipmentError){
                status = status.setShipmentError();
            }
            return true;
        }
        if(!ensureShipmentWeight()){
            if(status!=ShippingStatus.TruckError){
                status = status.setTruckError();
            }
            return true;
        }
        if(!isDriverCanDrive(driver)){
            if(status!=ShippingStatus.DriverError){
                status = status.setDriverError();
            }
            return true;
        }
        for (Site site: deliveryManager.getShipmentRoute()){
            if(getShippingDate()!=null&&site.getSiteType()== Site.SiteType.Branch){
                status = status.setSiteError();
                return true;
            }
        }
        return false;
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
        public static ShippingStatus getStatusFromString(String status){
        switch (status.toLowerCase()){
            case ("done") :
                return ShippingStatus.Done;
            case ("inprogress"):
                return ShippingStatus.InProgress;
            case ("siteerror") :
                return ShippingStatus.SiteError;
            case ("shipmenterror"):
                return ShippingStatus.ShipmentError;
            case ("drivererror") :
                return ShippingStatus.DriverError;
            case ("truckerror") :
                return ShippingStatus.TruckError;
            case ("itemerror") :
                return ShippingStatus.ItemError;
            default:
                return ShippingStatus.StandBy;
        }
    }
    private static ShippingZone getZoneFromString(String zoneFromString) {
        switch (zoneFromString.toLowerCase()){
            case "south":
                return ShippingZone.South;
            case "north":
                return ShippingZone.North;
            case "east":
                return ShippingZone.East;
            case "west":
                return ShippingZone.West;
            case "center":
                return ShippingZone.Center;
            default:
                throw new IllegalArgumentException("Invalid string to get zone");
        }
    }
    public void setZoneFromString(String zoneFromString) {
        switch (zoneFromString){
            case "S":
                zone = ShippingZone.South;
                break;
            case "N":
                zone = ShippingZone.North;
                break;
            case "E":
                zone = ShippingZone.East;
                break;
            case "W":
                zone = ShippingZone.West;
                break;
            case "C":
                zone = ShippingZone.Center;
                break;
            default:
                throw new IllegalArgumentException("Invalid string to get zone");
        }
        //checkAndSetErrorStatus();
    }
    public enum ShippingZone
    {
        North, South, East, West, Center;
    }
    public enum ShippingStatus
    {
        TruckError, DriverError, ShipmentError, ItemError, SiteError, StandBy, InProgress, Done;
        public boolean isErrorStatus()
        {
            return switch (this) {
                case TruckError, ShipmentError, DriverError, SiteError, ItemError -> true;
                default -> false;
            };
        }
        public boolean isViableToEdit()
        {
            return this!=InProgress && this!=Done;
        }
        public ShippingStatus setTruckError(){
            return TruckError;
        }
        public ShippingStatus setDriverError(){
            return DriverError;
        }
        public ShippingStatus setShipmentError(){return ShipmentError;}
        public ShippingStatus setItemError() { return ItemError;}
        public ShippingStatus setSiteError() { return SiteError;}
    }
}
