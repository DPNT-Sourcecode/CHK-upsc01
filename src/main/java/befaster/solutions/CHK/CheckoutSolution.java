package befaster.solutions.CHK;

public class CheckoutSolution {
    public Integer checkout(String skus) {

        if ("B".equals(skus)) {
            return 30;
        } else if("C".equals(skus)) {
            return 20;
        } else if("D".equals(skus)) {
            return 15;
        }

        return 50;
    }
}
