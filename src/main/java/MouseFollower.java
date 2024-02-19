import java.awt.*;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

public class MouseFollower implements GObject {
  private GSprite sprite;
  private double acceleration;
  private int deadZoneRadius;
  private Point location;

  public MouseFollower(GSprite sprite, int acceleration, int deadZoneRadius) {
    setSprite(sprite);
    setAcceleration(acceleration);
    setDeadZoneRadius(deadZoneRadius);
  }

  public void update(GPanel panel) {
    Point mousePosition = panel.getMousePosition();
    if (mousePosition != null) {
      setLocation(mousePosition);
      getSprite().setPosition(mousePosition);
    }
  }

  public GSprite getSprite() {
    return sprite;
  }

  public void setSprite(@NotNull GSprite sprite) {
    this.sprite = sprite;
  }

  public double getAcceleration() {
    return acceleration;
  }

  public void setAcceleration(int acceleration) {
    this.acceleration = acceleration;
  }

  public int getDeadZoneRadius() {
    return deadZoneRadius;
  }

  public void setDeadZoneRadius(int deadZoneRadius) {
    this.deadZoneRadius = deadZoneRadius;
  }

  public Point getLocation() {
    return location;
  }

  public void setLocation(Point location) {
    this.location = location;
  }
}
