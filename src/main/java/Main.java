import java.awt.*;

public class Main {
  private static final Dimension WINDOW_SIZE = new Dimension(1024, 512);
  private static final int FRAME_RATE = 60;
  private static final int FRAME_MILLIS = 1000 / FRAME_RATE;

  public static void main(String[] args) {
    GPanel gPanel = new GPanel();
    gPanel.setBackground(Color.BLACK);
    gPanel.addGObject(new MouseFollower(new Circle(10, new Point(), Color.white), 10, 10));
    GFrame gFrame = new GFrame(gPanel);
    gFrame.setTitle("Mouse Following Demo");
    gFrame.setSize(WINDOW_SIZE);
    gFrame.setVisible(true);
    mainLoop(gPanel);
  }

  private static void mainLoop(GPanel gPanel) {
    while (true) {
      gPanel.update();
      System.out.println("A frame has passed.");
      try {
        Thread.sleep(FRAME_MILLIS);
      } catch (InterruptedException e) {
        System.out.println("Frame delay issue detected");
      }
    }
  }
}
