import java.awt.*;

public class MouseFollower implements GObject {
  private Circle sprite;

  public MouseFollower(int radius) {
    sprite = new Circle(radius, new Point());
  }

  public GShape getSprite() {
    return sprite;
  }
}
