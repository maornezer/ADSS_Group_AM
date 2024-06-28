package DAL;


public class TruckDTO
{
     int initialWeight;
     int maxWeight;
     String model;
     int id;

     public TruckDTO(int initialWeight, int maxWeight, String model,int id) {
          this.id = id;
          this.model = model;
          this.maxWeight = maxWeight;
          this.initialWeight = initialWeight;
     }
}
