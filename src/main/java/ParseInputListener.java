import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

public class ParseInputListener implements KeyListener {
  private JButton buttonToFocus;

  public ParseInputListener(JButton buttonToFocus) {
    this.buttonToFocus = buttonToFocus;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_TAB) {
      buttonToFocus.requestFocus();
      e.consume();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
