package Domain;

import DAL.DriverDTO;

public class Driver {
    private String name;
    private int id;
    private String typeOfLicense;

    public Driver(String name, int id, String typeOfLicense) {
        setName(name);
        setId(id);
        setTypeOfLicense(typeOfLicense);
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
