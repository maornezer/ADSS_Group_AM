package Domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Driver {
    private String name;
    private int id;
    private String typeOfLicense;
    private ArrayList<LocalDate> shifts;

    public Driver(String name, int id, String typeOfLicense) {
        setName(name);
        setId(id);
        setTypeOfLicense(typeOfLicense);
        shifts = new ArrayList<>();
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

    public void setShifts(ArrayList<LocalDate> shifts) {
        this.shifts = shifts;
    }
    public boolean addDateToShifts(LocalDate date)
    {
        for (LocalDate d : shifts)
        {
            if (d == date)
            {
                shifts.add(date);
                return true;
            }
        }
        return false;
    }
    public boolean removeDataFromShifts(LocalDate date)
    {
        for (LocalDate d : shifts)
        {
            if (d == date)
            {
                shifts.remove(date);
                return true;
            }
        }
        return false;
    }
    public ArrayList<LocalDate> getShifts() {
        return shifts;
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
