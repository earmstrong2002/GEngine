import java.awt.*;
import org.jetbrains.annotations.NotNull;

public class RubberBandMouseFollowerSprite implements GSprite {
  private Circle head;
  private GPoint position;
  private boolean drawConnection;
  private GPoint mousePosition;

  public RubberBandMouseFollowerSprite(@NotNull Circle head, @NotNull GPoint position) {
    setHead(head);
    setPosition(position);
  }

  public void setHead(@NotNull Circle head) {
    this.head = head;
  }

  /**
   * Draws the object.
   *
   * @param graphics graphics context to use for drawing.
   */
  @Override
  public void draw(@NotNull Graphics graphics) {
    head.draw(graphics);
    if (isDrawConnection()) {
      graphics.drawLine(
          GEngine.metersToPixels(position.x),
          GEngine.metersToPixels(position.y),
          GEngine.metersToPixels(mousePosition.x),
          GEngine.metersToPixels(mousePosition.y));
    }
  }

  public boolean isDrawConnection() {
    return drawConnection;
  }

  public void setDrawConnection(boolean drawConnection) {
    this.drawConnection = drawConnection;
  }

  /**
   * Retrieves the position of this sprite.
   *
   * @return the position of this sprite.
   */
  @Override
  public GPoint getPosition() {
    return position;
  }

  /**
   * Sets the position of this sprite.
   *
   * @param position the new position of this sprite.
   */
  @Override
  public void setPosition(@NotNull GPoint position) {
    head.setPosition(position);
    this.position = position;
  }

  public GPoint getMousePosition() {
    return mousePosition;
  }

  public void setMousePosition(@NotNull GPoint mousePosition) {
    this.mousePosition = mousePosition;
  }
}
