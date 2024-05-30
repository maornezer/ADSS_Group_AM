package Domain;

import java.util.ArrayList;


//singleTone

public class TruckController
{
    ArrayList <Truck> trucks;

    public TruckController() {
    }

    public boolean addTruck(String truckModel, TypeOfLicense typeOfLicense, double initialWeight, double maxWeight)
    {
        boolean bool = false;
        Truck t = new Truck(truckModel,typeOfLicense,initialWeight,maxWeight );
        for (int i=0; i<trucks.size(); i++)
        {
            if (t.equals(trucks.get(i)))
                return false;
        }
        bool = trucks.add(t);
        return bool;
    }
    //remove??
    public boolean addTruck(Truck t)
    {
        for (int i=0; i<trucks.size(); i++)
        {
            if (t.equals(trucks.get(i)))
                return false;
        }
        return trucks.add(t);
    }
    public Truck getTruckByLicenceType(TypeOfLicense type )
    {
        for (Truck truck : trucks) {
            if (truck.getTypeOfLicense().equals(type)) {
                return truck;
            }
        }
        return null;//exception
    }
    public Truck getTruckByLicencePlate(int platenum )
    {
        for (Truck truck : trucks) {
            if (truck.getLicensePlate() == platenum) {
                return truck;
            }
        }
        return null;//exception
    }
    public Truck getTruckByTypeLicence(TypeOfLicense type)
    {
        for (Truck truck : trucks) {
            if (truck.getTypeOfLicense().equals(type)) {
                return truck;
            }
        }
        return null;//exception
    }
    private ArrayList<Truck> getAllTrucksByLicence(TypeOfLicense licenceType)
    {
        ArrayList<Truck> trucksByLicence = new ArrayList<>();
        for (Truck truck : trucks) {
            if (truck.getTypeOfLicense().equals(licenceType)) {
                trucksByLicence.add(truck);
            }
        }
        return trucksByLicence;


    }
    public boolean removeTruckByLicencePlate(int LP)
    {
        for (int i=0; i<trucks.size(); i++)
        {
            if(trucks.get(i).getLicensePlate() == LP)
            {
                trucks.remove(trucks.get(i));
                return true;
            }
        }
        return false;
    }





    }

