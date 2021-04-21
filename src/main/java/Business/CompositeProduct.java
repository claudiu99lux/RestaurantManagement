package Business;

import java.util.ArrayList;

/**
 * Composite product, extends MenuItem
 */
public class CompositeProduct extends MenuItem {
    private ArrayList<MenuItem> productList;
    private String productName;
    private int id;

    public CompositeProduct(int id, String name){
        this.id = id;
        productName=name;
        productList=new ArrayList<MenuItem>();
    }

    public void addProduct(MenuItem product){
        productList.add(product);
    }

    public void removeProduct(MenuItem product){
        productList.remove(product);
    }

    public boolean containsProduct(MenuItem item){
        for (MenuItem m : productList) {
            if(m instanceof BaseProduct) {
                if (m.getId() == item.getId())
                    return true;
            }
            else
                return ((CompositeProduct)m).containsProduct(item);
        }
        return false;
    }

    public ArrayList<MenuItem> getProductList(){
        return productList;
    }

    public String getName(){
        return productName;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.productName=name;
    }

    public void setPrice(float price){}

    public String[] prepareTableRecord(){
        String[] record = new String[4];
        record[0] = ""+id;
        record[1] = productName;
        record[2] = "Composite Product";
        record[3] = ""+this.computePrice();
        return record;
    }

    @Override
    public float computePrice() {
        float price=0;
        for (MenuItem m:productList) {
            price+=m.computePrice();
        }
        return price;
    }
}
