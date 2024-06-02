package Domain;
public class Truck
{
    private double initialWeight;
    private double currWeight;
    private double maxWeight;
    private int idTruck;
    private String truckModel;


    //private static int nextLicensePlate = 1;
    //private TypeOfLicense typeOfLicense;


    public Truck(int idTruck, double initialWeight, double maxWeight, String model )
    {
        setIdTruck(idTruck);
        setInitialWeight(initialWeight);
        setMaxWeight(maxWeight);
        setTruckModel(model);
        setAddToCurrWeight(initialWeight);
        //this.licensePlate = nextLicensePlate ++;

    }

    public void setIdTruck(int idTruck) {this.idTruck = idTruck;}
    public void setInitialWeight(double num) {this.initialWeight = num; }
    public void setAddToCurrWeight(double num) {this.currWeight += num;}
    public void setSubFromCurrWeight(double num) { this.currWeight -= num;}
    public void setMaxWeight(double maxWeight) {this.maxWeight = maxWeight+initialWeight;}
    public void setTruckModel(String truckModel) {this.truckModel = truckModel;}


    public int getIdTruck() {return idTruck;}
    public double getInitialWeight() {return initialWeight;}
    public double getCurrWeight() {return currWeight;}
    public double getMaxWeight() {return maxWeight;}
    public String getTruckModel() {return truckModel;}

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Truck truck = (Truck) obj;
        return idTruck == truck.idTruck;
    }
    @Override
    public int hashCode()
    {
        return Integer.hashCode(idTruck);
    }

    public TypeOfLicense getTypeOfLicense()
    {
        return TypeOfLicense.WhichTypeOfLicense(maxWeight);
    }
    public String toString()
    {
        return "Truck ID: " + idTruck + ", Model: " + truckModel + ", Initial Weight: " + initialWeight +
                ", Max Weight: " + maxWeight + ", License required: " + getTypeOfLicense();
    }

}


//1,2000.0,10000.0,Volvo
//2,3000.0,12000.0,Scania
//3,2500.0,11000.0,Mercedes
//4,2200.0,10500.0,MAN
//5,2800.0,11500.0,DAF
//6,2900.0,11600.0,Iveco
//7,2700.0,11400.0,Renault
//8,2300.0,10600.0,Volvo
//9,2400.0,10800.0,Scania
//10,2600.0,11200.0,Mercedes