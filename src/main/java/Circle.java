import java.awt.*;

/** A class representing a circle that can draw itself. */
public class Circle implements GSprite {

  /** The radius of the circle, in pixels. */
  private int radius;

  /** The coordinates of the center of the circle. */
  private Point location;

  /** The color of the circle. */
  private Color color;

  public Circle(int radius, Point location) {
    this.radius = radius;
    this.location = location;
  }

  /**
   * Draws the circle to the screen.
   *
   * @param graphics graphics context to use for drawing.
   */
  @Override
  public void draw(Graphics graphics) {
    int x = location.x - radius;
    int y = location.y - radius;
    int diameter = radius * 2;
    graphics.setColor(color);
    graphics.fillOval(x, y, diameter, diameter);
  }
}
