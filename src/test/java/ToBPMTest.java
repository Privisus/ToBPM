import java.util.ArrayList;
import javax.swing.JTextArea;
import org.junit.jupiter.api.BeforeEach;

public abstract class ToBPMTest {

  public JTextArea outputArea;
  public ParseBPM parseBPM = new ParseBPM("", outputArea);
  public ArrayList<Double[]> parseBPMResult = new ArrayList<>();
  public ArrayList<Double[]> expectedBPMResult = new ArrayList<>();
  public Double[] parseStringToDoubleResult;
  public Double[] expectedTimingResult;

  @BeforeEach
  void clearParseResult() {
    parseBPMResult.clear();
    expectedBPMResult.clear();
    parseStringToDoubleResult = null;
    expectedTimingResult = null;
  }

  //Clean string contains "1221.2"
  public void setupParseCleanStringToDouble(String timing) {
    parseBPM.setInput(timing);
    parseStringToDoubleResult = parseBPM
        .parseCleanTimingListToDouble(parseBPM.splitInput(timing));
  }

  //Dirty string contains ",,...  1...221.,2"
  public void setupParseDirtyStringToDouble(String timing) {
    parseBPM.setInput(timing);
    parseStringToDoubleResult = parseBPM
        .parseDirtyTimingListToDouble(parseBPM.splitInput(timing));
  }

  public void setupParseToBPM(String input) {
    parseBPM.setInput(input);
    parseBPMResult = parseBPM.getParseResult();
  }

  public void setupAndAssertStringToDoubleNotNull(String timing) {
    setupParseDirtyStringToDouble(timing);
    TestUtils.assertArrayNotNull(parseStringToDoubleResult);
  }
}
