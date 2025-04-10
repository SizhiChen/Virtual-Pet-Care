package pet.helper.personality;

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

  Personality(PersonalityInterface personality, String name) {
    this.personality = personality;
    this.name = name;
  }

  public PersonalityInterface getPersonality() {
    return personality;
  }

  public String getName() {
    return name;
  }
}
