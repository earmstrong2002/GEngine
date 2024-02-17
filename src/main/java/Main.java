import java.awt.*;

public class Main {
  private static final Dimension WINDOW_SIZE = new Dimension(1024, 512);

  public static void main(String[] args) {
    GPanel gPanel = new GPanel();
    gPanel.setBackground(Color.BLACK);
    GFrame gFrame = new GFrame(gPanel);
    gFrame.setTitle("Mouse Following Demo");
    gFrame.setSize(WINDOW_SIZE);
    gFrame.setVisible(true);
  }
}
