package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

/**
 * The {@code MysophobiaPersonality} class defines how a pet with the "Mysophobia" personality
 * (fear of dirt/germs) behaves during time-based updates and in response to user interactions.
 * <p>
 * Pets with this personality experience much faster hygiene decay but benefit greatly from
 * cleaning.
 * Implements the {@link PersonalityInterface}.
 */
public class MysophobiaPersonality implements PersonalityInterface {

  /**
   * Modifies the pet's needs at each time step depending on its mood.
   * Mysophobic pets lose hygiene twice as fast as other needs.
   * <ul>
   *   <li>If {@code HAPPY}, hygiene decreases by 4, others by 2.</li>
   *   <li>If {@code SAD}, hygiene decreases by 6, others by 3.</li>
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
        step * 2,     // Hygiene decays faster
        step,         // Social
        step          // Sleep
    );
  }

  /**
   * Applies the effect of user interactions for a mysophobic pet.
   * Cleaning actions have an especially large impact on hygiene.
   * <ul>
   *   <li>{@code FEED} restores 40 hunger points.</li>
   *   <li>{@code PLAY} boosts social moderately.</li>
   *   <li>{@code CLEAN} greatly restores hygiene (+80).</li>
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
      case PLAY -> pet.adjustNeeds(-2, -2, 40, -2);
      case CLEAN -> pet.adjustNeeds(-2, 80, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 40);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }
  }
}
