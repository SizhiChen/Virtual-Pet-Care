package pet.helper.personality;

import java.util.Random;
import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

/**
 * The {@code SmartPersonality} class defines how a pet with the "Smart" personality
 * behaves during time-based updates and in response to user interactions.
 * <p>
 * Smart pets manage their needs more efficiently and occasionally resist decay in needs.
 * This personality introduces a small random chance (20%) that each need will decay
 * slightly less during each time step.
 * <p>
 * Implements the {@link PersonalityInterface}.
 */
public class SmartPersonality implements PersonalityInterface {

  private final Random random = new Random();

  /**
   * Modifies the pet's needs at each time step depending on its current mood.
   * <ul>
   *   <li>If {@code HAPPY}, needs typically decrease by 1.</li>
   *   <li>If {@code SAD}, needs typically decrease by 2.</li>
   * </ul>
   * However, each need (hunger, hygiene, social, sleep) has a 20% chance to
   * resist decay and lose 1 less point.
   *
   * @param pet  the pet model instance
   * @param mood the current mood of the pet
   */
  @Override
  public void modifyStep(PetInterface pet, MoodEnum mood) {
    int step = (mood == MoodEnum.HAPPY) ? -1 : -2;

    pet.adjustNeeds(
        step + ((random.nextInt(100) < 20) ? 1 : 0), // Hunger
        step + ((random.nextInt(100) < 20) ? 1 : 0), // Hygiene
        step + ((random.nextInt(100) < 20) ? 1 : 0), // Social
        step + ((random.nextInt(100) < 20) ? 1 : 0)  // Sleep
    );
  }

  /**
   * Applies the effect of user interactions for a smart pet.
   * Smart pets respond predictably and moderately well to all standard interactions.
   * <ul>
   *   <li>{@code FEED} restores 40 hunger points.</li>
   *   <li>{@code PLAY} increases social by 40.</li>
   *   <li>{@code CLEAN} restores 40 hygiene.</li>
   *   <li>{@code SLEEP} restores 40 sleep.</li>
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
      case CLEAN -> pet.adjustNeeds(-2, 40, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 40);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }
  }
}
