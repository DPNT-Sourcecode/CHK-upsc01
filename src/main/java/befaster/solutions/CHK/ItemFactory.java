package befaster.solutions.CHK;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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

    private Discount getDiscount(String sku) {
        return discounts.stream().filter(discount -> sku.equals(discount.getItem().getSku())).findFirst().orElse(null);
    }

    public Integer getTotalPrice(List<Item> items) {
        Map<String, Long> itemCounts = items.stream().collect(Collectors.groupingBy(Item::getSku, Collectors.counting()));

        List<Discount> itemsHasDiscount = new ArrayList<>();
        final boolean[] discountPresent = {true};

        while(discountPresent[0] && items.size() != 0) {

            for (int i = 0; i < items.size(); i++) {
                String sku = items.get(i).getSku();
                Long count = itemCounts.get(sku);


//            }


//            itemCounts.forEach((sku, count) -> {
                Optional<Discount> discountStream = Optional.ofNullable(getDiscount(sku));

                if (discountStream.isPresent() && count > 0 && (count >= discountStream.get().getQuantity() || count % discountStream.get().getQuantity() == 0)) {
                    itemsHasDiscount.add(discountStream.get());
                    long newCount = itemCounts.get(sku) - discountStream.get().getQuantity();
                    itemCounts.replace(sku, newCount);

                    for (int b = 0; b < discountStream.get().getQuantity(); b++) {
                        Optional<Item> first = items.stream().filter(item -> item.getSku().equals(sku)).findFirst();
                        first.ifPresent(items::remove);
                    }
                } else {
                    discountPresent[0] = false;
                }
            }
        }

        int total = 0;
        total += itemsHasDiscount.stream().mapToInt(Discount::getDescountedPrice).sum();
        total += items.stream().mapToInt(Item::getPrice).sum();

        return total;
    }

    private List<Discount> createDiscounts() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);

        Discount discountA = new Discount(a, 3, 130);
        Discount discountB = new Discount(b, 2, 45);

        return Arrays.asList(discountA, discountB);
    }

    private List<Item> createItems() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);
        Item c = new Item("C", 20);
        Item d = new Item("D", 15);

        return Arrays.asList(a, b, c, d);
    }
}
