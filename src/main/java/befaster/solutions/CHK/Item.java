package befaster.solutions.CHK;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Price == item.Price &&
                Objects.equals(sku, item.sku);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sku, Price);
    }
}

