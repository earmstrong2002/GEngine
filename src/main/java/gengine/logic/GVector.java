package gengine.logic;

/** The GVector class represents a vector with magnitude and direction. */
public class GVector {
  private double x;
  private double y;

  /** Constructs a GVector with default values of zero for both magnitude and direction. */
  public GVector() {
    this(0.0, 0.0);
  }

  public GVector(double x, double y) {
    setX(x);
    setY(y);
  }

  private void setX(double x) {
    this.x = x;
  }

  private void setY(double y) {
    this.y = y;
  }

  /**
   * Ensures the given magnitude value is non-negative.
   *
   * @param magnitude magnitude value to check.
   */
  private void checkMagnitude(double magnitude) {
    if (magnitude < 0) {
      throw new IllegalArgumentException(
          "Vector magnitude value " + magnitude + "is not valid because it is negative.");
    }
  }

  /**
   * Retrieves the magnitude of the x component of this vector.
   *
   * @return the magnitude of the x component of this vector.
   */
  public double getXMagnitude() {
    return x;
  }  /**
   * Retrieves the magnitude of this vector.
   *
   * @return the magnitude of this vector.
   */

  public double getMagnitude() {
    return Math.sqrt((x * x) + (y * y));
  }

  /**
   * Retrieves the magnitude of the y component of this vector.
   *
   * @return the magnitude of the y component of this vector.
   */
  public double getYMagintude() {
    return y;
  }
  /**
   * Sets the magnitude of this vector to the given value.
   *
   * @param magnitude new magnitude of the vector. Must be non-negative.
   */

  public void setMagnitude(double magnitude) {
    double direction = getDirection();
    setX(magnitude * Math.cos(direction));
    setY(magnitude * Math.sin(direction));
  }


  /**
   * Retrieves the direction of this vector.
   *
   * @return the direction of this vector, in radians.
   */

  public double getDirection() {
    return Math.atan2(y, x);
  }

  /**
   * Sets the direction of this vector.
   *
   * @param direction new direction of the vector, in radians.
   */
  public void setDirection(double direction) {
    double magnitude = getMagnitude();
    setX(magnitude * Math.cos(direction));
    setY(magnitude * Math.sin(direction));
  }
}
