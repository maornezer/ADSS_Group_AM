package Domain;

public class Driver {
    private String name;
    private int id;
    private TypeOfLicense typeOfLicense;

    public Driver(String name, int id, TypeOfLicense typeOfLicense) {
        setName(name);
        setId(id);
        setTypeOfLicense(typeOfLicense);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeOfLicense(TypeOfLicense type) {
        this.typeOfLicense = type;
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

//    public int getMaxWeightByLicence() {
//        return typeOfLicense.LimitWeight();
//    }

    public String toString()
    {
        return "Name: " + name + ", ID: " + id + ", License type: " + typeOfLicense;
    }

}