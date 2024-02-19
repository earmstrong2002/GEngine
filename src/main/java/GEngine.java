import java.awt.*;

public class GEngine {
  public static final int PIXELS_PER_METER = 10;
  private static final Dimension WINDOW_SIZE = new Dimension(1024, 512);
  private static final int FRAME_RATE = 60;
  private static final int FRAME_MILLIS = 1000 / FRAME_RATE;

  public static void main(String[] args) {
    GPanel gPanel = new GPanel();
    gPanel.setBackground(Color.BLACK);
    MouseFollower mouseFollower =
        new MouseFollower(new Circle(1, new GPoint(), Color.white), 0.1, 3);
    gPanel.addGObject(mouseFollower);
    gPanel.addMouseListener(mouseFollower);
    GFrame gFrame = new GFrame(gPanel);
    gFrame.setTitle("Mouse Following Demo");
    gFrame.setSize(WINDOW_SIZE);
    gFrame.setVisible(true);
    mainLoop(gPanel);
  }

  private static void mainLoop(GPanel gPanel) {
    while (true) {
      long start = System.nanoTime();
      gPanel.update();
      long end = System.nanoTime();
      long frameDuration = end - start;
      System.out.println(frameDuration / 1000000);
      try {
        Thread.sleep(FRAME_MILLIS);
      } catch (InterruptedException e) {
        System.out.println("Frame delay issue detected");
      }
    }
  }
}
