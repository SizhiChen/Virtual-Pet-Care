package pet;

import pet.helper.Action;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodEnum;
import pet.helper.personality.Personality;

/**
 * The {@code PetInterface} defines the contract for the virtual pet model.
 * <p>
 * Implementing classes must support:
 * <ul>
 *   <li>Initialization of game state</li>
 *   <li>Time progression and mood updates</li>
 *   <li>User interactions through actions</li>
 *   <li>Health and mood queries</li>
 *   <li>Need-based checks (e.g., hunger, sleepiness)</li>
 * </ul>
 * <p>
 * This interface allows external components like controllers or views
 * to interact with the pet model in a consistent and encapsulated way.
 */
public interface PetInterface {

  /**
   * Initializes the pet's state, including all needs, mood, and status flags.
   * Must be called before using other functionality.
   */
  void startGame();

  /**
   * Advances the pet’s internal state by one unit of time.
   * This typically degrades the pet's needs and may alter mood or trigger death.
   */
  void step();

  /**
   * Applies an interaction (such as feeding, playing, cleaning, or sleeping) to the pet.
   * The pet’s needs are adjusted based on the interaction and personality.
   *
   * @param action the {@link Action} to apply
   */
  void interactWith(Action action);

  /**
   * Retrieves the current health status of the pet as a read-only object.
   *
   * @return a {@link HealthStatus} snapshot representing all core needs
   */
  HealthStatus getHealth();

  /**
   * Gets the pet's current mood (e.g., HAPPY or SAD).
   *
   * @return the pet’s {@link MoodEnum}
   */
  MoodEnum getMood();

  /**
   * Returns the personality assigned to this pet, which
   * affects how it responds to time and actions.
   *
   * @return the current {@link Personality}
   */
  Personality getPersonality();

  /**
   * Manually sets the pet’s mood, which can influence its behavior and responses.
   *
   * @param mood the {@link MoodEnum} to set
   */
  void setMood(MoodEnum mood);

  /**
   * Sets the personality of the pet manually.
   * This can be used for testing or deterministic personality assignment.
   *
   * @param personality the {@link Personality} to assign
   */
  void setPersonality(Personality personality);

  /**
   * Returns whether the pet is alive. If any need reaches zero, the pet is considered dead.
   *
   * @return {@code true} if the pet is alive; {@code false} if not
   */
  boolean isAlive();

  /**
   * Checks whether the pet currently needs a shower (hygiene at or below 20).
   *
   * @return {@code true} if hygiene is low
   */
  boolean needShower();

  /**
   * Checks whether the pet currently needs to eat (hunger at or below 20).
   *
   * @return {@code true} if hunger is low
   */
  boolean needFeed();

  /**
   * Checks whether the pet currently needs to play (social at or below 20).
   *
   * @return {@code true} if social is low
   */
  boolean needPlay();

  /**
   * Checks whether the pet currently needs sleep (sleep at or below 20).
   *
   * @return {@code true} if sleep is low
   */
  boolean needSleep();

  /**
   * Adjusts the pet’s needs by the specified amounts.
   * This is typically used internally by personalities and behaviors.
   * Values are clamped between 0 and 100.
   *
   * @param hungerDelta  change in hunger value
   * @param hygieneDelta change in hygiene value
   * @param socialDelta  change in social value
   * @param sleepDelta   change in sleep value
   */
  void adjustNeeds(int hungerDelta, int hygieneDelta, int socialDelta, int sleepDelta);
}
