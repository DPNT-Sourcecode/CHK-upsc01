package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.List;

public class ItemFactory {

    public static List<Item> items() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);
        Item c = new Item("C", 20);
        Item d = new Item("D", 15);

        return Arrays.asList(a, b, c, d);
    }

    public static Item getItem(String a) {
        List<Item> items = items();

        return items.stream().filter(item -> a.equals(item.getSku())).findFirst().orElse(null);
    }

    public static Integer getTotalPrice(List<Item> items) {
        return items.stream().mapToInt(Item::getPrice).sum();
    }
}

