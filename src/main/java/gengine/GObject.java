package gengine;

import gengine.drawable.GSprite;

/** The GObject interface represents game objects. */
public interface GObject {
  /**
   * Retrieves this GObject's sprite.
   *
   * @return the sprite associated with this object.
   */
  GSprite getSprite();

  /**
   * Performs all tasks necessary for this object in a single frame.
   *
   * @param panel the panel that contains this object.
   */
  void update(GPanel panel);
}
