import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.jetbrains.annotations.NotNull;

public class MouseFollower implements GObject, MouseListener {
  // TODO: Convert MouseFollower to an abstract class and move current implementation to a subclass.
  private GSprite sprite;
  private double accelerationRate;
  private GVector velocity;
  private double deadZoneRadius;
  private GPoint position;

  public MouseFollower(GSprite sprite, double accelerationRate, double deadZoneRadius) {
    setSprite(sprite);
    setPosition(new GPoint());
    setAccelerationRate(accelerationRate);
    setVelocity(new GVector());
    setDeadZoneRadius(deadZoneRadius);
  }

  public void update(GPanel panel) {
    GPoint mousePosition = panel.getMousePositionMeters();
    if (mousePosition != null) {
      double deltaX = mousePosition.x - position.x;
      double deltaY = mousePosition.y - position.y;
      double distance = mousePosition.distance(getPosition());
      double newMagnitude = (distance - deadZoneRadius) * accelerationRate;
      velocity.setMagnitude(Math.max(newMagnitude, 0));
      velocity.setDirection(Math.atan2(deltaY, deltaX));
    }
    updatePosition();
  }

  private void updatePosition() {
    double deltaX = velocity.getXMagnitude();
    double deltaY = velocity.getYMagintude();
    GPoint newPosition = new GPoint((position.x + deltaX), (position.y + deltaY));
    setPosition(newPosition);
  }

  public GPoint getPosition() {
    return position;
  }

  public void setPosition(GPoint position) {
    this.position = position;
    getSprite().setPosition(position);
  }

  public GSprite getSprite() {
    return sprite;
  }

  public void setSprite(@NotNull GSprite sprite) {
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
    reset();
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
  public void mousePressed(MouseEvent e) {}

  /**
   * Invoked when a mouse button has been released on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseReleased(MouseEvent e) {}

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
}
