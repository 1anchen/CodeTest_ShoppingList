import Item.*;
import Customer.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {

    DiscountItem CD;
    Item Bread;
    Item Gin;
    Customer Ruri;
    LoyalCardCustomer Duncan;


    ShoppingBasket shoppingBasket;

    @Before
    public void before(){
        CD = new DiscountItem(1,"High Kick", 10.00);
        Bread = new Item(2,"Soft ",1.00);
        Gin = new Item(3,"Strong",15.00);
        Ruri = new Customer(100);
        Duncan = new LoyalCardCustomer(100);
        shoppingBasket = new ShoppingBasket();
    }

    //Basic Getter Test
    @Test
    public void canGetNumberOfItemsInTheBasket(){
        assertEquals(0,shoppingBasket.getNumberOfItemsInTheBasket());
    }

    @Test
    public void canGetTotal(){
        assertEquals(0,shoppingBasket.getTotal(),0.00);
    }



    //Can Add Item
    @Test
    public void canAddCDToTheBasket(){
        shoppingBasket.addItemToBasket(CD);
        assertEquals(1,shoppingBasket.getNumberOfItemsInTheBasket());
    }

    @Test
    public void canAddCDAndBreadToTheBasket(){
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.addItemToBasket(Bread);
        assertEquals(2,shoppingBasket.getNumberOfItemsInTheBasket());
    }


    //Can Add Item + Calculate Total Price
    @Test
    public void canAddCDAndPriceToTheBasket(){
        shoppingBasket.addItemToBasket(CD);
        assertEquals(1,shoppingBasket.getNumberOfItemsInTheBasket());
        assertEquals(10.00,shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void canAddCDAndBreadAndThePriceToTheBasket(){
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.addItemToBasket(Bread);
        assertEquals(2,shoppingBasket.getNumberOfItemsInTheBasket());
        assertEquals(11,shoppingBasket.getTotal(),0.00);
    }


    //Can Remove Item
    @Test
    public void canRemoveCDFromTheBasket(){
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.removeItemToBasket(CD);
        assertEquals(0,shoppingBasket.getNumberOfItemsInTheBasket());
    }

    @Test
    public void canNotRemoveBreadAfterAddedCDFromTheBasket(){
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.removeItemToBasket(Bread);
        assertEquals(1,shoppingBasket.getNumberOfItemsInTheBasket());
    }

    @Test
    public void canRemoveCDAndBreadFromTheBasket(){
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.addItemToBasket(Bread);
        shoppingBasket.removeItemToBasket(CD);
        shoppingBasket.removeItemToBasket(Bread);
        assertEquals(0,shoppingBasket.getNumberOfItemsInTheBasket());
    }


    //Can Remove Item + Reduce Total Price
    @Test
    public void canRemoveCDFromTheBasketAndReducePrice(){
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.removeItemToBasket(CD);
        assertEquals(0,shoppingBasket.getNumberOfItemsInTheBasket());
        assertEquals(0,shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void canNotRemoveBreadAfterAddedCDFromTheBasketWithPrice(){
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.removeItemToBasket(Bread);
        assertEquals(1,shoppingBasket.getNumberOfItemsInTheBasket());
        assertEquals(10.00,shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void canRemoveCDAndBreadFromTheBasketWithPrice() {
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.addItemToBasket(Bread);
        shoppingBasket.removeItemToBasket(CD);
        shoppingBasket.removeItemToBasket(Bread);
        assertEquals(0, shoppingBasket.getNumberOfItemsInTheBasket());
        assertEquals(0, shoppingBasket.getTotal(),0.00);
    }

    //Can Clear Basket
    @Test
    public void canClearTheBasket(){
        shoppingBasket.addItemToBasket(CD);
        shoppingBasket.addItemToBasket(Bread);
        shoppingBasket.emptyBasket();
        assertEquals(0,shoppingBasket.getNumberOfItemsInTheBasket());
    }


    //Can Test Buy One Get One Free
    @Test
    public void canApplyBuyOneGetOneFree(){
        shoppingBasket.buyOneGetOneFree(CD);
        assertEquals(2, shoppingBasket.getNumberOfItemsInTheBasket());
        assertEquals(10.00, shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void cannotApplyBuyOneGetOneFreeForNoneDiscountedItem(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.addItemToBasket(Gin);
        assertEquals(4, shoppingBasket.getNumberOfItemsInTheBasket());
        assertEquals(40.00, shoppingBasket.getTotal(),0.00);
    }

    //Total Discount Over 20
    @Test
    public void ifTotalIsOverTwentyTrue(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.addItemToBasket(Gin);
        assertEquals(true, shoppingBasket.isTotalOverTwenty());

    }

    @Test
    public void ifTotalIsOverTwentyFalse(){
        shoppingBasket.addItemToBasket(Gin);
        assertEquals(false, shoppingBasket.isTotalOverTwenty());

    }

    @Test
    public void canApplyTenPercentOff(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.applyTenPercentOff();
        assertEquals(22.50, shoppingBasket.getTotal(),0.00);
    }

    //This test at the moment can't work with price like £19.99,
    //it will return a double figure with 3 or 4 digits,
    //I have try to use the String.format to display but then the calculation didn't work.



     //Test with Loyal Card Discount
    @Test
    public void loyalCardCustomerCanApplyLoyalCardDiscountWithJustGin(){
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.applyLoyalDiscount(Duncan);
        assertEquals(14.70, shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void normalCustomerConNotApplyLoyalCardDiscountWithJustGin(){
        shoppingBasket.addItemToBasket(Gin);
        assertEquals(15.00, shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void loyalCardCustomerCanApplyLoyalCardDiscountWithBuyOneGetOneFreeCD(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.applyLoyalDiscount(Duncan);
        assertEquals(9.80, shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void normalCustomerConNotApplyLoyalCardDiscountWithBuyOneGetOneFreeCD(){
        shoppingBasket.buyOneGetOneFree(CD);
        assertEquals(10.00, shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void loyalCardCustomerCanApplyLoyalCardDiscountWithBuyOneGetOneFreeCDAndOverTwentyPoundDiscount(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.applyTenPercentOff();
        shoppingBasket.applyLoyalDiscount(Duncan);
        assertEquals(22.05, shoppingBasket.getTotal(),0.00);
    }

    @Test
    public void normalCustomerConNotApplyLoyalCardDiscountButCanAppyWithBuyOneGetOneFreeCDAndOverTwentyPoundDiscount(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.applyTenPercentOff();
        assertEquals(22.50, shoppingBasket.getTotal(),0.00);
    }

    //Can Charge Customers
    @Test
    public void canChargeLoyalCardCustomerCanApplyLoyalCardDiscountWithJustGin() {
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.applyLoyalDiscount(Duncan);
        shoppingBasket.chargeCustomer(Duncan);
        assertEquals(14.70, shoppingBasket.getTotal(), 0.00);
        assertEquals(85.30, Duncan.getWallet(), 0.00);
    }

    @Test
    public void canChargeNormalCustomerConNotApplyLoyalCardDiscountWithJustGin(){
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.chargeCustomer(Ruri);
        assertEquals(15.00, shoppingBasket.getTotal(),0.00);
        assertEquals(85.00, Ruri.getWallet(), 0.00);
    }


    @Test
    public void canChargeLoyalCardCustomerCanApplyLoyalCardDiscountWithBuyOneGetOneFreeCD(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.applyLoyalDiscount(Duncan);
        shoppingBasket.chargeCustomer(Duncan);
        assertEquals(9.80, shoppingBasket.getTotal(),0.00);
        assertEquals(90.20, Duncan.getWallet(), 0.00);
    }

    @Test
    public void canChargeNormalCustomerConNotApplyLoyalCardDiscountWithBuyOneGetOneFreeCD(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.chargeCustomer(Ruri);
        assertEquals(10.00, shoppingBasket.getTotal(),0.00);
        assertEquals(90.00, Ruri.getWallet(), 0.00);
    }

    @Test
    public void canChargeLoyalCardCustomerCanApplyLoyalCardDiscountWithBuyOneGetOneFreeCDAndOverTwentyPoundDiscount(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.applyTenPercentOff();
        shoppingBasket.applyLoyalDiscount(Duncan);
        shoppingBasket.chargeCustomer(Duncan);
        assertEquals(22.05, shoppingBasket.getTotal(),0.00);
        assertEquals(77.95, Duncan.getWallet(), 0.00);
    }

    @Test
    public void canChargeNormalCustomerConNotApplyLoyalCardDiscountButCanAppyWithBuyOneGetOneFreeCDAndOverTwentyPoundDiscount(){
        shoppingBasket.buyOneGetOneFree(CD);
        shoppingBasket.addItemToBasket(Gin);
        shoppingBasket.applyTenPercentOff();
        shoppingBasket.chargeCustomer(Ruri);
        assertEquals(22.50, shoppingBasket.getTotal(),0.00);
        assertEquals(77.50, Ruri.getWallet(), 0.00);
    }




}
