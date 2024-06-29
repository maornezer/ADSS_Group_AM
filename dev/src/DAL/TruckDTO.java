package DAL;


public class TruckDTO
{
     public double initialWeight;
     public double maxWeight;
     public String model;
     public int id;

     public TruckDTO(double initialWeight, double maxWeight, String model,int id) {
          this.id = id;
          this.model = model;
          this.maxWeight = maxWeight;
          this.initialWeight = initialWeight;
     }
}
