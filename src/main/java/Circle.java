import java.awt.*;
import org.jetbrains.annotations.NotNull;

/** A class representing a circle that can draw itself. */
public class Circle implements GSprite {

  /** The radius of the circle, in pixels. */
  private int radius;

  /** The coordinates of the center of the circle. */
  private GPoint position;

  /** The color of the circle. */
  private Color color;

  public Circle(int radius, GPoint position, Color color) {
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
    int diameter = radius * 2;
    graphics.setColor(color);
    graphics.fillOval(x, y, diameter, diameter);
  }

  @Override
  public GPoint getPosition() {
    return position;
  }

  @Override
  public void setPosition(@NotNull GPoint position) {
    this.position = position;
  }
}
