package Domain;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Transport
{
        private static int id = 1;
        private LocalDate date;
        private LocalTime time;
        private String zone;
        private Driver driver;
        private Truck truck;
        private ArrayList<Order> myOrders;
        private boolean changeDestination;
        private boolean unloadingItems;
        private boolean changeTruck;


        public Transport(Truck truck, Driver driver)
        {
                setId(id);
                setTruck(truck);
                setDriver(driver);
                setTime();
                myOrders = new ArrayList<>();
                id++;
                changeDestination = false;
                unloadingItems = false;
                changeTruck = false;
        }

        public void setId(int id)
        {
                Transport.id = id;
        }

        public void setDate(LocalDate date)
        {
                this.date = date;
        }

        public void setTime()
        {
                this.time = LocalTime.of(8, 0);;
        }

        public void setZone(String zone)
        {
                this.zone = zone;
        }

        public void setDriver(Driver driver)
        {
                if (driver != null)
                {
                        this.driver = driver;
                }
        }

        public void setTruck(Truck truck)
        {
                if (driver != null)
                {
                        this.truck = truck;
                }
        }



        public void setChangeTruck() {changeTruck = true;}


        public void setUnloadingItems() {unloadingItems = true;}


        public void setChangeDestination() {changeDestination = true; }

        public int getId() {
                return id;
        }
        public LocalDate getDate() {
                return date;
        }

        public LocalTime getTime() {
                return time;
        }

        public String getZone() {
                return zone;
        }

        public Driver getDriver() {
                return driver;
        }

        public Truck getTruck() {
                return truck;
        }
        public boolean addOrderToMYTransport(Order order)
        {
                if(myOrders.isEmpty())
                {
                        setDate(order.getDate());
                        setZone(order.getSource().getSiteZone());
                }
               return myOrders.add(order);

        }
        public Order getOrderByID(int orderID)
        {
                for (Order order : myOrders)
                {
                        if ( order.getId()== orderID)
                                return order;
                }
                return null;
        }

        public ArrayList<Order> getMyOrders() {
                return myOrders;
        }

        public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("Transport ID: ").append(id).append("\n");
                sb.append("Date: ").append(date).append("\n");
                sb.append("Departure Time: ").append(time).append("\n");
                sb.append("Truck Number: ").append(truck.getIdTruck()).append("\n");
                sb.append("Driver Name: ").append(driver.getName()).append("\n");
                sb.append("Source: ").append(zone).append("\n");
                sb.append("Destinations:").append("\n");
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

