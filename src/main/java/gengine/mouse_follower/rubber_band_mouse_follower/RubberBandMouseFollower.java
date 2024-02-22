package gengine.mouse_follower.rubber_band_mouse_follower;

import gengine.GPanel;
import gengine.drawable.GSprite;
import gengine.logic.GPoint;
import gengine.logic.GVector;
import gengine.mouse_follower.MouseFollower;
import org.jetbrains.annotations.NotNull;

public class RubberBandMouseFollower extends MouseFollower {
  private GSprite sprite;
  private int renderLayer;
  private double accelerationRate;
  private double accelerationRateWhileMouseIsPressed;
  private GVector velocity;
  private double deadZoneRadius;
  private GPoint position;

  public RubberBandMouseFollower(
      @NotNull RubberBandMouseFollowerSprite sprite,
      double accelerationRate,
      double accelerationRateWhileCLicked,
      double deadZoneRadius) {
    super(sprite, accelerationRate, deadZoneRadius);
    setVelocity(new GVector());
    setAccelerationRateWhileMouseIsPressed(accelerationRateWhileCLicked);
    setRenderLayer(1);
  }

  private void setRenderLayer(int renderLayer) {
    if (renderLayer < 0) {
      throw new IllegalArgumentException(
          renderLayer + " is not a valid render layer because it is negative.");
    }
    this.renderLayer = renderLayer;
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
              * (panel.mouseIsPressed() ? accelerationRateWhileMouseIsPressed : accelerationRate);
      velocity.setMagnitude(Math.max(newMagnitude, 0));
      velocity.setDirection(Math.atan2(deltaY, deltaX));
      ((RubberBandMouseFollowerSprite) getSprite()).setMousePosition(mousePosition);
    }
    ((RubberBandMouseFollowerSprite) getSprite()).setDrawConnection(panel.mouseIsPressed());
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
          "Sprite must be an instance of mouse_follower.RubberBandMouseFollowerSprite");
    }
    this.sprite = sprite;
  }

  @Override
  public boolean isAlwaysOnTop() {
    return false;
  }

  @Override
  public int getRenderLayer() {
    return 0;
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

  private void reset() {
    setPosition(new GPoint());
    setVelocity(new GVector());
  }

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
