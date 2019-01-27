package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemFactory {

    private final List<Item> items;
    private final List<Discount> discounts;

    public ItemFactory() {
        items = createItems();
        discounts = createDiscounts();
    }



    public Item getItem(String a) {
        return items.stream().filter(item -> a.equals(item.getSku())).findFirst().orElse(null);
    }

    public Integer getTotalPrice(List<Item> items) {

        List<Item> discountSkus = discounts.stream().map(Discount::getItem).collect(Collectors.toList());

        Map<String, Long> itemCounts = items.stream().collect(Collectors.groupingBy(Item::getSku, Collectors.counting()));

        List<Discount> itemsHasDiscount = discounts.stream().filter(
                discount -> {
                    String sku = discount.getItem().getSku();
                    return itemCounts.containsKey(sku) && itemCounts.get(sku) == discount.getQuantity();
                }
        ).collect(Collectors.toList());


        int total = 0;
        if (itemsHasDiscount.isEmpty()) {
            total = items.stream().mapToInt(Item::getPrice).sum();
        }

        total += itemsHasDiscount.stream().mapToInt(Discount::getDescountedPrice).sum();
        return total;
    }


    private List<Discount> createDiscounts() {
        Item a = new Item("A", 50);


        Discount discountA = new Discount(a, 3, 130);


        return Arrays.asList(discountA);
    }

    private List<Item> createItems() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);
        Item c = new Item("C", 20);
        Item d = new Item("D", 15);

        return Arrays.asList(a, b, c, d);
    }


}
