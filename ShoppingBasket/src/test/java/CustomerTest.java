import Customer.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    Customer Ruri;

    @Before
    public void before(){
        Ruri = new Customer(100);
    }

    @Test
    public void canGetPrice(){
        assertEquals(100.00, Ruri.getWallet(),0.00);
    }

    @Test
    public void canResetWallet(){
        Ruri.setWallet(80.00);
        assertEquals(80.00, Ruri.getWallet(),0.00);
    }
}
