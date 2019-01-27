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

        while(discountPresent[0] && itemCounts.size() != 0) {

            itemCounts.forEach((sku, count) -> {
                Optional<Discount> discountStream = discounts.stream()
                        .filter(discount -> sku.equals(discount.getItem().getSku()) && count > 0 && (count >= discount.getQuantity() || count % discount.getQuantity() == 0))
                        .findAny();

                if (discountStream.isPresent()) {
                    itemsHasDiscount.add(discountStream.get());
                    long newCount = itemCounts.get(sku) - discountStream.get().getQuantity();
                    itemCounts.replace(sku, newCount);

                    for (int i = 0; i < discountStream.get().getQuantity(); i++) {
                        Optional<Item> first = items.stream().filter(item -> item.getSku().equals(sku)).findFirst();
                        first.ifPresent(items::remove);
                    }
                } else {
                    discountPresent[0] = false;
                }
            });
        }


//        itemsHasDiscount.addAll(discounts.stream().filter(
//                    discount -> {
//                        String sku = discount.getItem().getSku();
//                        discountPresent[0].set(itemCounts.containsKey(sku) && itemCounts.get(sku) % discount.getQuantity() == 0);
//                        if (discountPresent[0].get()) {
//                            long newCount = itemCounts.get(sku) - discount.getQuantity();
//                            itemCounts.replace(sku, newCount);
//                            if (newCount == 0) {
//                                itemCounts.remove(sku);
//                            }
//
//                            for (int i = 0; i < discount.getQuantity(); i++) {
//                                Optional<Item> first = items.stream().filter(item -> item.getSku().equals(sku)).findFirst();
//                                first.ifPresent(items::remove);
//                            }
//                        }
//                        return discountPresent[0].get();
//                    }
//            ).collect(Collectors.toList()));

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


