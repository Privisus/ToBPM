import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PerformanceTimeoutTest extends ToBPMTest {

  @Test
  @DisplayName("Test parsing 1000 \"trash\" timing must not exceed 1250ms")
  void testParseTiming() {
    StringBuilder inputBuilder = new StringBuilder();
    for (int i = 1; i <= 1000; i++) {
      inputBuilder.append(String.format(",.,.,..,%.7f", (double) i));
    }
    String result = inputBuilder.toString();

    Assertions.assertTimeout(Duration.ofMillis(1250), () -> setupParseToBPM(result));
  }
}
