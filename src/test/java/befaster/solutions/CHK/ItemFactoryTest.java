package befaster.solutions.CHK;

import org.junit.Test;

import java.util.ArrayList;
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
        List<Item> items = new ArrayList<>();
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));

        Integer result = itemFactory.getTotalPrice(items);

        assertThat(result, is(130));
    }

    @Test
    public void getTotalPriceReturnsTotalPriceTakingIntoAccountDiscountsAndNormalPrice() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("B", 30));
        Integer result = itemFactory.getTotalPrice(items);
        assertThat(result, is(160));
    }


    @Test
    public void getTotalPriceReturnsTotalPriceTakingIntoAccountDiscountsAndNormalPrices() {

        List<Item> items = new ArrayList<>();
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        Integer result = itemFactory.getTotalPrice(items);
        assertThat(result, is(180));
    }

    @Test
    public void getTotalPriceReturnsTotalPriceTakingIntoAccountMultipleDiscounts() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));

        Integer result = itemFactory.getTotalPrice(items);
        assertThat(result, is(260));
    }

    @Test
    public void getTotalPriceReturnsTotalPriceTakingIntoAccountDiscountFor5ItemA() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));
        items.add(new Item("A", 50));

        Integer result = itemFactory.getTotalPrice(items);
        assertThat(result, is(200));
    }

    @Test
    public void getTotalPriceReturnsTotalPriceTakingIntoDiscountsForItemB() {

        List<Item> items = new ArrayList<>();
        items.add(new Item("B", 30));
        items.add(new Item("B", 30));
        Integer result = itemFactory.getTotalPrice(items);
        assertThat(result, is(45));
    }
}