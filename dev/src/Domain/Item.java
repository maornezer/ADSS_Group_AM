package Domain;

public class Item
{
     private int id;
     private String name;
     private int amount;

     public Item(int id, String name, int amount) {
          this.id = id;
          this.amount = amount;
          this.name = name;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public int getAmount() {
          return amount;
     }

     public boolean setAmount(int amount) {
          this.amount = amount;
          return true;
     }
     public boolean subAmount(int amount) {
          this.amount -= amount;
          return true;
     }



     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     @Override
     public String toString()
     {
          return "Item ID: " + id + ", Name: " + name + ", Amount: " + amount ;

     }
}
