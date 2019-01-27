package befaster.solutions.CHK;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemFactoryTest {

    @Test
    public void getItemReturnsCorrectItem() {
        Item result = ItemFactory.getItem("A");
        assertThat(result.getSku(), is("A"));
        assertThat(result.getPrice(), is(50));
    }

    @Test
    public void getItemReturnsDetailsForItemBWhenAskedForB() {
        Item result = ItemFactory.getItem("B");
        assertThat(result.getSku(), is("B"));
        assertThat(result.getPrice(), is(30));
    }

}