package Domain;

import DAL.TruckDTO;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public Truck(TruckDTO truckDTO) {
        setIdTruck(truckDTO.id);
        setInitialWeight(truckDTO.initialWeight);
        setMaxWeight(truckDTO.maxWeight);
        setTruckModel(truckDTO.model);
        setAddToCurrWeight(truckDTO.initialWeight);
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
        if (truckModel.isBlank() || !truckModel.chars().allMatch(Character::isLetter))
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


