import java.awt.*;
import java.util.logging.Logger;

public class GEngine {
  public static final int PIXELS_PER_METER = 10;
  private static final Dimension WINDOW_SIZE = new Dimension(1024, 512);
  private static final int FRAME_RATE = 60;
  private static final long FRAME_NANOS = (long) 1e9 / FRAME_RATE;
  private static Logger log = Logger.getLogger(GEngine.class.getName());

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
      long startNanos = System.nanoTime();
      gPanel.update();
      preciseWait(startNanos);
    }
  }

  private static void preciseWait(long startNanos, long durationNanos) {
    while (System.nanoTime() - startNanos < durationNanos - 3e6) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        log.info("Thread was interrupted while sleeping.");
      }
    }
    while (System.nanoTime() - startNanos < durationNanos) {
      // Spinlock for the remaining duration of the frame.
    }
  }
}
