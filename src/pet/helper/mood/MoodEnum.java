package pet.helper.mood;

/**
 * The {@code MoodEnum} represents the emotional state of the virtual pet.
 * <p>
 * The pet's mood influences how its needs degrade over time and how it reacts to interactions.
 * Implementations of mood-specific behavior are handled via the Strategy pattern
 * using corresponding {@code MoodBehavior} classes.
 */
public enum MoodEnum {

  /**
   * The pet is in a good mood. Needs degrade slowly and the pet reacts positively.
   */
  HAPPY,

  /**
   * The pet is in a bad mood. Needs to degrade faster and the pet may respond poorly to actions.
   */
  SAD
}
