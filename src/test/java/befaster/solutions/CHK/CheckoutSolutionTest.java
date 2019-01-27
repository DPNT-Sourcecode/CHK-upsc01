package befaster.solutions.CHK;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution = new CheckoutSolution();

    @Test
    public void checkout_shouldReturnPriceOfItemA() {

        Integer result = checkoutSolution.checkout("A");

        assertThat(result, is(50));
    }

    @Test
    public void checkout_shouldReturnPriceOfItemB() {
        Integer result = checkoutSolution.checkout("B");

        assertThat(result, is(30));
    }
}