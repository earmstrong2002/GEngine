package gengine;

import gengine.drawable.GSprite;
import gengine.logic.GPoint;
import java.awt.*;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

public class DebugDisplay implements GObject {
  public static final Color backgroundColor = new Color(0, 0, 0, 128);

  public DebugDisplay() {}

  @Override
  public GSprite getSprite() {
    return null;
  }

  @Override
  public void update(GPanel panel) {}

  @Override
  public boolean isAlwaysOnTop() {
    return true;
  }

  private class DebugDisplayPanel extends JPanel implements GSprite {
    @Override
    public void draw(Graphics graphics) {
      super.paintImmediately(getBounds());
    }

    @Override
    public GPoint getPosition() {
      return null;
    }

    @Override
    public void setPosition(@NotNull GPoint position) {}
  }
}
