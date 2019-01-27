package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        String[] individualSkus = skus.split(",");
        Integer total = 0;

        List<Item> items = new ArrayList<>();
        for (String sku:individualSkus) {
            Optional<Item> item = Optional.ofNullable(ItemFactory.getItem(sku));
            if (item.isPresent()) {
                items.add(item.get());
                total += item.get().getPrice();
            } else {
                return -1;
            }
        }

        return ItemFactory.getTotalPrice(items);
    }
}

