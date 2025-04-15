package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

/**
 * The {@code GluttonPersonality} class defines how a pet with the "Glutton" personality
 * behaves during time-based updates and in response to user interactions.
 * <p>
 * Glutton pets have an increased hunger decay rate and gain the most from feeding interactions.
 * This class implements the {@link PersonalityInterface}.
 */
public class GluttonPersonality implements PersonalityInterface {

  /**
   * Modifies the pet's needs at each time step depending on mood.
   * For Glutton pets, hunger decreases at twice the normal rate compared to other needs.
   * <ul>
   *   <li>If {@code HAPPY}, hunger decreases by 4, others by 2.</li>
   *   <li>If {@code SAD}, hunger decreases by 6, others by 3.</li>
   * </ul>
   *
   * @param pet  the pet model instance
   * @param mood the current mood of the pet
   */
  @Override
  public void modifyStep(PetInterface pet, MoodEnum mood) {
    int step = (mood == MoodEnum.HAPPY) ? -2 : -3;
    pet.adjustNeeds(
        step * 2,  // Hunger decays faster
        step,      // Hygiene
        step,      // Social
        step       // Sleep
    );
  }

  /**
   * Applies the effect of user interactions for a gluttonous pet.
   * Feeding has a strong positive effect; other actions behave normally.
   * <ul>
   *   <li>{@code FEED} restores 60 hunger points.</li>
   *   <li>{@code PLAY} boosts social moderately.</li>
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
      case FEED -> pet.adjustNeeds(60, -2, -2, -2);
      case PLAY -> pet.adjustNeeds(-2, -2, 40, -2);
      case CLEAN -> pet.adjustNeeds(-2, 40, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 40);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }
  }
}
