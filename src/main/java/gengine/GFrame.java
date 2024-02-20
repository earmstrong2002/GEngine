package gengine;

import javax.swing.*;

/** This class provides simpler ways to interact with a JFrame. */
public class GFrame extends JFrame {
  /** The primary panel. */
  private GPanel gPanel;

  /**
   * Instantiates a gengine.GFrame.
   *
   * @param gPanel The primary panel.
   */
  public GFrame(GPanel gPanel) {
    super();
    this.gPanel = gPanel;
    //    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().add(gPanel);
  }

  /**
   * @param b if {@code true}, makes the {@code Window} visible, otherwise hides the {@code Window}.
   *     If the {@code Window} and/or its owner are not yet displayable, both are made displayable.
   *     The {@code Window} will be validated prior to being made visible. If the {@code Window} is
   *     already visible, this will bring the {@code Window} to the front.
   *     <p>If {@code false}, hides this {@code Window}, its subcomponents, and all of its owned
   *     children. The {@code Window} and its subcomponents can be made visible again with a call to
   *     {@code #setVisible(true)}.
   */
  @Override
  public void setVisible(boolean b) {
    if (b && getTitle().isEmpty()) {
      throw new IllegalStateException("Cannot launch a gengine.GFrame with no title.");
    }
    super.setVisible(b);
  }
}
