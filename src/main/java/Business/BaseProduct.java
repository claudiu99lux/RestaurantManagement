package Business;

/**
 * This class defines the base products
 */
public class BaseProduct extends MenuItem{
    /**
     * @param id ID of product (unique)
     * @param price Price of product
     * @param productName Name of product
     */
    private int id;
    private float price;
    private String productName;

    public BaseProduct(int id, String name, float price){
        this.id = id;
        productName=name;
        this.price=price;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.productName=name;
    }

    public String getName(){
        return productName;
    }

    public void setPrice(float price){
        this.price=price;
    }

    public float computePrice() {
        return price;
    }

    /**
     * Prepares table record
     * @return String array with table data
     */
    public String[] prepareTableRecord(){
        String[] record = new String[4];
        record[0] = ""+id;
        record[1] = productName;
        record[2] = "Base Product";
        record[3] = ""+price;
        return record;
    }
}
