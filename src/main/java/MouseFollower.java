import org.jetbrains.annotations.NotNull;

public abstract class MouseFollower implements GObject {
  private GSprite sprite;
  private double accelerationRate;
  private double deadZoneRadius;
  private GPoint position;

  public MouseFollower(@NotNull GSprite sprite, double accelerationRate, double deadZoneRadius) {
    setSprite(sprite);
    setPosition(new GPoint());
    setAccelerationRate(accelerationRate);
    setDeadZoneRadius(deadZoneRadius);
  }

  @Override
  public abstract void update(@NotNull GPanel panel);

  public double getAccelerationRate() {
    return accelerationRate;
  }

  public void setAccelerationRate(double accelerationRate) {
    if (accelerationRate <= 0) {
      throw new IllegalArgumentException(
          "An acceleration rate of "
              + accelerationRate
              + " is not valid because it is not positive.");
    }
    this.accelerationRate = accelerationRate;
  }

  public double getDeadZoneRadius() {
    return deadZoneRadius;
  }

  public void setDeadZoneRadius(double deadZoneRadius) {
    if (deadZoneRadius <= 0) {
      throw new IllegalArgumentException(
          "A dead zone radius of " + deadZoneRadius + " is not valid because it is not positive.");
    }
    this.deadZoneRadius = deadZoneRadius;
  }

  public GPoint getPosition() {
    return position;
  }

  public void setPosition(@NotNull GPoint position) {
    this.position = position;
    getSprite().setPosition(position);
  }

  @Override
  public GSprite getSprite() {
    return sprite;
  }

  public void setSprite(@NotNull GSprite sprite) {
    this.sprite = sprite;
  }
}
