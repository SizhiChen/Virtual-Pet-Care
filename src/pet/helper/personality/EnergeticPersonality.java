package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

/**
 * The {@code EnergeticPersonality} class defines how a pet with the "Energetic" personality
 * behaves during automatic time-based updates and in response to user interactions.
 * <p>
 * Energetic pets are highly active, especially benefiting from play. They also consume
 * sleep energy faster than other types due to their constant activity.
 * <p>
 * Implements the {@link PersonalityInterface}.
 */
public class EnergeticPersonality implements PersonalityInterface {

  /**
   * Modifies the pet's needs at each game step depending on its mood.
   * <ul>
   *   <li>If {@code HAPPY}, most needs reduce by 2 and sleep by 3.</li>
   *   <li>If {@code SAD}, most needs reduce by 3 and sleep by 4.</li>
   * </ul>
   *
   * @param pet  the pet model instance
   * @param mood the current mood of the pet
   */
  @Override
  public void modifyStep(PetInterface pet, MoodEnum mood) {
    int step = (mood == MoodEnum.HAPPY) ? -2 : -3;
    pet.adjustNeeds(
        step,          // Hunger
        step,          // Hygiene
        step,          // Social
        step - 1       // Sleep (reduced slightly more due to high energy)
    );
  }

  /**
   * Applies the effect of a specific user action based on the pet's energetic personality.
   * Energetic pets gain large boosts from playing and respond slightly to other actions:
   * <ul>
   *   <li>{@code FEED} increases hunger by 40, decreases other needs slightly.</li>
   *   <li>{@code PLAY} greatly boosts social (+60).</li>
   *   <li>{@code CLEAN} improves hygiene moderately.</li>
   *   <li>{@code SLEEP} restores sleep moderately.</li>
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
      case PLAY -> pet.adjustNeeds(-2, -2, 60, -2);
      case CLEAN -> pet.adjustNeeds(-2, 40, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 40);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }
  }
}
