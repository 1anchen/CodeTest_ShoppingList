import java.util.ArrayList;

import Interface.ILoyal;
import Item.*;
import Customer.*;

public class ShoppingBasket{

    protected ArrayList<Item> basket;
    protected Double total;

    public ShoppingBasket(){
        this.basket = new ArrayList<>();
        this.total = 0.00;
    }

    public ArrayList<Item> getBasket() {
        return this.basket;
    }

    public int getNumberOfItemsInTheBasket() {
        return this.basket.size();
    }

    public double getTotal() {
        return this.total;
    }

    public void addItemToBasket(Item item) {
        this.basket.add(item);
        addToTotal(item);

    }

    public void addToTotal(Item item){
        this.total += item.getPrice();
    }

    public void removeFromTotal(Item item){
        this.total -= item.getPrice();
    }

    public void removeItemToBasket(Item item) {
        //Checking if the Item has already been added before remove
        for(int i=0; i<this.basket.size(); i++){
          if(this.basket.get(i) == item){
              this.basket.remove(item);
              removeFromTotal(item);
          }
        }
    }

    public void emptyBasket() {
        this.basket.clear();
        this.total = 0.00;
    }

    public void buyOneGetOneFree(DiscountItem item){
        addItemToBasket(item);
        addItemToBasket(item.GetSecondFree(item));
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
        this.total *=iLoyal.twoPercentOff(0.02);
    }

    public void chargeCustomer(Customer customer){
        Double total = customer.getWallet();
        total -= this.total;
        customer.setWallet(total);
    }



    // for future, discount item type can be implement on the IDiscount Interface,
    // and the Customer type discount can be implement on the ILoyal Interface.
}
