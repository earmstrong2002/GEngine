package gengine.logic;

/**
 * Classes that implement the GObserver interface have all the methods required to interact with the
 * {@link GSubject} interface as the observer in a subject-observer relationship.
 */
public interface GObserver {

  /**
   * Instructs this observer to retrieve state data from its {@link GSubject} and perform whatever
   * tasks for which it's rsponsible upon update. This method will most often be called by a {@link
   * GSubject} to which this observer is attached.
   */
  void update();
}
