package Domain;

public interface IRepository
{
    boolean insert(Object object);
    boolean remove(int id);
    Object get(int id);
    boolean search(int id);

    }
