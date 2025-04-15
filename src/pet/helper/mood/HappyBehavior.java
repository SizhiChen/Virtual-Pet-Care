package pet.helper.mood;

import pet.Pet;
import pet.PetInterface;
import pet.PetOld;

/**
 * The {@code HappyBehavior} class defines how a pet's needs change over time
 * when it is in a HAPPY mood.
 * <p>
 * In this mood, the pet's needs degrade more slowly compared to when it is SAD.
 * This class implements the {@link MoodBehavior} strategy interface and is
 * used by the {@link Pet} class to apply mood-specific effects during each time step.
 */
public class HappyBehavior implements MoodBehavior {

  private static final int HAPPY_NEED_DEGRADATION = -2;

  /**
   * Applies the HAPPY mood behavior to the given pet.
   * In this mood, all needs (hunger, hygiene, social, sleep)
   * degrade slightly, simulating a content and stable state.
   *
   * @param pet the {@link PetInterface} instance to apply the mood effect to
   */
  @Override
  public void applyMoodEffect(PetOld pet) {
    pet.adjustNeeds(
        HAPPY_NEED_DEGRADATION,
        HAPPY_NEED_DEGRADATION,
        HAPPY_NEED_DEGRADATION,
        HAPPY_NEED_DEGRADATION
    );
  }
}
