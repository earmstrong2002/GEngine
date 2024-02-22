package gengine;

import gengine.logic.GPoint;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

/**
 * The GPanel class provides a convenience layer between client code and JPanel, specifically geared
 * toward simple game development.
 */
public class GPanel extends JPanel {
  /** A list of all GObjects contained by this panel. */
  private final List<GObject> children;
  private final RenderLayerComparator renderLayerComparator = new RenderLayerComparator();

  private final List<Integer> keysPressed;
  public static final int ALWAYS_ON_TOP = 0;

  private final Point mousePosition;
  private boolean mouseIsPressed;

  /** Instantiates a new GPanel. */
  public GPanel() {
    super();
    children = new ArrayList<>();
    keysPressed = new ArrayList<>();
    mousePosition = new Point();
  }

  public GEventManager getEventManager() {
    return new GEventManager();
  }

  /**
   * Adds a GObject to this panel.
   *
   * @param object the GObject to add
   */
  public void addGObject(@NotNull GObject object) {
    addGObject(object,children.size());
  }

  public void addGObject(@NotNull GObject object, int index) {
    if (index > children.size() || index < 0) {
      throw new IllegalArgumentException(
          String.format(
              "Cannot add %s to %s at index %d because %d is invalid index .",
              object, this, index, index));
    }
    children.add(index, object);
    balanceChildren();
  }

  private void balanceChildren() {
    children.sort(renderLayerComparator);
  }

  /** Updates all GObjects in this panel, then repaints this panel. */
  public void update() {
    updateMousePosition();
    ping();
    paintImmediately(getBounds());
  }

  private void updateMousePosition() {
    Point newPosition = getMousePosition();
    if (newPosition != null) {
      mousePosition.setLocation(newPosition);
    }
  }

  private void ping() {
    for (GObject child : children) {
      child.update(this);
    }
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
   * @return The position of the mouse cursor relative to the panel, in meters.
   */
  public GPoint getMousePositionMeters() {
    return GPoint.pixelsToMeters(mousePosition);
  }

  public boolean mouseIsPressed() {
    return mouseIsPressed;
  }

  private void setMouseIsPressed(boolean b) {
    mouseIsPressed = b;
  }

  private class GEventManager
      implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    /**
     * Invoked when a key has been typed. See the class description for {@link KeyEvent} for a
     * definition of a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Invoked when a key has been pressed. See the class description for {@link KeyEvent} for a
     * definition of a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
      keysPressed.add(e.getKeyCode());
    }

    /**
     * Invoked when a key has been released. See the class description for {@link KeyEvent} for a
     * definition of a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
      keysPressed.remove(e.getKeyCode());
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
      setMouseIsPressed(true);
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      setMouseIsPressed(false);
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Invoked when a mouse button is pressed on a component and then dragged. {@code MOUSE_DRAGGED}
     * events will continue to be delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position is within the bounds of
     * the component).
     *
     * <p>Due to platform-dependent Drag&amp;Drop implementations, {@code MOUSE_DRAGGED} events may
     * not be delivered during a native Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {}

    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been
     * pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {}

    /**
     * Invoked when the mouse wheel is rotated.
     *
     * @param e the event to be processed
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {}
  }
  public static class RenderLayerComparator implements Comparator<GObject> {

    @Override
    public int compare(GObject o1, GObject o2) {
      return o1.getRenderLayer() - o2.getRenderLayer();
    }
  }
}
