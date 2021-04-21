package Business;

import java.io.Serializable;

/**
 * Abstract class extended by BaseProduct and CompositeProduct, defines basic methods that need to be implemented
 * Used to implement Composite Pattern
 */
public abstract class MenuItem implements Serializable {

    public MenuItem(){

    }

    public abstract float computePrice();

    public abstract String getName();
    public abstract void setName(String name);
    public abstract void setPrice(float price);
    public abstract int getId();
    public abstract String[] prepareTableRecord();

}
