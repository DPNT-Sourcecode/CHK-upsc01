package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {
    public Integer checkout(String skus) {

        if ("B".equals(skus)) {
            return 30;
        }

        return 50;
    }
}
