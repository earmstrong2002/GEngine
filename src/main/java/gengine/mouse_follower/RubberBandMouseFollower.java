package gengine.mouse_follower;

import gengine.GPanel;
import gengine.drawable.GSprite;
import gengine.logic.GPoint;
import gengine.logic.GVector;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.jetbrains.annotations.NotNull;

public class RubberBandMouseFollower extends MouseFollower implements MouseListener {
  private GSprite sprite;
  private double accelerationRate;
  private double accelerationRateWhileMouseIsPressed;
  private GVector velocity;
  private double deadZoneRadius;
  private GPoint position;
  private boolean mouseIsPressed;

  public RubberBandMouseFollower(
      @NotNull RubberBandMouseFollowerSprite sprite,
      double accelerationRate,
      double accelerationRateWhileCLicked,
      double deadZoneRadius) {
    super(sprite, accelerationRate, deadZoneRadius);
    setVelocity(new GVector());
    setAccelerationRateWhileMouseIsPressed(accelerationRateWhileCLicked);
  }

  @Override
  public void update(@NotNull GPanel panel) {
    GPoint mousePosition = panel.getMousePositionMeters();
    if (mousePosition != null) {
      double deltaX = mousePosition.x - position.x;
      double deltaY = mousePosition.y - position.y;
      double distance = mousePosition.distance(getPosition());
      double newMagnitude =
          (distance - deadZoneRadius)
              * (mouseIsPressed ? accelerationRateWhileMouseIsPressed : accelerationRate);
      velocity.setMagnitude(Math.max(newMagnitude, 0));
      velocity.setDirection(Math.atan2(deltaY, deltaX));
      ((RubberBandMouseFollowerSprite) getSprite()).setMousePosition(mousePosition);
    }
    ((RubberBandMouseFollowerSprite) getSprite()).setDrawConnection(mouseIsPressed);
    updatePosition();
  }

  public GPoint getPosition() {
    return position;
  }

  public GSprite getSprite() {
    return sprite;
  }

  private void updatePosition() {
    double deltaX = velocity.getXMagnitude();
    double deltaY = velocity.getYMagintude();
    GPoint newPosition = new GPoint((position.x + deltaX), (position.y + deltaY));
    setPosition(newPosition);
  }

  public void setPosition(@NotNull GPoint position) {
    this.position = position;
    getSprite().setPosition(position);
  }

  public void setSprite(@NotNull GSprite sprite) {
    if (!(sprite instanceof RubberBandMouseFollowerSprite)) {
      throw new IllegalArgumentException(
          "Sprite must be an instance of gengine.mouse_follower.RubberBandMouseFollowerSprite");
    }
    this.sprite = sprite;
  }

  public double getSpeed() {
    return getVelocity().getMagnitude();
  }

  public GVector getVelocity() {
    return velocity;
  }

  public void setVelocity(GVector velocity) {
    this.velocity = velocity;
  }

  public double getAccelerationRate() {
    return accelerationRate;
  }

  public void setAccelerationRate(double accelerationRate) {
    checkAccelerationRate(accelerationRate);
    this.accelerationRate = accelerationRate;
  }

  public double getDeadZoneRadius() {
    return deadZoneRadius;
  }

  public void setDeadZoneRadius(double deadZoneRadius) {
    this.deadZoneRadius = deadZoneRadius;
  }

  /**
   * Invoked when the mouse button has been clicked (pressed and released) on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    //    reset();
  }

  private void reset() {
    setPosition(new GPoint());
    setVelocity(new GVector());
  }

  /**
   * Invoked when a mouse button has been pressed on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mousePressed(MouseEvent e) {
    setMouseIsPressed(true);
  }

  private void setMouseIsPressed(boolean mouseIsPressed) {
    this.mouseIsPressed = mouseIsPressed;
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
   * events will continue to be delivered to the component where the drag originated until the mouse
   * button is released (regardless of whether the mouse position is within the bounds of the
   * component).
   *
   * <p>Due to platform-dependent Drag&amp;Drop implementations, {@code MOUSE_DRAGGED} events may
   * not be delivered during a native Drag&amp;Drop operation.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseDragged(MouseEvent e) {}

  /**
   * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseMoved(MouseEvent e) {}

  public double getAccelerationRateWhileMouseIsPressed() {
    return accelerationRateWhileMouseIsPressed;
  }

  public void setAccelerationRateWhileMouseIsPressed(double accelerationRateWhileMouseIsPressed) {
    checkAccelerationRate(accelerationRateWhileMouseIsPressed);
    this.accelerationRateWhileMouseIsPressed = accelerationRateWhileMouseIsPressed;
  }

  private void checkAccelerationRate(double accelerationRate) {
    if (accelerationRate <= 0) {
      throw new IllegalArgumentException("Acceleration rate must be greater than zero");
    }
  }
}
