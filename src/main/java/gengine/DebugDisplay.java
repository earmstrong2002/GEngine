package gengine;

import gengine.drawable.GSprite;
import gengine.logic.GPoint;
import java.awt.*;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

public class DebugDisplay implements GObject {
  public static final Color backgroundColor = new Color(0, 0, 0, 128);
  private GSprite debugDisplayPanel = new DebugDisplayPanel();

  public DebugDisplay() {
    getSprite().setPosition(new GPoint());
  }

  @Override
  public GSprite getSprite() {
    return debugDisplayPanel;
  }

  @Override
  public void update(GPanel panel) {}

  @Override
  public boolean isAlwaysOnTop() {
    return true;
  }

  @Override
  public int getRenderLayer() {
    return 0;
  }


  private static class DebugDisplayPanel extends JPanel implements GSprite {
    private static Dimension size = new Dimension(100, 100);
    @Override
    public void draw(Graphics graphics) {
      super.paintImmediately(getBounds());
    }

    @Override
    public GPoint getPosition() {
      return null;
    }

    @Override
    public void setPosition(@NotNull GPoint position) {
      setBounds(new Rectangle(GPoint.metersToPixels(position), size));
    }
  }
}
