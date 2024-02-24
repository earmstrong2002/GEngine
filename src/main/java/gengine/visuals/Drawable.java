package gengine.visuals;

import java.awt.*;

/** An interface for anything to be drawn to a component in a way defined by client code. */
public interface Drawable {
  /**
   * Draws the object.
   *
   * @param graphics graphics context to use for drawing.
   */
  void draw(Graphics graphics);
}
