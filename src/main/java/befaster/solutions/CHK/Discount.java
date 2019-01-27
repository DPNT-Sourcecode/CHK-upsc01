package befaster.solutions.CHK;

public class Discount {
    private final Item item;
    private final int quantity;
    private final int descountedPrice;

    public Discount(Item item, int quantity, int descountedPrice) {
        this.item = item;
        this.quantity = quantity;
        this.descountedPrice = descountedPrice;
    }

    public Item getItem() {
        return item;
    }

    public int getDescountedPrice() {
        return descountedPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}

