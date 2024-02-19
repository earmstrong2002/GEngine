import org.jetbrains.annotations.NotNull;

/** The GSprite iterface represents a drawable sprite associated with a GObject. */
public interface GSprite extends Drawable {
  /**
   * Retrieves the position of this sprite.
   *
   * @return the position of this sprite.
   */
  GPoint getPosition();

  /**
   * Sets the position of this sprite.
   *
   * @param position the new position of this sprite.
   */
  void setPosition(@NotNull GPoint position);
}
