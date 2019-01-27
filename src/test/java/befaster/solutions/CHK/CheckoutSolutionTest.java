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

    @Test
    public void checkout_shouldReturnPriceOfItemC() {
        Integer result = checkoutSolution.checkout("C");

        assertThat(result, is(20));
    }

    @Test
    public void checkout_shouldReturnPriceOfItemD() {
        Integer result = checkoutSolution.checkout("D");

        assertThat(result, is(15));
    }

    @Test
    public void checkout_shouldReturnTotalPriceOfItemAandB() {
        Integer result = checkoutSolution.checkout("A,B");

        assertThat(result, is(80));
    }
}