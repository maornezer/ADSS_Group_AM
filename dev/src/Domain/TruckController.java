package Domain;

import java.util.ArrayList;
import java.util.List;


public class TruckController
{
    private List <Truck> trucks;
    private static TruckController instance;

    private TruckController()
    {
        trucks = new ArrayList<>();
        instance=this;
    }

    public static TruckController getInstance()
    {
        if(instance == null)
            instance = new TruckController();
        return instance;
    }
    public boolean addTruck(String truckModel, TypeOfLicense typeOfLicense, double initialWeight, double maxWeight)
    {
        Truck t = new Truck(truckModel, typeOfLicense, initialWeight, maxWeight );
        return addTruck(t);
    }
    public boolean addTruck(Truck t)
    {
        if (isTruckExists(t))
        {
            return false;
        }
        return trucks.add(t);
    }

    public boolean isTruckExists(Truck truck)
    {
    return trucks.contains(truck);
    }
    public Truck getTruckByLicenceType(TypeOfLicense type )
    {
        for (Truck truck : trucks)
        {
            if (truck.getTypeOfLicense().equals(type)) {
                return truck;
            }
        }
        return null;//throw new IllegalArgumentException("Truck with license type " + type + " not found");

    }

    public Truck getTruckByLicencePlate(int lp )
    {
        for (Truck truck : trucks) {
            if (truck.getLicensePlate() == lp) {
                return truck;
            }
        }
        return null;//throw new IllegalArgumentException("Truck with license plate " + lp + " not found");

    }

    private List<Truck> getAllTrucksByLicence(TypeOfLicense licenceType)
    {
        List<Truck> trucksByLicence = new ArrayList<>();
        for (Truck truck : trucks) {
            if (truck.getTypeOfLicense().equals(licenceType)) {
                trucksByLicence.add(truck);
            }
        }
        return trucksByLicence;
    }
    public boolean removeTruckByLicencePlate(int lp)
    {
        for (int i = 0; i<trucks.size(); i++)
        {
            if(trucks.get(i).getLicensePlate() == lp)
            {
                trucks.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getAmountOfTrucks(){return trucks.size();}


    }

