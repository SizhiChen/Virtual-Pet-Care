package pet;

import pet.helper.Action;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodEnum;
import pet.helper.personality.Personality;

/**
 * The {@code PetInterface} defines the contract for interacting with a virtual pet model.
 * <p>
 * Implementing classes must support time progression, interaction with actions,
 * and provide access to the pet's mood and health status.
 * <p>
 * This interface is used by external components (e.g., controllers or views)
 * to communicate with the pet model in a consistent and encapsulated way.
 */
public interface PetInterface {

  /**
   * Initializes the pet's needs and sets its initial mood.
   * This must be called before using the pet.
   */
  void startGame();

  /**
   * Advances the pet’s internal state by one unit of time.
   * This typically causes the pet's needs to degrade based on its current mood.
   * Can also trigger mood changes or health consequences over time.
   */
  void step();

  /**
   * Interacts with the pet using the specified action (e.g., feeding, playing).
   * Each action affects the pet’s internal needs and potentially its mood.
   *
   * @param action the {@link Action} to apply to the pet
   */
  void interactWith(Action action);

  /**
   * Retrieves the pet’s current health status in an immutable form.
   *
   * @return a {@link HealthStatus} object representing the pet’s needs
   */
  HealthStatus getHealth();

  /**
   * Gets the current mood of the pet.
   *
   * @return the current {@link MoodEnum} of the pet
   */
  MoodEnum getMood();

  Personality getPersonality();

  /**
   * Sets the mood of the pet manually. This may also change how it behaves over time.
   *
   * @param mood the new {@link MoodEnum} to assign
   */
  void setMood(MoodEnum mood);

  boolean isAlive();

  boolean needShower();

  boolean needFeed();

  boolean needPlay();

  boolean needSleep();

  void adjustNeeds(int hungerDelta, int hygieneDelta, int socialDelta, int sleepDelta);
}
