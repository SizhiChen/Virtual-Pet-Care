package pet.helper.mood;

import pet.PetInterface;

/**
 * The {@code MoodBehavior} interface defines a strategy for how a pet's needs
 * are affected based on its current mood.
 * <p>
 * This interface is part of the Strategy design pattern and is implemented by
 * mood-specific behavior classes such as {@link HappyBehavior} and {@link SadBehavior}.
 * These classes apply different logic to degrade the pet’s internal needs during each time step.
 */
public interface MoodBehavior {

  /**
   * Applies the mood-specific effect to the given pet.
   * This is typically called during the {@code step()} method to simulate
   * how the pet's needs degrade over time in a certain mood.
   *
   * @param pet the {@link PetInterface} instance to apply the mood effect to
   */
  void applyMoodEffect(PetInterface pet);
}
