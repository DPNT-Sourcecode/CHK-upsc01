package befaster.solutions.CHK;

import java.util.List;

public class CheckoutSolution {
    public Integer checkout(String skus) {

        Item item = ItemFactory.getItem(skus);
        return item.getPrice();
    }
}
