/** The GVector class represents a vector with magnitude and direction. */
public class GVector {
  /** The magnitude of the vector. The units of this vector depend on the type of the vector: */
  private double magnitude;

  /**
   * The direction of the vector, in radians. Angle progresses clockwise from 0 being directly east.
   */
  private double direction;

  /** Constructs a GVector with default values of zero for both magnitude and direction. */
  public GVector() {
    this(0.0, 0.0);
  }

  /**
   * Constructs a GVector with the given magnitude and direction values.
   *
   * @param magnitude magnitude of the vector. Cannot be negative.
   * @param direction direction of the vector, in radians.
   */
  public GVector(double magnitude, double direction) {
    setMagnitude(magnitude);
    setDirection(direction);
  }

  /**
   * Retrieves the magnitude of this vector.
   *
   * @return the magnitude of this vector.
   */
  public double getMagnitude() {
    return magnitude;
  }

  /**
   * Sets the magnitude of this vector to the given value.
   *
   * @param magnitude new magnitude of the vector. Must be non-negative.
   */
  public void setMagnitude(double magnitude) {
    checkMagnitude(magnitude);
    this.magnitude = magnitude;
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
   * Retrieves the direction of this vector.
   *
   * @return the direction of this vector, in radians.
   */
  public double getDirection() {
    return direction;
  }

  /**
   * Sets the direction of this vector.
   *
   * @param direction new direction of the vector, in radians.
   */
  public void setDirection(double direction) {
    this.direction = direction;
  }
}
