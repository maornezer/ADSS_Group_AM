package Domain;

import java.util.ArrayList;


//singleTone

public class TruckController
{
    ArrayList <Truck> trucks;



    //////
    public boolean addTruck(int LicensePlate, String truckModel, TypeOfLicense typeOfLicense, double initialWeight, double maxWeight)
    {
        Truck t = new Truck(LicensePlate, truckModel, typeOfLicense, initialWeight, maxWeight);
        for (int i=0; i<trucks.size(); i++)
        {
            if (t.equals(trucks.get(i)))
                return false;
        }
        trucks.add(t);
        return true;
    }
    //remove??
    public boolean addTruck(Truck t)
    {
        for (int i=0; i<trucks.size(); i++)
        {
            if (t.equals(trucks.get(i)))
                return false;
        }
        trucks.add(t);
        return true;
    }
    public Truck getTruckByLicenceType(String type )
    {
        for (int i=0; i<trucks.size(); i++)
        {
            if (trucks.get(i).getTypeOfLicense().equals(type))
            {
                return trucks.get(i);
            }
        }
        return null;//exception
    }
    public Truck getTruckByLicencePlate(int platenum )
    {
        for (int i=0; i<trucks.size(); i++)
        {
            if (trucks.get(i).getLicensePlate()==platenum)
            {
                return trucks.get(i);
            }
        }
        return null;//exception
    }
    //String to sting ??

}

