package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

/**
 * The {@code NeedyPersonality} class defines how a pet with the "Needy" personality
 * behaves during periodic updates and in response to user interactions.
 * <p>
 * Needy pets experience faster social decay and require more attention and interaction
 * to stay happy.
 * Implements the {@link PersonalityInterface}.
 */
public class NeedyPersonality implements PersonalityInterface {

  /**
   * Modifies the pet's needs at each time step depending on its mood.
   * Needy pets lose social points at twice the normal rate.
   * <ul>
   *   <li>If {@code HAPPY}, social decreases by 4, others by 2.</li>
   *   <li>If {@code SAD}, social decreases by 6, others by 3.</li>
   * </ul>
   *
   * @param pet  the pet model instance
   * @param mood the current mood of the pet
   */
  @Override
  public void modifyStep(PetInterface pet, MoodEnum mood) {
    int step = (mood == MoodEnum.HAPPY) ? -2 : -3;
    pet.adjustNeeds(
        step,         // Hunger
        step,         // Hygiene
        step * 2,     // Social decays faster
        step          // Sleep
    );
  }

  /**
   * Applies the effect of user interactions for a needy pet.
   * Needy pets respond especially well to social interactions like play.
   * <ul>
   *   <li>{@code FEED} restores 40 hunger points.</li>
   *   <li>{@code PLAY} greatly boosts social (+70).</li>
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
      case PLAY -> pet.adjustNeeds(-2, -2, 70, -2);
      case CLEAN -> pet.adjustNeeds(-2, 40, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 40);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }
  }
}
