package Domain;

import java.util.ArrayList;
import java.util.List;

public class DriverRepository {
    private ArrayList<Driver> drivers;

    public DriverRepository() {
        drivers = new ArrayList<>();
    }

    public boolean addDriver(Driver d) {
        if (isDriverExists(d)) {
            System.out.println("RON");
            return false;
        }
        return drivers.add(d);
    }

    public boolean isDriverExists(Driver d)
    {
        return drivers.contains(d);
    }
    public boolean isDriverExists(int id)
    {
        if (drivers != null)
        {
            for (Driver d : drivers)
            {
                if (d.getId()== id)
                {
                    return true;
                }

            }
        }
        return false;
    }
    public boolean isDriverExists(String type)
    {
        if (drivers != null)
        {
            for (Driver d : drivers)
            {
                if (d.getTypeOfLicense().compareTo(type)==0)
                {
                    return true;
                }

            }
        }
        return false;
    }

    public Driver getDriverByID(int id) {
        for (Driver d : drivers) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }
    public int getAmountOfDrivers(){return drivers.size();}

    public List<Driver> getDrivers() {
        return drivers;
    }

    public int getSizeDrivers() {
        return drivers.size();
    }
}
