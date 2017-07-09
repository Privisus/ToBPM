/*
 *     ToBPM - Parsing time in seconds to BPM
 *     Copyright (C) 2017 Steven Hans
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JTextArea;

public class ParseBPM {

  private JTextArea outputArea;
  private String input;

  public ParseBPM(String input, JTextArea outputArea) {
    this.input = input;
    this.outputArea = outputArea;
  }

  public ArrayList<Double[]> getParseResult() {
    String[] unparsedTimingList = cleanParsedInput();

    //timingList is a list of time to be parsed. Example: [1.0, 2.0, 3.0]
    Double[] timingList = parseCleanTimingListToDouble(unparsedTimingList);

    //timingResult is the result of the parsing. The format is {[1.0, 60], [2.0, 60]}.
    //Notice that there is no [3.0, ...] because it has no reference to the future time (it is the last time)
    ArrayList<Double[]> timingResult = new ArrayList<>();

    //timingList.length - 1 is used to prevent the last calculation from being calculated (because there is no t2 for the last timing)
    for (int i = 0; i < timingList.length - 1; i++) {
      double t1 = timingList[i]; //Get the first time
      double t2 = timingList[i + 1]; //Get the second time

      //Set BPM to 0 by default
      double BPM = 0;

      //If somehow the user inputted the same number twice, it will skip the calculation to prevent an error
      if (t2 - t1 != 0) {
        BPM = calculateBPM(t2, t1);
      } else {
        //cannot divide by 0: undefined
      }

      //Add the result to the list
      timingResult.add(new Double[]{t1, BPM});
    }

    return timingResult;
  }

  private String[] cleanParsedInput() {
    String[] unparsedDirtyTimingList = splitInput(this.input);
    String[] unparsedTimingList = removeEmptyLine(unparsedDirtyTimingList);

    removeAllWhiteSpaces(unparsedTimingList);
    replaceCommaToPoint(unparsedTimingList);
    removeAndRemainOnePoint(unparsedTimingList);

    return unparsedTimingList;
  }

  public void removeAndRemainOnePoint(String[] unparsedTimingList) {
    for (int currentIndex = 0; currentIndex < unparsedTimingList.length; currentIndex++) {
      unparsedTimingList[currentIndex] = unparsedTimingList[currentIndex].replaceAll("\\.(?=.*\\.)", "");
    }
  }

  public void removeAllWhiteSpaces(String[] unparsedTimingList) {
    for (int currentIndex = 0; currentIndex < unparsedTimingList.length; currentIndex++) {
      unparsedTimingList[currentIndex] = unparsedTimingList[currentIndex].replaceAll(" ", "");
    }
  }

  public String[] removeEmptyLine(String[] unparsedDirtyTimingList) {
    LinkedList<String> timings = new LinkedList<>();

    for (String timing : unparsedDirtyTimingList) {
      if (!timing.equalsIgnoreCase("")) {
        timings.add(timing);
      }
    }

    return timings.toArray(new String[timings.size()]);
  }

  public String[] splitInput(String theString) {
    return theString.split("\n");
  }

  public Double[] parseDirtyTimingListToDouble(String[] unparsedDirtyTimingList) {
    String[] unparsedTimingList = cleanParsedInput();
    return parseCleanTimingListToDouble(unparsedTimingList);
  }

  public Double[] parseCleanTimingListToDouble(String[] unparsedTimingList) {
    Double[] timingList = new Double[unparsedTimingList.length];

    for (int timingIndex = 0; timingIndex < timingList.length; timingIndex++) {
      try {
        timingList[timingIndex] = Double.parseDouble(unparsedTimingList[timingIndex]);
      } catch (NumberFormatException e) {
        System.out.println(
            "Error when parsing this time to double: " + unparsedTimingList[timingIndex]
                + ". Assigning it to 0.");
        timingList[timingIndex] = 0.0;
      }
    }
    return timingList;
  }

  private void replaceCommaToPoint(String[] timingList) {
    for (int i = 0; i < timingList.length; i++) {
      timingList[i] = timingList[i].replaceAll(",", ".");
    }
  }

  public void setInput(String input) {
    this.input = input;
  }

  public void parseToOutput() {
    ArrayList<Double[]> timingResult = getParseResult();

    //Clean the area first before outputting the result. This overwrites the previous parse output.
    //TODO make overwrite as an option and make a "clear output" & "copy" button
    outputArea.setText("");

    for (Double[] timing : timingResult) {
      outputArea.setText(outputArea.getText() +
          String.format("When t = %.5fs, it is %.5f BPM", timing[0], timing[1])
          + "\n"
      );
    }
  }

  public double calculateBPM(double t2, double t1) {
    return 60 / (t2 - t1);
  }

}