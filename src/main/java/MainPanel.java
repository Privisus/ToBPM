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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class MainPanel extends JPanel {

  private JButton parseButton, clearButton;
  private JTextArea parseInput, parseOutput;
  private JScrollPane inputScroll, outputScroll;
  private Border inputBorder, outputBorder;
  private Box mainBox, buttonBox;

  private JCheckBox overwriteCheckBox = new JCheckBox("overwrite", true);
  private JCheckBox autoCopyCheckBox = new JCheckBox("auto-copy", true);

  public MainPanel() {
    createAllComponents();
    applyListenerToComponents();
    applyLayout();
    putAllComponents();

    parseInput.requestFocus();
  }

  private void applyLayout() {
    this.mainBox = Box.createVerticalBox();
    this.buttonBox = Box.createHorizontalBox();
  }

  private void applyListenerToComponents() {
    parseInput.addKeyListener(new ParseInputListener(parseButton));
    parseButton.addActionListener(
        new ParseClickListener(parseButton, parseInput, parseOutput, autoCopyCheckBox,
            overwriteCheckBox));
    clearButton.addActionListener(new ParseClearListener(clearButton, parseOutput));
  }

  private void createAllComponents() {
    //Create a parse button
    parseButton = new JButton("Parse") {
      {
        setSize(70, 25);
        setMaximumSize(getSize());
      }
    };
    parseButton.setAlignmentX(CENTER_ALIGNMENT);

    clearButton = new JButton("Clear") {
      {
        setSize(70, 25);
        setMaximumSize(getSize());
      }
    };

    //Create a text field so user can put their timings
    parseInput = new JTextArea(5, 30);
    parseInput.setLineWrap(true);
    parseInput.setWrapStyleWord(true);
    parseInput.setFocusTraversalKeysEnabled(true);

    //Create input scroll pane
    inputScroll = new JScrollPane(parseInput, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    inputBorder = BorderFactory.createRaisedSoftBevelBorder();
    parseInput.setBorder(inputBorder);

    //Create another text field to output parsing result;
    parseOutput = new JTextArea(20, 30);
    parseOutput.setLineWrap(true);
    parseOutput.setWrapStyleWord(true);
    parseOutput.setEditable(false);

    outputBorder = BorderFactory.createRaisedSoftBevelBorder();
    parseOutput.setBorder(outputBorder);

    //Create output scroll pane
    outputScroll = new JScrollPane(parseOutput, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
  }

  private void putAllComponents() {
    buttonBox.add(parseButton);
    buttonBox.add(Box.createHorizontalStrut(15));
    buttonBox.add(clearButton);
    buttonBox.add(Box.createHorizontalGlue());
    buttonBox.add(autoCopyCheckBox);
    buttonBox.add(overwriteCheckBox);

    mainBox.add(inputScroll);

    mainBox.add(buttonBox);

    mainBox.add(outputScroll);

    add(mainBox);
  }

  @Deprecated
  public JButton getParseButton() {
    return parseButton;
  }

  @Deprecated
  public JTextArea getParseInput() {
    return parseInput;
  }

  @Deprecated
  public JTextArea getParseOutput() {
    return parseOutput;
  }
}
