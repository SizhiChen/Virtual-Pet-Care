package pet.helper;

/**
 * The {@code HealthStatus} class represents a snapshot of a pet's internal state.
 * <p>
 * It encapsulates four key health attributes: hunger, hygiene, social, and sleep.
 * This class is immutable, meaning the values cannot be changed once created.
 * It is used by external components to safely read a pet's health without
 * directly modifying its internal state.
 */
public class HealthStatus {

  /**
   * Current hunger level of the pet (0–100).
   */
  private final int hunger;

  /**
   * Current hygiene level of the pet (0–100).
   */
  private final int hygiene;

  /**
   * Current social (interaction) level of the pet (0–100).
   */
  private final int social;

  /**
   * Current sleep (rest) level of the pet (0–100).
   */
  private final int sleep;

  /**
   * Constructs a new {@code HealthStatus} object with the specified values.
   *
   * @param hunger  the pet's current hunger level
   * @param hygiene the pet's current hygiene level
   * @param social  the pet's current social level
   * @param sleep   the pet's current sleep level
   */
  public HealthStatus(int hunger, int hygiene, int social, int sleep) {
    this.hunger = hunger;
    this.hygiene = hygiene;
    this.social = social;
    this.sleep = sleep;
  }

  /**
   * Returns the current hunger level.
   *
   * @return hunger level (0–100)
   */
  public int getHunger() {
    return hunger;
  }

  /**
   * Returns the current hygiene level.
   *
   * @return hygiene level (0–100)
   */
  public int getHygiene() {
    return hygiene;
  }

  /**
   * Returns the current social level.
   *
   * @return social level (0–100)
   */
  public int getSocial() {
    return social;
  }

  /**
   * Returns the current sleep level.
   *
   * @return sleep level (0–100)
   */
  public int getSleep() {
    return sleep;
  }

  /**
   * Returns a string representation of the health status,
   * including all four tracked values.
   *
   * @return string representation of the health status
   */
  @Override
  public String toString() {
    return "HealthStatus{"
        + "hunger=" + hunger
        + ", hygiene=" + hygiene
        + ", social=" + social
        + ", sleep=" + sleep
        + '}';
  }
}
