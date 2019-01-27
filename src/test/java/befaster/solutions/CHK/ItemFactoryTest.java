package befaster.solutions.CHK;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemFactoryTest {

    private ItemFactory itemFactory = new ItemFactory();

    @Test
    public void getItemReturnsCorrectItem() {
        Item result = itemFactory.getItem("A");
        assertThat(result.getSku(), is("A"));
        assertThat(result.getPrice(), is(50));
    }

    @Test
    public void getItemReturnsDetailsForItemBWhenAskedForB() {
        Item result = itemFactory.getItem("B");
        assertThat(result.getSku(), is("B"));
        assertThat(result.getPrice(), is(30));
    }

    @Test
    public void getTotalPriceReturnsTotalPrice() {
        List<Item> items = Arrays.asList(new Item("A", 50), new Item("B", 30));
        Integer result = itemFactory.getTotalPrice(items);
        assertThat(result, is(80));
    }

    @Test
    public void getTotalPriceReturnsTotalPriceTakingIntoAccountDiscounts() {
        List<Item> items = Arrays.asList(new Item("A", 50), new Item("A", 50), new Item("B", 50));
        Integer result = itemFactory.getTotalPrice(items);
        assertThat(result, is(130));
    }

}
