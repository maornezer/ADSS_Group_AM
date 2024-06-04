package Domain;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;

public class Order
{
    private static int id = 1;
    private ArrayList<Item> items;
    private Site source;
    private Site destination;
    private LocalDate date;
    private LocalTime time;

    public Order(LocalTime time, LocalDate date, Site destination, Site source)
    {
        setId(id);
        setDate(date);
        setTime(time);
        setSource(source);
        setDestination(destination);
        items = new ArrayList<>();
        id++;
    }

    public void setId(int id) {
        Order.id = id;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDestination(Site destination) {
        this.destination = destination;
    }

    public void setSource(Site source) {
        this.source = source;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Site getSource() {
        return source;
    }

    public Site getDestination() {
        return destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
    public boolean addItem(int id, String name, int amount)
    {
        if (amount < 0 && existItemID(id) )
        {
            return false;
        }
        Item item = new Item(id,name,amount);
        return items.add(item);
    }

    public boolean removeItem(int id)
    {
        if(existItemID(id))
        {
            Item item = items.get(id);
            return items.remove(item);
        }
        return false;
    }
    public boolean changeAmount(int id, int amount)
    {
        if (existItemID(id))
        {
            Item item = items.get(id);
            return item.setAmount(amount);
        }
        return false;
    }
    public boolean existItemID(int id)
    {
        for (Item item : items)
        {
            if(item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }
}





