package befaster.solutions.CHK;

import java.util.List;

public class Offer {
    private final List<Item> items;
    private final int discountedPrice;

    public Offer(List<Item> items, int discountedPrice) {
        this.items = items;
        this.discountedPrice = discountedPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }
}

