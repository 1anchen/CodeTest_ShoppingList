import Customer.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LoyalCardCustmerTest{

    LoyalCardCustomer Duncan;

    @Before
    public void before(){
        Duncan = new LoyalCardCustomer(100);
    }

    @Test
    public void canGetPrice(){
        assertEquals(100.00, Duncan.getWallet(),0.00);
    }

    @Test
    public void canGetRatedPercentage(){

        assertEquals(0.98,  Duncan.twoPercentOff(0.02),0.00);
    }


}