package gengine.visuals;

import gengine.GEngine;
import gengine.logic.GPoint;
import java.awt.*;
import org.jetbrains.annotations.NotNull;

/** A class representing a circle that can draw itself. */
public class Circle implements Drawable {

  /** The radius of the circle, in pixels. */
  private double radius;

  // TODO: It may be possible to cast the Graphics object passed to the draw method to Graphics2D,
  // which would allow for varied stroke weight and such. Consider changing it to use that instead
  // of kluging the stroke.
  private double strokeRadius;

  /** The coordinates of the center of the circle. */
  private GPoint position;

  /** The color of the circle. */
  private Color color;

  private Color strokeColor;

  /**
   * Instantiates a circle with the given radius, position, fill color, stroke color, and stroke
   * width.
   *
   * @param radius the radius of the circle, in meters
   * @param position the position of the circle
   * @param fillColor the fill color of the circle.
   */
  public Circle(
      double radius,
      @NotNull GPoint position,
      @NotNull Color fillColor,
      @NotNull Color strokeColor,
      double strokeRadius) {
    // TODO allow for differing stroke and fill fillColor
    setRadius(radius);
    setStrokeRadius(strokeRadius);
    setPosition(position);
    setFillColor(fillColor);
    setStrokeColor(strokeColor);
  }

  public Color getColor() {
    return color;
  }

  /**
   * Draws the circle to the screen.
   *
   * @param graphics graphics context to use for drawing.
   */
  @Override
  public void draw(Graphics graphics) {
    drawStroke(graphics);
    drawFill(graphics);
  }

  private void drawStroke(Graphics graphics) {
    graphics.setColor(getStrokeColor());
    int x = GEngine.metersToPixels(position.x - strokeRadius);
    int y = GEngine.metersToPixels((position.y - strokeRadius));
    int diameter = GEngine.metersToPixels(strokeRadius * 2);
    graphics.setColor(strokeColor);
    graphics.fillOval(x, y, diameter, diameter);
  }

  private void drawFill(Graphics graphics) {
    int x = (GEngine.metersToPixels(position.x - radius));
    int y = (GEngine.metersToPixels(position.y - radius));
    int diameter = GEngine.metersToPixels(radius * 2);
    graphics.setColor(color);
    graphics.fillOval(x, y, diameter, diameter);
  }

  public Color getStrokeColor() {
    return strokeColor;
  }

  /**
   * Retrieves the position of this circle.
   *
   * @return the position of this circle.
   */
  public GPoint getPosition() {
    return position;
  }

  public double getStrokeRadius() {
    return strokeRadius;
  }

  public void setStrokeRadius(double strokeRadius) {
    if (strokeRadius <= getRadius()) {
      throw new IllegalArgumentException("Stroke radius must be greater than fill radius.");
    }
    this.strokeRadius = strokeRadius;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    if (radius <= 0) {
      throw new IllegalArgumentException("Radius must be greater than zero");
    }
    this.radius = radius;
  }

  /**
   * Sets the position of the circle
   *
   * @param position the new position of this circle.
   */
  public void setPosition(@NotNull GPoint position) {
    this.position = position;
  }

  public void setFillColor(@NotNull Color color) {
    this.color = color;
  }

  public void setStrokeColor(@NotNull Color strokeColor) {
    this.strokeColor = strokeColor;
  }
}
