import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

/**
 * The GPanel class provides a convenience layer between client code and JPanel, specifically geared
 * toward simple game development.
 */
public class GPanel extends JPanel {

  /** A list of all GObjects contained by this panel. */
  List<GObject> children;

  /** Instantiates a new GPanel. */
  public GPanel() {
    super();
    children = new LinkedList<>();
  }

  /**
   * Adds a GObject to this panel.
   *
   * @param object the GObject to add
   */
  public void addGObject(@NotNull GObject object) {
    children.add(object);
  }

  /** Updates all GObjects in this panel, then repaints this panel. */
  public void update() {
    for (GObject child : children) {
      child.update(this);
    }
    paintImmediately(getBounds());
  }

  /**
   * Renders one frame.
   *
   * @param graphics the <code>Graphics</code> context in which to paint
   */
  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    for (GObject object : children) {
      object.getSprite().draw(graphics);
    }
  }

  /**
   * Retrieves the position of the mouse cursor relative to the panel, in meters (as opposed to the
   * return of {@code getMousePosition()}, which is in pixels).
   *
   * @return
   */
  public GPoint getMousePositionMeters() {
    Point mousePosition = getMousePosition();
    if (mousePosition == null) {
      return null;
    }
    double x = GEngine.pixelsToMeters(mousePosition.x);
    double y = GEngine.pixelsToMeters(mousePosition.y);
    return new GPoint(x, y);
  }
}
