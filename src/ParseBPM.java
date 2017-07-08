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
import javax.swing.JTextArea;

public class ParseBPM {

  private JTextArea outputArea;
  private String input;

  public ParseBPM(String input, JTextArea outputArea) {
    this.input = input;
    this.outputArea = outputArea;
  }

  public ArrayList<Double[]> getParseResult() {
    String[] unparsedTimingList = input.split("\n");

    replaceCommaToPoint(unparsedTimingList);

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

    ArrayList<Double[]> timingResult = new ArrayList<>();

    //timingList.length - 1 is used to prevent the last calculation from being calculated (because there is no t2 for the last timing)
    for (int i = 0; i < timingList.length - 1; i++) {
      double t1 = timingList[i]; //Get the first time
      double t2 = timingList[i + 1]; //Get the second time

      //Set BPM to 0 by default
      double BPM = 0;

      //If somehow the user inputted the same number twice, it will skip the calculation to prevent an error
      if (t2 - t1 != 0) {
        BPM = 60 / (t2 - t1);
      } else {
        //cannot divide by 0: undefined
      }

      //Add the result to the list
      timingResult.add(new Double[]{t1, BPM});
    }

    return timingResult;
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

    //Clean the area first
    outputArea.setText("");

    for (Double[] timing : timingResult) {
      outputArea.setText(outputArea.getText() +
          String.format("When t = %.5fs, it is %.5f BPM", timing[0], timing[1])
          + "\n"
      );
    }
  }

}
