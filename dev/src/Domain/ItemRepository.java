package Domain;
import DAL.ItemDAO;
import DAL.ItemDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepository{
    private ArrayList<Item> items;
    private ItemDAO itemDAO;
    private Map<Integer, List<Integer>> orderItemsMap;//(orderID,list of items id)

    public ItemRepository()
    {
        items = new ArrayList<>();
        itemDAO = new ItemDAO();
        orderItemsMap = new HashMap<>();

    }


    public boolean insert(Item item) {
        itemDAO.insert(item);
        items.add(item);
        addToOrderItemsMap(item.getIdO(), item.getId());
        return true;
    }

    public boolean remove(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                items.remove(item);
                removeFromOrderItemsMap(item.getIdO(), item.getId());//maybe not
                break;
            }
        }
        itemDAO.remove(id);
        return true;
    }
    public boolean search(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return true;
            }
        }
        ItemDTO temp = (ItemDTO) itemDAO.get(id);
        return temp != null;
    }


    public Item get(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        Item item = new Item((ItemDTO) itemDAO.get(id));
        items.add(item);
        return item;
    }


    // Get list of item IDs by order ID
    public List<Integer> getItemsByOrderId(int orderId) {
        if (orderItemsMap.containsKey(orderId)) {
            return orderItemsMap.get(orderId);
        }
        else {
            List<Integer> itemIds = itemDAO.getItemIdsByOrderId(orderId);
            orderItemsMap.put(orderId, itemIds);
            return itemIds;
        }
    }

    // Add item to order-items map
    private void addOrderItem(int orderId, int itemId) {
        if (!orderItemsMap.containsKey(orderId)) {
            orderItemsMap.put(orderId, new ArrayList<>());
        }
        orderItemsMap.get(orderId).add(itemId);
    }
    // Remove item from order-items map
        private void removeFromOrderItemsMap(int idOrder, int idItem) {
        List<Integer> items = orderItemsMap.get(idOrder);
        if (items != null) {
            items.remove(Integer.valueOf(idItem));
            if (items.isEmpty()) {
                orderItemsMap.remove(idOrder);
            }
        }
    }

    // Add item to order-items map
    private void addToOrderItemsMap(int idOrder, int idItem) {
        orderItemsMap.computeIfAbsent(idOrder, k -> new ArrayList<>()).add(idItem);
    }




}


