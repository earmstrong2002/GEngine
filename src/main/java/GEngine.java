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
    Circle circle = new Circle(1, new GPoint(), Color.WHITE, Color.RED, 1.3);
    RubberBandMouseFollowerSprite mouseFollowerSprite =
        new RubberBandMouseFollowerSprite(circle, new GPoint());
    MouseFollower mouseFollower = new RubberBandMouseFollower(mouseFollowerSprite, 0.02, 0.1, 1);
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
    long sleepImprecisionAllowanceMillis = 10;
    long millisToSleep =
        calcMillisToSleep(targetNanos, System.nanoTime(), sleepImprecisionAllowanceMillis);
    try {
      Thread.sleep(millisToSleep);
    } catch (InterruptedException e) {
      log.warning(e.getMessage());
    }
    // Spinlock for the remaining duration of the frame.
    //noinspection StatementWithEmptyBody
    while (System.nanoTime() < targetNanos) {}
  }

  private static long calcMillisToSleep(
      long targetNanos, long currentNanos, long sleepImprecisionAllowanceMillis) {
    long durationNanos = targetNanos - currentNanos;
    long durationMillis = durationNanos / (long) 1e9;
    long durationMillisWithAllowance = durationMillis - sleepImprecisionAllowanceMillis;
    return Math.max(durationMillisWithAllowance, 0);
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
