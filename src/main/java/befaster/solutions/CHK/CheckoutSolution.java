package befaster.solutions.CHK;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (skus.isEmpty()) {
            return 0;
        }

        ItemFactory itemFactory = new ItemFactory();
        String[] individualSkus = skus.split(",");

        List<Item> items = new ArrayList<>();
        for (String sku:individualSkus) {
            Optional<Item> item = Optional.ofNullable(itemFactory.getItem(sku.trim()));
            if (item.isPresent()) {
                items.add(item.get());
            } else {
                return -1;
            }
        }

        return itemFactory.getTotalPrice(items);
    }
}

