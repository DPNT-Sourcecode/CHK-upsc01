package befaster.solutions.CHK;

public class Item {

    private final String sku;
    private final int Price;

    public Item(String sku, int price) {
        this.sku = sku;
        Price = price;
    }

    public String getSku() {
        return sku;
    }

    public int getPrice() {
        return Price;
    }
}
