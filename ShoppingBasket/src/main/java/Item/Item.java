package Item;

import Interface.IDiscount;

import java.text.DecimalFormat;

public class Item {

    protected int id;
    protected String name;
    protected Double price;

    public Item(int id, String name, Double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
