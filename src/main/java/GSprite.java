import java.awt.*;
import org.jetbrains.annotations.NotNull;

public interface GSprite extends Drawable {
  Point getPosition();

  void setPosition(@NotNull Point position);
}
