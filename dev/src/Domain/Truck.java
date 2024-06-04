package Domain;
public class Truck
{
    private double initialWeight;
    private double currWeight;
    private double maxWeight;
    private int id;
    private String model;


    public Truck(int id, double initialWeight, double maxWeight, String model )
    {
        setIdTruck(id);
        setInitialWeight(initialWeight);
        setMaxWeight(maxWeight);
        setTruckModel(model);
        setAddToCurrWeight(initialWeight);

    }

    public boolean setIdTruck(int id) {
        if (id < 0)
        {
            return false;
        }
        this.id = id;
        return true;
    }
    public boolean setInitialWeight(double num) {
        if (id < 0)
        {
            return false;
        }
        this.initialWeight = num;
        return true;
    }
    public boolean setAddToCurrWeight(double num) {
        if (id < 0)
        {
            return false;
        }
        this.currWeight += num;
        return true;
    }
    public void setSubFromCurrWeight(double num)
    {
        this.currWeight -= num;
    }

    public boolean setMaxWeight(double maxWeight) {
        if (maxWeight < 0)
        {
            return false;
        }
        this.maxWeight = maxWeight+initialWeight;
        return true;
    }
    public boolean setTruckModel(String truckModel)
    {
        if (model.isBlank() || !model.chars().allMatch(Character::isLetter))
        {
            return false;
        }
        this.model = truckModel;
        return true;
    }


    public int getIdTruck() {return id;}
    public double getInitialWeight() {return initialWeight;}
    public double getCurrWeight() {return currWeight;}
    public double getMaxWeight() {return maxWeight;}
    public String getTruckModel() {return model;}

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
        return id == truck.id;
    }
    @Override
    public int hashCode()
    {
        return Integer.hashCode(id);
    }

    public String getTypeOfLicense()
    {
        return WhichTypeOfLicense(maxWeight);
    }

    public String WhichTypeOfLicense(double w)
    {
        if (w <= 2000.0 && w >= 0.0 )
        {
            return "B";
        }
        else if (w > 2000.0 && w < 8000.0)
        {
            return "C";
        }
        else return "D";
    }

    public String toString()
    {
        return "Truck ID: " + id + ", Model: " + model + ", Initial Weight: " + initialWeight +
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