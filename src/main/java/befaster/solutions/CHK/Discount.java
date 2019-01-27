package befaster.solutions.CHK;

import java.util.List;

public class Discount {
    private final List<Item> items;
    private final int descountedPrice;

    public Discount(List<Item> items, int descountedPrice) {
        this.items = items;
        this.descountedPrice = descountedPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getDescountedPrice() {
        return descountedPrice;
    }
}
