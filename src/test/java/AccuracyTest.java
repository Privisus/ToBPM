import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccuracyTest extends ToBPMTest {

  @Test
  @DisplayName("Test the accuracy of very precise double (up to 14 decimal places)")
  void testAccuracyDouble() {
    setupParseToBPM("1.00000125\n2.00005255\n3.000007505\n4.0000001751");

    expectedBPMResult = TestUtils
        .createTimingBPMResult(
            new Double[]{1.00000125, 59.99692215789330},
            new Double[]{2.00005255, 60.00270282174859},
            new Double[]{3.000007505, 60.00043979722366}
        );

    TestUtils.assertEqualsBPM(expectedBPMResult, parseBPMResult);
  }

  //I have no idea why anyone would do this. They're probably insane.
  @Test
  @DisplayName("Test involving really big seconds must not produce an error")
  void testBigNumbers() {
    setupParseToBPM("9007199254740989.00\n9007199254740990.00\n9007199254740991.00");

    expectedBPMResult = TestUtils
        .createTimingBPMResult(
            new Double[]{9007199254740989.00, 60.00},
            new Double[]{9007199254740990.00, 60.00}
        );

    TestUtils.assertEqualsBPM(expectedBPMResult, parseBPMResult);
  }

  @Test
  @DisplayName("Test involving really small numbers")
  void testSmallNumbers() {
    setupParseToBPM(
        "0.00000000000000000000000000000000000000001\n0.00000000000000000000000000000000000000002");

    expectedBPMResult = TestUtils
        .createTimingBPMResult(
            new Double[]{0.00000000000000000000000000000000000000001, 6.0E42}
        );

    TestUtils.assertEqualsBPM(expectedBPMResult, parseBPMResult);
  }

  @Test
  @DisplayName("Test must NOT throw an error when t2-t1 is 0")
  void testZeroDivision() {
    setupParseToBPM("1\n1\n2\n2");
    expectedBPMResult = TestUtils
        .createTimingBPMResult(
            new Double[]{1.00, 0.00},
            new Double[]{1.00, 60.00},
            new Double[]{2.00, 0.00}
        );

    TestUtils.assertEqualsBPM(expectedBPMResult, parseBPMResult);
  }

}
