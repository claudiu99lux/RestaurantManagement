package Business;

/**
 * Subject interface in Observer pattern
 */
public interface Subject {
    public void registerObserver(Observer o);
    public void unregisterObserver(Observer o);
    public void notifyObservers(Order o);
}
