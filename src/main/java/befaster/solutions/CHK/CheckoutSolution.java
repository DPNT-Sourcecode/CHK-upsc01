package befaster.solutions.CHK;

import java.util.Optional;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        String[] individualSkus = skus.split(",");
        Integer total = 0;
        for (String sku:individualSkus) {
            Optional<Item> item = Optional.ofNullable(ItemFactory.getItem(sku));
            if (item.isPresent()) {
                total += item.get().getPrice();
            } else {
                return -1;
            }
        }

        return total;
    }
}

