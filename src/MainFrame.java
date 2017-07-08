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

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

  public static Dimension screenSize;
  public static JPanel panel;
  private static Toolkit toolKit = Toolkit.getDefaultToolkit();


  public MainFrame(JPanel panel) {
    this.panel = panel;
  }

  public static void retrieveScreenSize() {
    screenSize = toolKit.getScreenSize();
  }

  public void run() {
    retrieveScreenSize();

    setSize(400, 500);
    centerWindow();

    setTitle("ToBPM");

    add(panel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setResizable(false);

    setVisible(true);
  }

  private void centerWindow() {
    int centerX = (screenSize.width / 2) - (this.getWidth() / 2);
    int centerY = (screenSize.height / 2) - (this.getHeight() / 2);

    this.setLocation(centerX, centerY);
  }
}
