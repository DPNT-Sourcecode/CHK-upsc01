package befaster.solutions.CHK;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemFactory {

    private final List<Item> items;
    private final List<Offer> offers;

    public ItemFactory() {
        items = createItems();
        offers = createDiscounts();
    }

    public Item getItem(String a) {
        return items.stream().filter(item -> a.equals(item.getSku())).findFirst().orElse(null);
    }

    private Offer getDiscount(String sku) {
        return offers.stream().filter(offer -> sku.equals(offer.getItems().get(0).getSku())).findFirst().orElse(null);
    }

    private Offer getOffers(List<Item> items) {
        return offers.stream().filter(offer -> items.containsAll(offer.getItems())).findFirst().orElse(null);
    }

    public Integer getTotalPrice(List<Item> items) {
        Map<String, Long> itemCounts = items.stream().collect(Collectors.groupingBy(Item::getSku, Collectors.counting()));

        List<Offer> itemsHasOffers = new ArrayList<>();
        final boolean[] discountPresent = {true};

        while(discountPresent[0] && items.size() != 0) {

            for (int i = 0; i < items.size(); i++) {
                String sku = items.get(i).getSku();
                Long count = itemCounts.get(sku);

                Optional<Offer> discountStream = Optional.ofNullable(getDiscount(sku));
                Optional<Offer> offers = Optional.ofNullable(getOffers(items));

                if (discountStream.isPresent() && count > 0 && (count >= discountStream.get().getItems().size() || count % discountStream.get().getItems().size() == 0)) {
                    itemsHasOffers.add(discountStream.get());
                    long newCount = itemCounts.get(sku) - discountStream.get().getItems().size();
                    itemCounts.replace(sku, newCount);

                    for (Item item : discountStream.get().getItems()) {
                        items.remove(item);
                    }
                } else {
                    discountPresent[0] = false;
                }
            }
        }

        int total = 0;
        total += itemsHasOffers.stream().mapToInt(Offer::getDiscountedPrice).sum();
        total += items.stream().mapToInt(Item::getPrice).sum();

        return total;
    }

    private List<Offer> createDiscounts() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);

        Offer offerFor3A = new Offer(Arrays.asList(a, a, a), 130);
        Offer offerFor5A = new Offer(Arrays.asList(a, a, a, a, a), 200);
        Offer offerB = new Offer(Arrays.asList(b, b), 45);

        return Arrays.asList(offerFor5A, offerB, offerFor3A);
    }

    private List<Item> createItems() {
        Item a = new Item("A", 50);
        Item b = new Item("B", 30);
        Item c = new Item("C", 20);
        Item d = new Item("D", 15);

        return Arrays.asList(a, b, c, d);
    }
}


