package Domain;

public class Driver
{
    private String name;
    private int id;
    private  TypeOfLicense typeOfLicense;

    public Driver(String name, int id, TypeOfLicense typeOfLicense)
    {
        //בדיקות קלט?
        this.name = name;
        this.id = id;
        this.typeOfLicense = typeOfLicense;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public TypeOfLicense getTypeOfLicense() {
        return typeOfLicense;
    }

    public void setTypeOfLicense(TypeOfLicense type) {
        this.typeOfLicense = type;
    }
    public int getMaxWeightByLicence()
    {
        return typeOfLicense.LimitWeight();
    }

//    public String toString() {
//        return "Name: " + name + "ID: " + id + "Type of licence: " + typeOfLicense;
//    }
    //("Name: %s\t ID: %s\tLicenceType: %s\t ",
    //getName(),getId(),getTypeOfLicense().toString());

    }
