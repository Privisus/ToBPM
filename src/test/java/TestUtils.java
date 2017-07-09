import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;

public abstract class TestUtils {

  private TestUtils() {
  }

  /**
   * Create the hard-coded expected timing result.
   *
   * @param timingResults List of timing results. Example: [1.25, 80], [2.0000, 95]
   * @return An ArrayList of arrays of Double.
   */
  public static LinkedList<Double[]> createTimingBPMResult(Double[]... timingResults) {
    LinkedList<Double[]> correctResult = new LinkedList<>();

    for (Double[] result : timingResults) {
      correctResult.add(result);
    }

    return correctResult;
  }

  /**
   * Compares both timing result. Both timing result must equal to each other.
   */
  public static void assertEqualsBPM(LinkedList<Double[]> expected,
      LinkedList<Double[]> result) {
    for (Double[] expectedTiming : expected) {
      int currentIndex = expected.indexOf(expectedTiming);

      assertTrue(Arrays.equals(expectedTiming, result.get(currentIndex)));
    }
  }

  public static void assertArrayNotNull(Double[] parseStringToDoubleResult) {
    for (double d : parseStringToDoubleResult) {
      assertNotNull(d);
    }
  }
}
