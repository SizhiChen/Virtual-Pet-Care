package pet.helper.mood;

import pet.Pet;
import pet.PetInterface;

/**
 * The {@code SadBehavior} class defines how a pet's needs change over time
 * when it is in a SAD mood.
 * <p>
 * In this mood, the pet's needs degrade more rapidly than when it is HAPPY.
 * This class implements the {@link MoodBehavior} strategy interface and is
 * used by the {@link Pet} class to apply mood-specific effects during each time step.
 */
public class SadBehavior implements MoodBehavior {

  private static final int SAD_NEED_DEGRADATION = -4;

  /**
   * Applies the SAD mood behavior to the given pet.
   * In this mood, all needs (hunger, hygiene, social, sleep)
   * degrade faster, simulating stress, loneliness, or neglect.
   *
   * @param pet the {@link PetInterface} instance to apply the mood effect to
   */
  @Override
  public void applyMoodEffect(PetInterface pet) {
    pet.adjustNeeds(
        SAD_NEED_DEGRADATION,
        SAD_NEED_DEGRADATION,
        SAD_NEED_DEGRADATION,
        SAD_NEED_DEGRADATION
    );
  }
}