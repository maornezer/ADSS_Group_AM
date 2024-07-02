package Domain;

public interface IRepository
{
    public boolean insert(Object object);

    boolean insert(Item item);

    boolean remove(int id);
    Object get(int id);
    boolean search(int id);

    }
