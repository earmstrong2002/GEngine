import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

public class GPanel extends JPanel {

  List<GObject> children;

  /**
   * Invoked by Swing to draw components. Applications should not invoke <code>paint</code>
   * directly, but should instead use the <code>repaint</code> method to schedule the component for
   * redrawing.
   *
   * <p>This method actually delegates the work of painting to three protected methods: <code>
   * paintComponent</code>, <code>paintBorder</code>, and <code>paintChildren</code>. They're called
   * in the order listed to ensure that children appear on top of component itself. Generally
   * speaking, the component and its children should not paint in the insets area allocated to the
   * border. Subclasses can just override this method, as always. A subclass that just wants to
   * specialize the UI (look and feel) delegate's <code>paint</code> method should just override
   * <code>paintComponent</code>.
   *
   * @param graphics the <code>Graphics</code> context in which to paint
   * @see #paintComponent
   * @see #paintBorder
   * @see #paintChildren
   * @see #getComponentGraphics
   * @see #repaint
   */
  public GPanel() {
    super();
    children = new LinkedList<>();
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    for (GObject object : children) {
      object.getSprite().draw(graphics);
    }
  }

  public void addGObject(@NotNull GObject object) {
    children.add(object);
  }

  public void update() {
    for (GObject child : children) {
      child.update(this);
    }
    repaint();
  }
}
