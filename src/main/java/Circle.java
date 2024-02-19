import java.awt.*;
import org.jetbrains.annotations.NotNull;

/** A class representing a circle that can draw itself. */
public class Circle implements GSprite {

  /** The radius of the circle, in pixels. */
  private double radius;

  /** The coordinates of the center of the circle. */
  private GPoint position;

  /** The color of the circle. */
  private Color color;

  /**
   * Instantiates a circle with the given radius, position, and color.
   *
   * @param radius the radius of the circle, in meters
   * @param position the position of the circle
   * @param color the color of the circle.
   */
  public Circle(double radius, GPoint position, Color color) {
    // TODO allow for differing stroke and fill color
    this.radius = radius;
    this.position = position;
    this.color = color;
  }

  /**
   * Draws the circle to the screen.
   *
   * @param graphics graphics context to use for drawing.
   */
  @Override
  public void draw(Graphics graphics) {
    int x = (int) (position.x * Main.PIXELS_PER_METER - radius);
    int y = (int) (position.y * Main.PIXELS_PER_METER - radius);
    int diameter = (int) (radius * Main.PIXELS_PER_METER * 2);
    graphics.setColor(color);
    graphics.fillOval(x, y, diameter, diameter);
  }

  /**
   * Retrieves the position of this circle.
   *
   * @return the position of this circle.
   */
  @Override
  public GPoint getPosition() {
    return position;
  }

  /**
   * Sets the position of the circle
   *
   * @param position the new position of this circle.
   */
  @Override
  public void setPosition(@NotNull GPoint position) {
    this.position = position;
  }
}
