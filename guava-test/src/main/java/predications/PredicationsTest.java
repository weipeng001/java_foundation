package predications;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**

 * @Date: 2018/6/22 11:01
 * @Description:
 */
public class PredicationsTest {

    public void pintAgeNoErrorMessage(int age) {
        Preconditions.checkArgument(age > 0);
        System.out.println("age is: " + age);
    }

    public void pintAgeWithErrorMessage(int age) {
        String message = "Age can't be zero or less than zero.";
        Preconditions.checkArgument(age > 0, message);
        System.out.println("age is: " + age);
    }

    public void pintAgeWithTemplateErrorMessage(int age) {
        String message = "Age should be positive number, you supplied %s.";
        Preconditions.checkArgument(age > 0, message, age);
        System.out.println("age is: " + age);
    }

    @Test
    public void checkArgumentTest() {
        int age = -18;
        assertThatThrownBy(() -> pintAgeNoErrorMessage(age))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(null).hasNoCause();

        String message = "Age can't be zero or less than zero.";
        assertThatThrownBy(() -> pintAgeWithErrorMessage(age))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message).hasNoCause();

        String message1 = "Age should be positive number, you supplied %s.";
        assertThatThrownBy(() -> pintAgeWithTemplateErrorMessage(age))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message1, age).hasNoCause();
    }

    @Test
    public void checkElementIndexTest() {
        int[] numbers = { 1, 2, 3, 4, 5 };
        String message = "Please check the bound of an array and retry";

        assertThatThrownBy(() ->
                Preconditions.checkElementIndex(6, numbers.length, message))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageStartingWith(message).hasNoCause();
    }

    @Test
    public void checkPositionIndexTest() {
        int[] numbers = { 1, 2, 3, 4, 5 };
        String message = "Please check the bound of an array and retry";

        assertThatThrownBy(
                () -> Preconditions.checkPositionIndex(6, numbers.length, message))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageStartingWith(message).hasNoCause();
    }

    @Test
    public void checkNotNullTest () {
        String nullObject = null;
        String message = "Please check the Object supplied, its null!";

        assertThatThrownBy(() -> Preconditions.checkNotNull(nullObject, message))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(message).hasNoCause();
    }

    @Test
    public void checkStateTest() {
        int[] validStates = { -1, 0, 1 };
        int givenState = 10;
        String message = "You have entered an invalid state";

        assertThatThrownBy(
                () -> Preconditions.checkState(
                        Arrays.binarySearch(validStates, givenState) > 0, message))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith(message).hasNoCause();
    }
}
