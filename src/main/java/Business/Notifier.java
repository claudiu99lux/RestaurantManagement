package Business;

import java.util.ArrayList;

/**
 * Notifies the chef of new orders / modified orders, used in the Observer pattern
 */
public class Notifier implements Subject {

    ArrayList<Observer> observerList;
    public Notifier(ArrayList<Observer> observerList){
        this.observerList = observerList;
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers(Order order) {
        for(Observer o : observerList){
            o.update(order);
        }
    }
}
