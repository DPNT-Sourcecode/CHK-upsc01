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
        Integer result = checkoutSolution.checkout("AB");

        assertThat(result, is(80));
    }

    @Test
    public void checkout_shouldReturnTotalPriceOfItemsABCD() {
        Integer result = checkoutSolution.checkout("ABCD");

        assertThat(result, is(115));
    }

    @Test
    public void checkout_shouldReturnMinusOneForInvalidInput() {
        Integer result = checkoutSolution.checkout("dsa");

        assertThat(result, is(-1));
    }

    @Test
    public void checkout_shouldReturnTotalPriceOf3ItemAS() {
        Integer result = checkoutSolution.checkout("AAA");

        assertThat(result, is(130));
    }

    @Test
    public void checkout_shouldReturnTotalPriceOfMultipleDiscountedItems() {
        Integer result = checkoutSolution.checkout("AAAAAA");

        assertThat(result, is(260));
    }

    @Test
    public void checkout_shouldReturnTotalPriceOfDiscountsAndNormalPrice() {
        Integer result = checkoutSolution.checkout("AAAA");

        assertThat(result, is(180));
    }

    @Test
    public void checkout_shouldReturnCorrectPriceOfBItems() {
        Integer result = checkoutSolution.checkout("BBBBB");

        assertThat(result, is(120));
    }

    @Test
    public void checkout_fixFailingScenario() {
        Integer result = checkoutSolution.checkout("ABCDCBAABCABBAAA");

        assertThat(result, is(505));
    }

    @Test
    public void checkout_shouldReturnTotalPriceOfDiscountsWith5AItems() {
        Integer result = checkoutSolution.checkout("AAAAA");

        assertThat(result, is(200));
    }

    @Test
    public void checkout_shouldReturnZeroForEmptyString() {
        Integer result = checkoutSolution.checkout("");

        assertThat(result, is(0));
    }
}