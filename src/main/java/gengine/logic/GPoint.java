package gengine.logic;

import java.awt.*;

/**
 * The GPoint class represents a coordinate pair. The x and y coordinates are in meters, as per the
 * standard in Gengine.
 */
public class GPoint {
  /** The x-coordinate of this point. */
  public double x;

  /** The y-coordinate of this point. */
  public double y;

  /**
   * Instantiates a GPoint, initializing the coordinates to the values in the given Point.
   *
   * @param point the point whose coordinates are to be copied.
   */
  public GPoint(Point point) {
    this.x = point.x;
    this.y = point.y;
  }

  /** Instantiates a GPoint at the default coordinates of (0, 0). */
  public GPoint() {
    this(0.0, 0.0);
  }

  /**
   * Instantiates a GPoint at the given coordinates.
   *
   * @param x x coordinate to use for the new GPoint.
   * @param y y coordinate to use for the new GPoint.
   */
  public GPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Calculates the distance between this point and another.
   *
   * @param other the other point to be used in the distance calculation.
   * @return the distance between this point and the other.
   */
  public double distance(GPoint other) {
    return distance(this, other);
  }

  /**
   * Calculates the distance between two points.
   *
   * @param gPoint1 one of the two points to be used in the distance calculation
   * @param gPoint2 one of the two points to be used in the distance calculation
   * @return the distance between the two given points.
   */
  public static double distance(GPoint gPoint1, GPoint gPoint2) {
    double deltaX = gPoint1.x - gPoint2.x;
    double deltaY = gPoint1.y - gPoint2.y;
    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }
}
