
import java.util.HashMap;

import Interface.ILoyal;
import Item.*;
import Customer.*;

public class ShoppingBasket{

    protected HashMap<Item,Integer> basket;
    protected double total;
    protected Customer customer;

    public ShoppingBasket(Customer customer){
        this.customer = customer;
        this.basket = new HashMap<>();
        this.total = 0.00;
    }

    public HashMap<Item,Integer> getBasket() {
        return this.basket;
    }

    public int getNumberOfItemsInTheBasket() {
        int totalItems = 0;
        for(int quantity : basket.values()){
            totalItems += quantity;
        }
        return totalItems;
    }

    public double getTotal() {
        return this.roundTotal();
    }

    private double roundTotal(){
        return total= Math.round(total*100.00)/100.00;
    }

    public void addItemToBasket(Item item, int quantity) {
        if(quantity>0) {
            this.basket.put(item, quantity);
            addToTotal(item);
        }else throw new IllegalArgumentException("Item must be more than 1");

    }

    public void addToTotal(Item item){
        if(basket.get(item)>1) {
            this.total += item.getPrice()*basket.get(item);
        }else {
            this.total +=item.getPrice();
        }
    }

    public void removeFromTotal(Item item){
        this.total -= item.getPrice();
    }

    public void removeItemFromBasket(Item item) {
        if(basket.containsKey(item)) {
            this.basket.remove(item);
            removeFromTotal(item);
        }

    }

    public void emptyBasket() {
        this.basket.clear();
        this.total = 0.00;
    }


    public void buyOneGetOneFree(DiscountItem item,int quantity){

        if(quantity%2==0){
            item.setPrice(item.getPrice()/quantity);
        }
        addItemToBasket(item,quantity);
    }

    public boolean isTotalOverTwenty(){
        if(this.total >= 20){
            return true;
        }
        return false;
    }

    public void applyTenPercentOff(){
        if(isTotalOverTwenty())
            this.total *=0.90;
    }

    public void applyLoyalDiscount(ILoyal iLoyal){
        this.total *=iLoyal.twoPercentOff();
    }

    public void chargeCustomer(){
        Double total = customer.getWallet();
        total -= this.total;
        customer.setWallet(total);
    }




    // for future, discount item type can be implement on the IDiscount Interface,
    // and the Customer type discount can be implement on the ILoyal Interface.
}
