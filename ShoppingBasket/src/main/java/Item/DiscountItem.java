package Item;

import Interface.IDiscount;

public class DiscountItem extends Item implements IDiscount {


    public DiscountItem(int id, String name, double price) {
        super(id, name, price);
    }

    //set second item price and return the item
    @Override
    public Item GetSecondFree(DiscountItem item) {
        item.setPrice(0);
        return item;
    }
}
