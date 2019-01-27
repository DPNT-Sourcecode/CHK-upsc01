package befaster.solutions.CHK;

import java.util.*;
import java.util.stream.Collectors;

public class ItemFactory {

    private final List<Item> items;
    private final List<Offers> offers;

    public ItemFactory() {
        items = createItems();
        offers = createDiscounts();
    }

    public Item getItem(String a) {
        return items.stream().filter(item -> a.equals(item.getSku())).findFirst().orElse(null);
    }

    private Offers getDiscount(String sku) {
        return offers.stream().filter(offers -> sku.equals(offers.getItems().get(0).getSku())).findFirst().orElse(null);
    }

    public Integer getTotalPrice(List<Item> items) {
        Map<String, Long> itemCounts = items.stream().collect(Collectors.groupingBy(Item::getSku, Collectors.counting()));

        List<Offers> itemsHasOffers = new ArrayList<>();
        final boolean[] discountPresent = {true};

        while(discountPresent[0] && items.size() != 0) {

            for (int i = 0; i < items.size(); i++) {
                String sku = items.get(i).getSku();
                Long count = itemCounts.get(sku);

                Optional<Offers> discountStream = Optional.ofNullable(getDiscount(sku));

                if (discountStream.isPresent() && count > 0 && (count >= discountStream.get().getItems().size() || count % discountStream.get().getItems().size() == 0)) {
                    itemsHasOffers.add(discountStream.get());
                    long newCount = itemCounts.get(sku) - discountStream.get().getItems().size();
                    itemCounts.replace(sku, newCount);

                    for (Item item : discountStream.get().getItems()) {
                        items.remove(item);
                    }


//                    for (int b = 0; b < discountStream.get().getItems().size(); b++) {
//                        Optional<Item> first = items.stream().filter(item -> item.getSku().equals(sku)).findFirst();
//                        first.ifPresent(items::remove);
//                    }
                } else {
                    discountPresent[0] = false;
                }
            }
        }

        int total = 0;
        total += itemsHasOffers.stream().mapToInt(Offers::getDiscountedPrice).sum();
        total += items.stream().mapToInt(Item::getPrice).sum();

        return total;
    }

    private List<Offers> createDiscounts() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);

        Offers offersFor3A = new Offers(Arrays.asList(a, a, a), 130);
//        Offers offersFor5A = new Offers(Arrays.asList(a, a, a, a, a), 200);
        Offers offersB = new Offers(Arrays.asList(b, b), 45);

        return Arrays.asList(offersB, offersFor3A);
    }

    private List<Item> createItems() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);
        Item c = new Item("C", 20);
        Item d = new Item("D", 15);

        return Arrays.asList(a, b, c, d);
    }
}



