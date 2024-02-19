import java.awt.*;

public class GPoint {
  public double x;
  public double y;

  public GPoint(Point point) {
    this.x = point.x;
    this.y = point.y;
  }

  public GPoint() {
    this(0.0, 0.0);
  }

  public GPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(GPoint other) {
    return distance(this, other);
  }

  public static double distance(GPoint gPoint1, GPoint gPoint2) {
    double deltaX = gPoint1.x - gPoint2.x;
    double deltaY = gPoint1.y - gPoint2.y;
    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }
}
