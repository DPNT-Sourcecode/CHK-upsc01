package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.List;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        String[] individualSkus = skus.split(",");
        Integer total = 0;
        for (String sku:individualSkus) {
            total += ItemFactory.getItem(sku).getPrice();
        }

        return total;
    }
}

