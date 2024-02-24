package gengine;

import gengine.logic.GPoint;
import gengine.mouse_follower.MouseFollower;
import gengine.mouse_follower.rubber_band_mouse_follower.RubberBandMouseFollower;
import gengine.mouse_follower.rubber_band_mouse_follower.RubberBandMouseFollowerSprite;
import gengine.visuals.Circle;
import gengine.visuals.GColors;
import java.awt.*;
import java.util.logging.Logger;

/**
 * The GEngine class contains the runnable main method that sets up a GEngine instance and runs the
 * main game loop.
 */
public class GEngine {
  // TODO: Extract demo-specific stuff to its own class with a runnable main method; GEngine
  // should
  // not have that type of code in it.
  /** The conversion rate from the in-engine unit of distance, meters, to screen pixels. */
  public static final int PIXELS_PER_METER = 10;

  /** The size at which to initialize the game window. */
  private static final Dimension WINDOW_SIZE = new Dimension(1024, 512);

  /** Number of times per second to render a frame. */
  private static final int FRAME_RATE = 60;

  /**
   * The duration between one frame and the next.
   *
   * @see #FRAME_RATE
   */
  private static final long FRAME_NANOS = (long) 1e9 / FRAME_RATE;

  /**
   * The safety margin to use for preventing {@link java.lang.Thread#sleep} from introducing too
   * much delay between frames.
   *
   * @see #preciseWait
   * @see #calcMillisToSleep
   */
  private static final long SLEEP_IMPRECISION_ALLOWANCE_MILLIS = 10;

  /**
   * The logger to use :) I don't know much about logging in Java so idk what I'm really doing with
   * this.
   */
  private static final Logger log = Logger.getLogger(GEngine.class.getName());

  /**
   * Sets up all the elements of the game, then calls {@link #mainLoop} to initiate the game.
   *
   * @param args command line arguments (unused).
   */
  public static void main(String[] args) {
    GPanel gPanel = new GPanel();
    gPanel.setBackground(GColors.PIGMENT_GREEN);
    Circle circle = new Circle(1, new GPoint(), GColors.NYANZA, GColors.INDIA_GREEN, 1.3);
    RubberBandMouseFollowerSprite mouseFollowerSprite =
        new RubberBandMouseFollowerSprite(circle, new GPoint());
    MouseFollower mouseFollower = new RubberBandMouseFollower(mouseFollowerSprite, 0.02, 0.1, 1);
    gPanel.addGObject(mouseFollower);
    gPanel.addGObject(new DebugDisplay());
    gPanel.addMouseListener(gPanel.getEventManager());
    GFrame gFrame = new GFrame(gPanel);
    gFrame.setTitle("Mouse Following Demo");
    gFrame.setSize(WINDOW_SIZE);
    gFrame.setVisible(true);
    mainLoop(gPanel);
  }

  /**
   * Executes all game logic repeatedly at a rate of {@link #FRAME_RATE} per second.
   *
   * @param gPanel The primary panel used to display game elements.
   */
  private static void mainLoop(GPanel gPanel) {
    while (true) {
      long startNanos = System.nanoTime();
      gPanel.update();
      preciseWait(startNanos, FRAME_NANOS);
    }
  }

  /**
   * Pauses execution on the thread until the given duration has passed after the given start time.
   * This method uses a combination of {@link java.lang.Thread#sleep} and spin lock to achieve a far
   * more precise pause than {@link java.lang.Thread#sleep} alone without sacrificing as much
   * performance as exclusively spin locking.
   *
   * @param startNanos The return from {@link java.lang.System#nanoTime} at the beginning of the
   *     desired delay period. This method will pause execution until the desired duration has
   *     passed since this time.
   * @param durationNanos The duration of the pause, in nanoseconds. If time has passed since the
   *     start time defined in the other parameter of this method, then the time this method
   *     actually pauses execution will be less than this duration by the amount of time that has
   *     passed since the start time.
   * @see <a href="https://blog.bearcats.nl/accurate-sleep-function/">Blat Blatnik's tutorial on the
   *     matter</a>
   */
  public static void preciseWait(long startNanos, long durationNanos) {
    long targetNanos = startNanos + durationNanos;
    long millisToSleep =
        calcMillisToSleep(targetNanos, System.nanoTime(), SLEEP_IMPRECISION_ALLOWANCE_MILLIS);
    // Sleep for a bit less than the desired duration to allow room for the imprecision of thread
    // sleeping.
    try {
      Thread.sleep(millisToSleep);
    } catch (InterruptedException e) {
      log.warning(e.getMessage());
    }
    // Spinlock for the remaining duration.
    //noinspection StatementWithEmptyBody
    while (System.nanoTime() < targetNanos) {}
  }

  /**
   * Calculates the number of milliseconds to sleep, considering the difference between the current
   * time and target sleep time, as well as allowance for the imprecision of thread sleeping.
   *
   * @param targetNanos Desired end time of the sleep.
   * @param currentNanos current nano time.
   * @param sleepImprecisionAllowanceMillis number of milliseconds to allow to account for sleep
   *     imprecision.
   * @return number of milliseconds for which to sleep before spin-locking for the remaining
   *     duration of the pause.
   */
  private static long calcMillisToSleep(
      long targetNanos, long currentNanos, long sleepImprecisionAllowanceMillis) {
    long durationNanos = targetNanos - currentNanos;
    long durationMillis = durationNanos / (long) 1e9;
    long durationMillisWithAllowance = durationMillis - sleepImprecisionAllowanceMillis;
    return Math.max(durationMillisWithAllowance, 0);
  }

  /**
   * Converts a pixel measure to a meter measure.
   *
   * @param pixels number of pixels in the measure.
   * @return amount of meters equivalent to the given number of pixels.
   * @see #PIXELS_PER_METER
   */
  public static double pixelsToMeters(int pixels) {
    return (double) pixels / PIXELS_PER_METER;
  }

  /**
   * Converts a meter measure to a pixel measure.
   *
   * @param meters amount of meters in the measure.
   * @return number of pixels equivalent to the given amount of meters.
   */
  public static int metersToPixels(double meters) {
    if (Double.isInfinite(meters) || Double.isNaN(meters)) {
      throw new IllegalArgumentException("Cannot convert " + meters + " meters to pixels.");
    }
    return (int) Math.round(meters * PIXELS_PER_METER);
  }
}
