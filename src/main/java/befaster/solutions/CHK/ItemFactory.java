package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.List;

public class ItemFactory {

    private final List<Item> items;

    public ItemFactory() {
        items = createItems();


    }



    public Item getItem(String a) {
        return items.stream().filter(item -> a.equals(item.getSku())).findFirst().orElse(null);
    }

    public Integer getTotalPrice(List<Item> items) {
        return items.stream().mapToInt(Item::getPrice).sum();
    }


    private Integer getDiscountedValueOfA() {
        return null;
    }

    private List<Item> createItems() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);
        Item c = new Item("C", 20);
        Item d = new Item("D", 15);

        return Arrays.asList(a, b, c, d);
    }


}


