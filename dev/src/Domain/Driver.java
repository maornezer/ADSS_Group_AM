package Domain;

import DAL.DriverDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Driver {
    private String name;
    private int id;
    private String typeOfLicense;
    private ArrayList<LocalDate> dates;

    public Driver(String name, int id, String typeOfLicense) {
        setName(name);
        setId(id);
        setTypeOfLicense(typeOfLicense);
        dates = new ArrayList<>();
    }

    public Driver(DriverDTO driverDTO)
    {
        setName(driverDTO.name);
        setId(driverDTO.id);
        setTypeOfLicense(driverDTO.typeOflicence);

    }

    public boolean setName(String name)
    {
        if (name.isBlank())
        {
            return false;
        }
        this.name = name;
        return true;
    }

    public void setDates(ArrayList<LocalDate> dates) {
        this.dates = dates;
    }
    public boolean addDate(LocalDate localDate)
    {
        if(checkShiftDate(localDate))
        {
            return false;
        }
        dates.add(localDate);
        return true;
    }
    public boolean removeDate(LocalDate localDate)
    {
        if(checkShiftDate(localDate))
        {
            dates.remove(localDate);
            return true;
        }
        return false;
    }
    public boolean checkShiftDate(LocalDate localDate)
    {
        for (LocalDate date: dates)
        {
            if (date.isEqual(localDate))
            {
                return true;
            }
        }
        return false;
    }

    public boolean setId(int id)
    {
        if (id < 0)
        {
            return false;
        }
        this.id = id;
        return true;
    }

    public void setTypeOfLicense(String type) {
        this.typeOfLicense = type;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getTypeOfLicense() {
        return typeOfLicense;
    }

    public String toString()
    {
        return "Name: " + name + ", ID: " + id + ", License type: " + typeOfLicense;
    }

}
