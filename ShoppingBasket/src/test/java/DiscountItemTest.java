import Item.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DiscountItemTest {

        DiscountItem CD;

        @Before
        public void before() {
            CD = new DiscountItem(1,"Best Song Ever", 10.00);

        }

        //Test All Getter
        @Test
        public void canGetId(){
            assertEquals(1, CD.getId());
        }

        @Test
        public void canGetName(){
            assertEquals("Best Song Ever", CD.getName());
        }

        @Test
        public void canGetPrice(){
            assertEquals(10.00, CD.getPrice(),0.00);
        }


        //Test Setter
        @Test
        public void canSetPrice(){
            CD.setPrice(0);
            assertEquals(0, CD.getPrice(),0.00);
        }

        @Test
        public void canGetSecondOneFree(){
            CD.getSecondFree(CD);
            assertEquals(0, CD.getPrice(),0.00);
    }
}
