package pet.helper;

import pet.PetInterface;

/**
 * The {@code Action} enum represents the possible types of interactions
 * a user can perform with the virtual pet.
 * <p>
 * Each action has a specific effect on the pet’s internal needs,
 * and may also influence the pet's mood indirectly.
 * These actions are passed into the {@code interactWith()} method of the {@link PetInterface}.
 */
public enum Action {

  /**
   * Feed the pet to increase its hunger level.
   * May decrease hygiene, social, or sleep slightly.
   */
  FEED,

  /**
   * Play with the pet to improve its social level.
   * May consume hunger, hygiene, and sleep.
   */
  PLAY,

  /**
   * Clean the pet to improve its hygiene level.
   * May reduce hunger, social, or sleep a little.
   */
  CLEAN,

  /**
   * Put the pet to sleep to restore energy.
   * May significantly reduce hunger and social needs.
   */
  SLEEP
}
