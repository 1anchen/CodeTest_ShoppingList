import Item.*;
import Customer.*;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {

    DiscountItem CD;
    Item Bread;
    Item Gin;
    Customer Ruri;
    LoyalCardCustomer Duncan;


    ShoppingBasket shoppingBasketForDuncan;
    ShoppingBasket shoppingBasketForRuri;

    @Before
    public void before(){
        CD = new DiscountItem(1,"Best Song Ever", 9.99);
        Bread = new Item(2,"Soft White ",1.00);
        Gin = new Item(3,"Just Gin",15.00);
        Ruri = new Customer(100);
        Duncan = new LoyalCardCustomer(100);
        shoppingBasketForDuncan = new ShoppingBasket(Duncan);
        shoppingBasketForRuri = new ShoppingBasket(Ruri);
    }

    //Basic Getter Test
    @Test
    public void canGetEmptyBasket(){
        HashMap<Item,Integer> emptyBasket = new HashMap<>();
        assertEquals(emptyBasket,shoppingBasketForDuncan.getBasket());
    }

    @Test
    public void canGetNumberOfItemsInTheBasket(){
        assertEquals(0,shoppingBasketForDuncan.getNumberOfItemsInTheBasket());
    }

    @Test
    public void canGetTotal(){
        assertEquals(0,shoppingBasketForDuncan.getTotal(),0.00);
    }



    //Can Add Item
    @Test
    public void canAddCDToTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        assertEquals(1,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotAddCDWithZeroQuantityToTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,0);
        assertEquals(0,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
    }

    @Test
    public void canAddCDAndBreadToTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.addItemToBasket(Bread,1);
        assertEquals(2,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
    }


    //Can Add Item + Calculate Total Price
    @Test
    public void canAddCDAndPriceToTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        assertEquals(1,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
        assertEquals(9.99,shoppingBasketForRuri.getTotal(),0.00);
    }

    @Test
    public void canAddCDAndBreadAndThePriceToTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.addItemToBasket(Bread,1);
        assertEquals(2,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
        assertEquals(10.99,shoppingBasketForRuri.getTotal(),0.00);
    }


    //Can Remove Item
    @Test
    public void canRemoveCDFromTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.removeItemFromBasket(CD);
        assertEquals(0,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
    }

    @Test
    public void canNotRemoveBreadAfterAddedCDFromTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.removeItemFromBasket(Bread);
        assertEquals(1,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
    }

    @Test
    public void canRemoveCDAndBreadFromTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.addItemToBasket(Bread,1);
        shoppingBasketForRuri.removeItemFromBasket(CD);
        shoppingBasketForRuri.removeItemFromBasket(Bread);
        assertEquals(0,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
    }


    //Can Remove Item + Reduce Total Price
    @Test
    public void canRemoveCDFromTheBasketAndReducePrice(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.removeItemFromBasket(CD);
        assertEquals(0,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
        assertEquals(0,shoppingBasketForRuri.getTotal(),0.00);
    }

    @Test
    public void canNotRemoveBreadAfterAddedCDFromTheBasketWithPrice(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.removeItemFromBasket(Bread);
        assertEquals(1,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
        assertEquals(9.99,shoppingBasketForRuri.getTotal(),0.00);
    }

    @Test
    public void canRemoveCDAndBreadFromTheBasketWithPrice() {
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.addItemToBasket(Bread,1);
        shoppingBasketForRuri.removeItemFromBasket(CD);
        shoppingBasketForRuri.removeItemFromBasket(Bread);
        assertEquals(0, shoppingBasketForRuri.getNumberOfItemsInTheBasket());
        assertEquals(0, shoppingBasketForRuri.getTotal(),0.00);
    }

    //Can Clear Basket
    @Test
    public void canClearTheBasket(){
        shoppingBasketForRuri.addItemToBasket(CD,1);
        shoppingBasketForRuri.addItemToBasket(Bread,1);
        shoppingBasketForRuri.emptyBasket();
        assertEquals(0,shoppingBasketForRuri.getNumberOfItemsInTheBasket());
    }


    //Can Test Buy One Get One Free
    @Test
    public void canApplyBuyOneGetOneFree(){
        shoppingBasketForRuri.buyOneGetOneFree(CD,2);
        assertEquals(2, shoppingBasketForRuri.getNumberOfItemsInTheBasket());
        assertEquals(9.99, shoppingBasketForRuri.getTotal(),0.00);
    }

    @Test
    public void cannotApplyBuyOneGetOneFreeForNoneDiscountedItem(){
        shoppingBasketForRuri.buyOneGetOneFree(CD,2);
        shoppingBasketForRuri.addItemToBasket(Gin,2);
        assertEquals(4, shoppingBasketForRuri.getNumberOfItemsInTheBasket());
        assertEquals(39.99, shoppingBasketForRuri.getTotal(),0.00);
    }

    //Total Discount Over 20
    @Test
    public void ifTotalIsOverTwentyTrue(){
        shoppingBasketForRuri.buyOneGetOneFree(CD,2);
        shoppingBasketForRuri.addItemToBasket(Gin,1);
        assertEquals(true, shoppingBasketForRuri.isTotalOverTwenty());

    }

    @Test
    public void ifTotalIsOverTwentyFalse(){
        shoppingBasketForRuri.addItemToBasket(Gin,1);
        assertEquals(false, shoppingBasketForRuri.isTotalOverTwenty());

    }

    @Test
    public void canApplyTenPercentOff(){
        shoppingBasketForRuri.buyOneGetOneFree(CD,2);
        shoppingBasketForRuri.addItemToBasket(Gin,1);
        shoppingBasketForRuri.applyTenPercentOff();
        assertEquals(22.49, shoppingBasketForRuri.getTotal(),0.00);
    }


     //Test with Loyal Card Discount
    @Test
    public void loyalCardCustomerCanApplyLoyalCardDiscountWithJustGin(){
        shoppingBasketForDuncan.addItemToBasket(Gin,1);
        shoppingBasketForDuncan.applyLoyalDiscount(Duncan);
        assertEquals(14.70, shoppingBasketForDuncan.getTotal(),0.00);
    }

    @Test
    public void normalCustomerConNotApplyLoyalCardDiscountWithJustGin(){
        shoppingBasketForRuri.addItemToBasket(Gin,1);
        assertEquals(15.00, shoppingBasketForRuri.getTotal(),0.00);
    }

    @Test
    public void loyalCardCustomerCanApplyLoyalCardDiscountWithBuyOneGetOneFreeCD(){
        shoppingBasketForDuncan.buyOneGetOneFree(CD,2);
        shoppingBasketForDuncan.applyLoyalDiscount(Duncan);
        assertEquals(9.79, shoppingBasketForDuncan.getTotal(),0.00);
    }

    @Test
    public void normalCustomerConNotApplyLoyalCardDiscountWithBuyOneGetOneFreeCD(){
        shoppingBasketForRuri.buyOneGetOneFree(CD,2);
        assertEquals(9.99, shoppingBasketForRuri.getTotal(),0.00);
    }

    @Test
    public void loyalCardCustomerCanApplyLoyalCardDiscountWithBuyOneGetOneFreeCDAndOverTwentyPoundDiscount(){
        shoppingBasketForDuncan.buyOneGetOneFree(CD,2);
        shoppingBasketForDuncan.addItemToBasket(Gin,1);
        shoppingBasketForDuncan.applyTenPercentOff();
        shoppingBasketForDuncan.applyLoyalDiscount(Duncan);
        assertEquals(22.04, shoppingBasketForDuncan.getTotal(),0.00);
    }

    @Test
    public void normalCustomerConNotApplyLoyalCardDiscountButCanApplyWithBuyOneGetOneFreeCDAndOverTwentyPoundDiscount(){
        shoppingBasketForRuri.buyOneGetOneFree(CD,2);
        shoppingBasketForRuri.addItemToBasket(Gin,1);
        shoppingBasketForRuri.applyTenPercentOff();
        assertEquals(22.49, shoppingBasketForRuri.getTotal(),0.00);
    }

    //Can Charge Customers
    @Test
    public void canChargeLoyalCardCustomerCanApplyLoyalCardDiscountWithJustGin() {
        shoppingBasketForDuncan.addItemToBasket(Gin,1);
        shoppingBasketForDuncan.applyLoyalDiscount(Duncan);
        shoppingBasketForDuncan.chargeCustomer();
        assertEquals(14.70, shoppingBasketForDuncan.getTotal(), 0.00);
        assertEquals(85.30, Duncan.getWallet(), 0.00);
    }

    @Test
    public void canChargeNormalCustomerConNotApplyLoyalCardDiscountWithJustGin(){
        shoppingBasketForRuri.addItemToBasket(Gin,1);
        shoppingBasketForRuri.chargeCustomer();
        assertEquals(15.00, shoppingBasketForRuri.getTotal(),0.00);
        assertEquals(85.00, Ruri.getWallet(), 0.00);
    }


    @Test
    public void canChargeLoyalCardCustomerCanApplyLoyalCardDiscountWithBuyOneGetOneFreeCD(){
        shoppingBasketForDuncan.buyOneGetOneFree(CD,2);
        shoppingBasketForDuncan.applyLoyalDiscount(Duncan);
        shoppingBasketForDuncan.chargeCustomer();
        assertEquals(9.79, shoppingBasketForDuncan.getTotal(),0.00);
        assertEquals(90.21, Duncan.getWallet(), 0.00);
    }

    @Test
    public void canChargeNormalCustomerConNotApplyLoyalCardDiscountWithBuyOneGetOneFreeCD(){
        shoppingBasketForRuri.buyOneGetOneFree(CD,2);
        shoppingBasketForRuri.chargeCustomer();
        assertEquals(9.99, shoppingBasketForRuri.getTotal(),0.00);
        assertEquals(90.01, Ruri.getWallet(), 0.00);
    }

    @Test
    public void canChargeLoyalCardCustomerCanApplyLoyalCardDiscountWithBuyOneGetOneFreeCDAndOverTwentyPoundDiscount(){
        shoppingBasketForDuncan.buyOneGetOneFree(CD,2);
        shoppingBasketForDuncan.addItemToBasket(Gin,1);
        shoppingBasketForDuncan.applyTenPercentOff();
        shoppingBasketForDuncan.applyLoyalDiscount(Duncan);
        shoppingBasketForDuncan.chargeCustomer();
        assertEquals(22.04, shoppingBasketForDuncan.getTotal(),0.00);
        assertEquals(77.96, Duncan.getWallet(), 0.00);
    }

    @Test
    public void canChargeNormalCustomerConNotApplyLoyalCardDiscountButCanAppyWithBuyOneGetOneFreeCDAndOverTwentyPoundDiscount(){
        shoppingBasketForRuri.buyOneGetOneFree(CD,2);
        shoppingBasketForRuri.addItemToBasket(Gin,1);
        shoppingBasketForRuri.applyTenPercentOff();
        shoppingBasketForRuri.chargeCustomer();
        assertEquals(22.49, shoppingBasketForRuri.getTotal(),0.00);
        assertEquals(77.51, Ruri.getWallet(), 0.00);
    }




}
