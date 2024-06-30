package Domain;

import DAL.ItemDTO;

public class Item
{
     private int id;
     private String name;
     private int amount;
     private int idO;
     public Item(int id, String name, int amount) {
          setId(id);
          setAmount(amount);
          setName(name);
     }

     public Item(ItemDTO itemDTO) {
          setId(itemDTO.id);
          setAmount(itemDTO.amount);
          setName(itemDTO.name);
          this.idO=itemDTO.id0;

     }
     public int getIdO() {return idO;}

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public int getAmount() {return amount;}

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
     public String toString() {
          return "Item ID: " + id + ", Name: " + name + ", Amount: " + amount ;
     }
}
