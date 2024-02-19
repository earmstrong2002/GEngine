import java.awt.*;
import java.util.logging.Logger;

public class GEngine {
  // TODO: Extract demo-specific stuff to its own class with a runnable main method; GEngine should
  // not have that type of code in it.
  public static final int PIXELS_PER_METER = 10;
  private static final Dimension WINDOW_SIZE = new Dimension(1024, 512);
  private static final int FRAME_RATE = 60;
  private static final long FRAME_NANOS = (long) 1e9 / FRAME_RATE;
  private static Logger log = Logger.getLogger(GEngine.class.getName());

  public static void main(String[] args) {
    GPanel gPanel = new GPanel();
    gPanel.setBackground(Color.BLACK);
    RubberBandMouseFollower mouseFollower =
        new RubberBandMouseFollower(new Circle(1, new GPoint(), Color.white), 0.1, 1);
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
      preciseWait(startNanos, FRAME_NANOS);
    }
  }

  public static void preciseWait(long startNanos, long durationNanos) {
    long targetNanos = startNanos + durationNanos;
    long conservativeTargetNanos = targetNanos - (long) 3e6;
    // While there is enough time left to wait that we needn't worry about the imprecision of
    // Thread::sleep, sleep one millisecond at a time.
    while (System.nanoTime() < conservativeTargetNanos) {
      try {
        //noinspection BusyWait
        Thread.sleep(1);
      } catch (InterruptedException e) {
        log.info("Thread was interrupted while sleeping.");
      }
    }
    // Spinlock for the remaining duration of the frame.
    //noinspection StatementWithEmptyBody
    while (System.nanoTime() < targetNanos) {}
  }

  public static double pixelsToMeters(int pixels) {
    return (double) pixels / PIXELS_PER_METER;
  }

  public static int metersToPixels(double meters) {
    if (Double.isInfinite(meters) || Double.isNaN(meters)) {
      throw new IllegalArgumentException("Cannot convert " + meters + " meters to pixels.");
    }
    return (int) Math.round(meters * PIXELS_PER_METER);
  }
}
