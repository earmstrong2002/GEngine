package gengine.logic;

import java.util.List;

/**
 * Classes that implement the {@code GSubject} interface have all the necessary methods to maintain
 * a subject in a subject-observer pattern.
 */
public interface GSubject {
  /**
   * Attaches an observer to this subject such that it will receive a ping when {@link #update} is
   * called.
   *
   * @param observer The observer to attach to this subject.
   */
  void attach(GObserver observer);

  /**
   * Detaches an observer from this subject such that it will no longer receive pings when {@link
   * #update} is called.
   *
   * @param observer The observer to detach to this subject.
   * @return Returns {@code true} if the given observer was found and detached from this subject
   *     successfully. Returns {@code false} if the given observer could not be found.
   */
  boolean detach(GObserver observer);

  /**
   * Retrieves a list of the observers that are currently attached to this subject.
   *
   * @return A list of the observers that are attached to this subject at the time this method is
   *     called.
   */
  List<GObserver> getObservers();

  /**
   * Pings all observers currently attached to this subject.
   *
   * <p>Typically, in the context of GEngine, this method will be called each frame. However, this
   * interface is flexible enough to be used in a wider variety of ways.
   */
  void update();
}
