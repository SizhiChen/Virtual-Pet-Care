package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

/**
 * The {@code AloofPersonality} class defines how a pet with the "Aloof" personality
 * behaves during the game's step updates and when interacting with the user.
 * <p>
 * This personality tends to be emotionally distant, with reduced social gain from interactions.
 * It implements the {@link PersonalityInterface} and modifies the pet's needs based on
 * mood and actions.
 */
public class AloofPersonality implements PersonalityInterface {

  /**
   * Modifies the pet's needs on each time step depending on its current mood.
   * <ul>
   *   <li>If the mood is {@code HAPPY}, the needs decrease by 2 units.</li>
   *   <li>If the mood is {@code SAD}, the needs decrease by 3 units.</li>
   * </ul>
   *
   * @param pet  the pet model instance
   * @param mood the current mood of the pet
   */
  @Override
  public void modifyStep(PetInterface pet, MoodEnum mood) {
    int step = (mood == MoodEnum.HAPPY) ? -2 : -3;
    pet.adjustNeeds(
        step,
        step,
        step,
        step);
  }

  /**
   * Applies the effect of a specific user action based on the pet's mood and personality.
   * Each action modifies the pet's needs in a personality-specific way:
   * <ul>
   *   <li>{@code FEED} increases hunger significantly but reduces other needs slightly.</li>
   *   <li>{@code PLAY} increases social moderately but affects others minimally.</li>
   *   <li>{@code CLEAN} improves hygiene substantially.</li>
   *   <li>{@code SLEEP} restores sleep significantly.</li>
   *   <li>Other actions have no effect.</li>
   * </ul>
   *
   * @param pet    the pet model instance
   * @param mood   the current mood of the pet
   * @param action the action performed by the user
   */
  @Override
  public void applyPersonalityInteract(PetInterface pet, MoodEnum mood, Action action) {
    switch (action) {
      case FEED -> pet.adjustNeeds(40, -2, -2, -2);
      case PLAY -> pet.adjustNeeds(-2, -2, 30, -2);
      case CLEAN -> pet.adjustNeeds(-2, 40, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 40);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }
  }
}
