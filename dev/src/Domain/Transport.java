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

        public Transport(Truck truck, Driver driver)
        {
                setId(id);
                setTruck(truck);
                setDriver(driver);
                setTime();
                myOrders = new ArrayList<>();
                id++;
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

        public String toString()
        {
                return "to do";
        }

}



