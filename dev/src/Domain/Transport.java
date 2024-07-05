package Domain;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Transport
{
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String zone;
    private Driver driver;
    private Truck truck;
    private ArrayList<Order> myOrders;
    private boolean changeDestination;
    private boolean unloadingItems;
    private boolean changeTruck;


    public  Transport(int id,Truck truck, Driver driver, Order order)
    {
        setId(id);
        setTruck(truck);
        setDriver(driver);
        setTime();
        setDate(order.getDate());
        setZone(order.getDestination().getSiteZone());
        myOrders = new ArrayList<>();
        myOrders.add(order);
        changeDestination = false;
        unloadingItems = false;
        changeTruck = false;
    }

    public  Transport(Truck truck, Driver driver,int id)
    {
        setTruck(truck);
        setDriver(driver);
        setTime();
        setId(id);
        changeDestination = false;
        unloadingItems = false;
        changeTruck = false;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {return date;}


    public String getZone() {
        return zone;
    }

    public Driver getDriver() {
        return driver;
    }

    public Truck getTruck() {
        return truck;
    }

    public ArrayList<Order> getMyOrders() {
        return myOrders;
    }

    public void setId(int id) {
                this.id = id;
        }

    public void setDate(LocalDate date)
    {
            this.date = date;
    }

    public void setTime() {this.time = LocalTime.of(8, 0);;}

    public void setMyOrders(ArrayList<Order> myOrders) {this.myOrders = myOrders;}

    public void setZone(String zone)
    {
            this.zone = zone;
    }


    public void setUnloadingItems() {unloadingItems = true;}


    public void setDriver(Driver driver){
        if (driver != null){
            this.driver = driver;
        }
    }

    public void setTruck(Truck truck){
        if (truck != null){
            this.truck = truck;
        }
    }

    public String toStringTransportReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transport ID: ").append(id).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Departure Time: ").append(time).append("\n");
        sb.append("Truck Number: ").append(truck.getIdTruck()).append("\n");
        sb.append("Driver Name: ").append(driver.getName()).append("\n");
        sb.append("Zone: ").append(zone).append("\n");
        sb.append("Destinations: ").append("\n");
        for (Order order : myOrders) {
                sb.append("  - ").append(order.getDestination().getAddress()).append("\n");
        }
        if(changeDestination){
                sb.append("A change was made in the transport. The solution was to change the destination ").append("\n");
        }
        if(unloadingItems){
                sb.append("A change was made in the transport. The solution was to unloading items ").append("\n");
        }
        if(changeTruck){
                sb.append("A change was made in the transport. The solution was to change the truck ").append("\n");
        }
        return sb.toString();
    }

}

