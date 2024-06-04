package Domain;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Transport
{
        private static int id=1;
        private LocalDate date;
        private LocalTime time;
        private String zone;
        private Driver driver;
        private Truck truck;
        private ArrayList<Order> orders;

        public Transport(Truck truck, Driver driver)
        {
                setId(id);
                setTruck(truck);
                setDriver(driver);
                orders = new ArrayList<>();
                id++;
        }

        public void setId(int id)
        {
                Transport.id = id;
        }

        public boolean setDate(LocalDate date)
        {
                if(!orders.isEmpty())
                {
                        this.date = orders.getFirst().getDate();
                        return true;
                }
                return false;
        }

        public void setTime()
        {
                this.time = LocalTime.of(8, 0);;
        }

        public boolean setZone()
        {
                if(!orders.isEmpty())
                {
                        this.zone = orders.getFirst().getDestination().getSiteZone();
                        return true;
                }
                return false;
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


}



