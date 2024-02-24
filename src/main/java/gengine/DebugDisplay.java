package gengine;

import gengine.logic.GPoint;
import gengine.visuals.GSprite;
import java.awt.*;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

public class DebugDisplay implements GObject {
  // TODO: Add actual functionality to debug display
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 127);
  private static final Color FOREGROUND_COLOR = Color.WHITE;

  private static final int TEXT_SIZE = 12;
  private static final Font DEBUG_FONT = new Font(Font.MONOSPACED, Font.PLAIN, TEXT_SIZE);
  private final DebugDisplayPanel panel;

  public DebugDisplay() {
    panel = configurePanel();
    addWidgets();
  }

  private DebugDisplayPanel configurePanel() {
    DebugDisplayPanel panel = new DebugDisplayPanel();
    panel.setBackground(BACKGROUND_COLOR);
    panel.setPosition(new GPoint(0, 0));
    return panel;
  }

  private void addWidgets() {
    addFpsWidget();
    addRenderTimeWidget();
  }

  private void addFpsWidget() {
    JLabel fpsWidget = new JLabel("FPS:\t");
    fpsWidget.setForeground(FOREGROUND_COLOR);
    fpsWidget.setFont(DEBUG_FONT);
    panel.add(fpsWidget);
  }

  private void addRenderTimeWidget() {
    JLabel renderTimeWidget = new JLabel("Render Time:\t");
    renderTimeWidget.setForeground(FOREGROUND_COLOR);
    renderTimeWidget.setFont(DEBUG_FONT);
    panel.add(renderTimeWidget);
  }

  @Override
  public GSprite getSprite() {
    return panel;
  }

  @Override
  public void update(GPanel panel) {}

  @Override
  public int getRenderLayer() {
    return 0;
  }

  private static class DebugDisplayPanel extends JPanel implements GSprite {
    private static final Dimension MAX_SIZE = new Dimension(200, 999999999);
    private static final Dimension MIN_SIZE = new Dimension(200, 0);
    private static final Dimension SIZE = new Dimension(200, 200);

    public DebugDisplayPanel() {
      super();
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setMaximumSize(MAX_SIZE);
      setMinimumSize(MIN_SIZE);
    }

    @Override
    public void draw(Graphics graphics) {}

    @Override
    public GPoint getPosition() {
      return null;
    }

    @Override
    public void setPosition(@NotNull GPoint position) {
      super.setBounds(new Rectangle(GPoint.metersToPixels(position), SIZE));
    }
  }
}
