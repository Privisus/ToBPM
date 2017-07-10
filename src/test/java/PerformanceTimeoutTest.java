import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PerformanceTimeoutTest extends ToBPMTest {

  @Test
  @DisplayName("Test parsing 100 \"trash\" timing must not exceed 100ms")
  void testParseTiming() {
    StringBuilder inputBuilder = new StringBuilder();
    for (int i = 1; i <= 100; i++) {
      inputBuilder.append(String.format(",.,.,..,%.7f", (double) i));
    }
    String result = inputBuilder.toString();

    Assertions.assertTimeout(Duration.ofMillis(100), () -> setupParseToBPM(result));
  }
}
