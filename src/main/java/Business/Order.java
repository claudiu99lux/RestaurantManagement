package Business;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Defines order objects, implements serializable to enable seralization of the data in the Restaurant
 */
public class Order implements Serializable {
    /**
     * @params id ID of the order
     * @params date The date when the order was created
     * @params table The number of the table
     * @params finished Keeps track of finished orders. Finished orders cannot be edited.
     */
    private int id;
    private LocalDate date;
    private int table;
    private int finished=0;

    public Order(int id, int table){
        this.id=id;
        this.table=table;
        date = LocalDate.now();
        finished=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                table == order.table &&
                date.equals(order.date);
    }

    @Override
    public int hashCode() {
        int hash=7;
        hash = 31*hash + id;
        hash = 31*hash + date.hashCode();
        hash = 31*hash + table;
        return hash;
        // Looked up on the internet and 31 is a good practice to use because multiplication is easily done by shifting bits to the left by 5 then substracting the number
    }

    public String[] prepareTableRecord(){
        String[] record = new String[5];
        record[0] = ""+id;
        record[1] = ""+table;
        record[2] = date.toString();
        record[3] = (finished==1)? "Yes":"No";
        record[4] = (finished==1)? ""+computePrice():"";
        return record;
    }

    public int getId() {
        return id;
    }

    public int getTable(){
        return table;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
        Restaurant.sendToNotifier(this);
    }

    public float computePrice(){
        float price=0;
        for(Business.MenuItem m : Restaurant.getOrdersContent().get(this)){
            price+=m.computePrice();
        }
        return price;
    }
}
