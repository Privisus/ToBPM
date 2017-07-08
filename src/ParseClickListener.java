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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ParseClickListener implements ActionListener {

  private JButton parseButton;
  private JTextArea parseInput, parseOutput;
  private ParseBPM parseBPM;

  public ParseClickListener(JButton parseButton, JTextArea parseInput, JTextArea parseOutput) {
    this.parseButton = parseButton;
    this.parseInput = parseInput;
    this.parseOutput = parseOutput;
    parseBPM = new ParseBPM("", parseOutput);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == parseButton) {
      parseBPM.setInput(parseInput.getText());
      parseBPM.parseToOutput();
    }
  }
}
