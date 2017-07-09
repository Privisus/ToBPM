import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TimingParseTest extends ToBPMTest {

  @ParameterizedTest
  @ValueSource(strings = {"2.055\n5.025", "2.001\n5.001", "0.0001\n5.0005"})
  @DisplayName("Test inputting second with a point must not null")
  void testPointNotNull(String timing) {
    setupAndAssertStringToDoubleNotNull(timing);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1,0\n2,0", "0,1\n1,0", "0,005\n0,025"})
  @DisplayName("Test inputting second with a comma must not null")
  void testCommaNotNull(String timing) {
    setupAndAssertStringToDoubleNotNull(timing);

    assertEquals(2, parseStringToDoubleResult.length);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1.0\nabcdef\n2.0\nghijklmnop\n3.0", "a\nb\nc\nd"})
  @DisplayName("Test inputting any character instead of number must not null")
  void testNotNumberNotNull(String timing) {
    setupParseDirtyStringToDouble(timing);

    TestUtils.assertArrayNotNull(parseStringToDoubleResult);
  }

  @Test
  @DisplayName("Test inputting second with a comma must parse correctly")
  void testGoodCommaParse() {
    setupParseToBPM("0,5\n1,5\n2,0\n3,5");

    expectedBPMResult = TestUtils
        .createTimingBPMResult(
            new Double[]{0.5, 60.00},
            new Double[]{1.5, 120.00},
            new Double[]{2.0, 40.00}
        );

    TestUtils.assertEqualsBPM(expectedBPMResult, parseBPMResult);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1.0\n2\n3.0", "1\n2.0000\n3", "1\n2\n3"})
  @DisplayName("Test inputting an integer (no delimiter)")
  void testNoDelimiterNumber(String timing) {
    setupParseToBPM(timing);

    expectedBPMResult = TestUtils
        .createTimingBPMResult(
            new Double[]{1.0, 60.00},
            new Double[]{2.0, 60.00}
        );

    TestUtils.assertEqualsBPM(expectedBPMResult, parseBPMResult);
  }

  @Test
  @DisplayName("Test inputting second with a point as delimiter")
  void testPointNotZero() {
    setupParseCleanStringToDouble("1.1\n2.1\n3.1");

    for (double d : parseStringToDoubleResult) {
      assertNotEquals(0.00, d);
    }
  }

  @Test
  @DisplayName("Test inputting second with comma as delimiter")
  void testCommaNotZero() {
    setupParseDirtyStringToDouble("1,1\n2,1\n3,1");

    for (double d : parseStringToDoubleResult) {
      assertNotEquals(0.00, d);
    }
  }

  @Test
  @DisplayName("Test inputting double point must produce consistent result (20,0,15 treat as 200,15)")
  void testDoublePoint() {
    setupParseDirtyStringToDouble("20.0.15\n20.1.15\n20.2.15\n20.3.15");
    expectedTimingResult = new Double[]{200.15, 201.15, 202.15, 203.15};
    assertTrue(Arrays.equals(expectedTimingResult, parseStringToDoubleResult));
  }

  @Test
  @DisplayName("Test inputting double comma must produce consistent result (20,0,15 treat as 200,15)")
  void testDoubleComma() {
    setupParseDirtyStringToDouble("20,0,15\n20,1,15\n20,2,15\n20,3,15");
    expectedTimingResult = new Double[]{200.15, 201.15, 202.15, 203.15};
    assertTrue(Arrays.equals(expectedTimingResult, parseStringToDoubleResult));
  }

  @ParameterizedTest
  @ValueSource(strings = {"1. 50\n 2 .0 5", "1 , 5 0\n 2,..,0 5", "1.,, 5   0\n 2,  0     5"})
  @DisplayName("Test must not include all extra whitespaces and comma/point when printing the output")
  void testNoExtraSymbols(String timing) {
    setupParseDirtyStringToDouble(timing);
    expectedTimingResult = new Double[]{1.5, 2.05};
    assertTrue(Arrays.equals(expectedTimingResult, parseStringToDoubleResult));
  }

  @ParameterizedTest
  @ValueSource(strings = {"1.0\n2.0\n\n3.0", "1.0\n\n2.0\n\n\n3.0", "1.0\n\n\n\n\n2.0\n3.0"})
  @DisplayName("Test must not produce 0 BPM when added extra new empty line")
  void testNoZeroBPMWhenNewEmptyLineExists(String timing) {
    setupParseDirtyStringToDouble(timing);
    expectedTimingResult = new Double[]{1.0, 2.0, 3.0};
    assertTrue(Arrays.equals(expectedTimingResult, parseStringToDoubleResult));
  }
}
