import Item.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    Item CD;

    @Before
    public void before() {
        CD = new Item(1,"High Kick", 10.00);

    }

    //Test All Getter
    @Test
    public void canGetId(){
        assertEquals(1, CD.getId());
    }

    @Test
    public void canGetName(){
        assertEquals("High Kick", CD.getName());
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



}
