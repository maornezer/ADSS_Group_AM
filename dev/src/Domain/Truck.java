package Domain;
public class Truck
{
    private double initialWeight;
    private double currWeight;
    private double maxWeight;
    private static int licensePlate =1;
    private TypeOfLicense typeOfLicense;
    private String truckModel;

    public Truck(String model,TypeOfLicense typeLicense,double initialWeight,double maxWeight )
    {
        setTruckModel(model);
        setTypeOfLicense( typeLicense);
        setInitialWeight(initialWeight);
        setMaxWeight(maxWeight);
        setAddToCurrWeight(initialWeight);
        licensePlate ++;

    }


    public boolean setInitialWeight(double num)
    {
        if (num >= 0)
        {
            this.initialWeight = num;

        }
        return num >= 0 ;
    }
    public boolean setAddToCurrWeight(double num)
    {
        if (num >= 0) {
            this.currWeight += num;
        }
        return num >= 0 ;
    }
    public boolean setSubFromCurrWeight(double num)
    {
        if (num >= 0) {
            this.currWeight -= num;
        }
        return num >= 0 ;

    }

    public boolean setMaxWeight(double maxWeight) {
        if (maxWeight >= 0)
        {
            this.maxWeight = maxWeight+initialWeight;
        }
        return maxWeight >= 0 ;

    }

    public void setTypeOfLicense(TypeOfLicense typeOfLicense) {
        this.typeOfLicense = typeOfLicense;
    }

    public void setTruckModel(String truckModel) {

        this.truckModel = truckModel;
    }

    public double getInitialWeight() {
        return initialWeight;
    }
    public double getCurrWeight() {
        return currWeight;
    }
    public double getMaxWeight() {
        return maxWeight;
    }

    public int getLicensePlate() {return licensePlate;}

    public TypeOfLicense getTypeOfLicense() {
        return typeOfLicense;
    }

    public String getTruckModel() {
        return truckModel;
    }

    public boolean equals(Truck t)
    {
        if ( t == null)
        {
            return false;
        }
        return this.licensePlate == t.licensePlate;
    }

}
//("Licence Number:\t  %s,\nModel: \t %s, \nWeight:\t%3.3f\nMax Carry Weight: \t%3.3f \n ",licenceNumber,model,weight,maxCarryWeight);