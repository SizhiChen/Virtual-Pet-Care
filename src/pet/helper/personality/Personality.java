package pet.helper.personality;

/**
 * The {@code Personality} enum defines all possible pet personalities in the game,
 * each associated with a specific behavior implementation and a string identifier.
 * <p>
 * Each personality maps to a class that implements {@link PersonalityInterface},
 * which controls how the pet behaves during time steps and interactions.
 * <p>
 * Personalities include:
 * <ul>
 *   <li>{@code GLUTTON} – Eats more and gets hungry faster</li>
 *   <li>{@code LAZY} – Sleeps more and prefers resting</li>
 *   <li>{@code ENERGETIC} – Loves to play and burns energy quickly</li>
 *   <li>{@code MYSOPHOBIA} – Obsessed with cleanliness; hygiene decays quickly</li>
 *   <li>{@code NEEDY} – Craves attention and social interaction</li>
 *   <li>{@code ALOOF} – Emotionally distant; reacts less to interactions</li>
 *   <li>{@code SMART} – Unique logic defined in {@code SmartPersonality}</li>
 * </ul>
 */
public enum Personality {
  Glutton(new GluttonPersonality(), "GLUTTON"),
  Lazy(new LazyPersonality(), "LAZY"),
  Energetic(new EnergeticPersonality(), "ENERGETIC"),
  Mysophobia(new MysophobiaPersonality(), "MYSOPHOBIA"),
  Needy(new NeedyPersonality(), "NEEDY"),
  Aloof(new AloofPersonality(), "ALOOF"),
  Smart(new SmartPersonality(), "SMART");

  private final PersonalityInterface personality;
  private final String name;

  /**
   * Constructs a {@code Personality} enum instance with the corresponding
   * behavior implementation and name.
   *
   * @param personality the behavior logic associated with this personality
   * @param name        the string representation of the personality
   */
  Personality(PersonalityInterface personality, String name) {
    this.personality = personality;
    this.name = name;
  }

  /**
   * Returns the {@link PersonalityInterface} instance associated with this personality.
   *
   * @return the personality logic
   */
  public PersonalityInterface getPersonality() {
    return personality;
  }

  /**
   * Returns the display name of the personality.
   *
   * @return the personality name as a string
   */
  public String getName() {
    return name;
  }
}
