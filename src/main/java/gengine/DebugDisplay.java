package gengine;

import gengine.drawable.GSprite;
import gengine.logic.GPoint;
import java.awt.*;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

public class DebugDisplay implements GObject {
  public static final Color BACKGROUND_COLOR = Color.WHITE;
  public static final Color TEXT_COLOR = Color.BLACK;
  private final DebugDisplayPanel panel;

  public DebugDisplay() {
    panel = configurePanel();
    addWidgets();
  }

  private DebugDisplayPanel configurePanel() {
    DebugDisplayPanel panel = new DebugDisplayPanel();
    panel.setBackground(BACKGROUND_COLOR);
    panel.setForeground(TEXT_COLOR);
    panel.setPosition(new GPoint(0, 0));
    return panel;
  }

  private void addWidgets() {
    addFpsWidget();
    addRenderTimeWidget();
  }

  private void addFpsWidget() {
    JLabel fpsWidget = new JLabel("FPS:\t");
    panel.add(fpsWidget);
  }

  private void addRenderTimeWidget() {
    JLabel renderTimeWidget = new JLabel("Render Time:\t");
    panel.add(renderTimeWidget);
  }

  private Component createFpsWidget() {
    return null;
  }

  @Override
  public GSprite getSprite() {
    return panel;
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
      super.setBounds(new Rectangle(GPoint.metersToPixels(position), new Dimension(1000, 100)));
    }
  }
}
