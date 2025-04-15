package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

/**
 * The {@code LazyPersonality} class defines how a pet with the "Lazy" personality
 * behaves during time-based updates and in response to user interactions.
 * <p>
 * Lazy pets have slower social decay but faster sleep decay. They also benefit most from sleeping.
 * Implements the {@link PersonalityInterface}.
 */
public class LazyPersonality implements PersonalityInterface {

  /**
   * Modifies the pet's needs at each time step depending on its mood.
   * Lazy pets lose social points more slowly but become sleepy faster than others.
   * <ul>
   * If {@code HAPPY}, hunger, hygiene, and sleep decrease by 2 (sleep by 3), and social by 1.
   * If {@code SAD}, hunger, hygiene, and sleep decrease by 3 (sleep by 4), and social by 1.
   * </ul>
   *
   * @param pet  the pet model instance
   * @param mood the current mood of the pet
   */
  @Override
  public void modifyStep(PetInterface pet, MoodEnum mood) {
    int step = (mood == MoodEnum.HAPPY) ? -2 : -3;
    pet.adjustNeeds(
        step,        // Hunger
        step,        // Hygiene
        -1,          // Social decays slowly
        step - 1     // Sleep decays faster
    );
  }

  /**
   * Applies the effect of user interactions for a lazy pet.
   * Lazy pets benefit the most from sleep and the least from play.
   * <ul>
   *   <li>{@code FEED} restores 40 hunger points.</li>
   *   <li>{@code PLAY} only slightly increases social (+20).</li>
   *   <li>{@code CLEAN} improves hygiene moderately.</li>
   *   <li>{@code SLEEP} greatly restores sleep (+80).</li>
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
      case PLAY -> pet.adjustNeeds(-2, -2, 20, -2);
      case CLEAN -> pet.adjustNeeds(-2, 40, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 80);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }
  }
}
