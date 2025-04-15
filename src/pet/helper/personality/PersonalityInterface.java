package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

/**
 * The {@code PersonalityInterface} defines the contract for all pet personality types.
 * <p>
 * Each personality modifies the pet's behavior in two main ways:
 * <ul>
 *   <li>{@link #modifyStep(PetInterface, MoodEnum)} — how the pet's needs change over time</li>
 *   <li>{@link #applyPersonalityInteract(PetInterface, MoodEnum, Action)} —
 *   how the pet responds to user actions</li>
 * </ul>
 * <p>
 * Implementations of this interface define specific personality traits that influence
 * how the pet reacts
 * to mood and interactions in the game.
 */
public interface PersonalityInterface {

  /**
   * Applies personality-specific effects to the pet's needs at each time step,
   * considering the pet's current mood.
   *
   * @param pet  the pet instance whose needs will be modified
   * @param mood the current mood of the pet (e.g., HAPPY or SAD)
   */
  void modifyStep(PetInterface pet, MoodEnum mood);

  /**
   * Applies personality-specific effects when the pet interacts with the player.
   * The impact may vary depending on the action taken and the pet's mood.
   *
   * @param pet    the pet instance whose needs will be modified
   * @param mood   the current mood of the pet
   * @param action the action performed by the user (e.g., FEED, PLAY, CLEAN, SLEEP)
   */
  void applyPersonalityInteract(PetInterface pet, MoodEnum mood, Action action);
}
