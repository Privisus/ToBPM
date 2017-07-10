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

public class ParseClearListener implements ActionListener {

  private JButton clearButton;
  private JTextArea parseOutput;

  public ParseClearListener(JButton clearButton, JTextArea parseOutput) {
    this.clearButton = clearButton;
    this.parseOutput = parseOutput;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == clearButton) {
      parseOutput.setText("");
    }
  }
}
