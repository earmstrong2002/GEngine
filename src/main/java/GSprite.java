import org.jetbrains.annotations.NotNull;

public interface GSprite extends Drawable {
  GPoint getPosition();

  void setPosition(@NotNull GPoint position);
}
