import org.jetbrains.annotations.NotNull;

public class MouseFollower implements GObject {
  private GSprite sprite;
  private double acceleration;
  private int deadZoneRadius;

  public MouseFollower(GSprite sprite, int acceleration, int deadZoneRadius) {}

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
}
