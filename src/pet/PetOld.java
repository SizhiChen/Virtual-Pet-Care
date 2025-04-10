package pet;

import pet.helper.Action;
import pet.helper.mood.HappyBehavior;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodBehavior;
import pet.helper.mood.MoodEnum;
import pet.helper.mood.SadBehavior;
import pet.helper.personality.Personality;

/**
 * The {@code Pet} class represents the internal model of a virtual pet.
 * It tracks the pet's core needs (hunger, hygiene, social, sleep),
 * mood, and alive status. The pet's state changes over time and through interactions.
 */
public class PetOld implements PetInterface {
  private static final int INITIAL_STATE = 50;

  /**
   * Represents the pet's hunger level (0-100).
   */
  private int hunger;

  /**
   * Represents the pet's hygiene level (0-100).
   */
  private int hygiene;

  /**
   * Represents the pet's social level (0-100).
   */
  private int social;

  /**
   * Represents the pet's sleep level (0-100).
   */
  private int sleep;

  /**
   * Indicates whether the pet is alive.
   */
  private boolean alive;

  /**
   * The current mood of the pet (HAPPY or SAD).
   */
  private MoodEnum mood;

  /**
   * Behavior strategy based on the pet's mood.
   */
  private MoodBehavior moodBehavior;

  /**
   * Default constructor that leaves the pet uninitialized.
   * Call startView() to initialize the pet.
   */
  public PetOld() {
  }

  /**
   * Initializes the pet's needs and sets its initial mood.
   * This must be called before using the pet.
   */
  @Override
  public void startGame() {
    this.hunger = INITIAL_STATE;
    this.hygiene = INITIAL_STATE;
    this.social = INITIAL_STATE;
    this.sleep = INITIAL_STATE;
    this.alive = true;
    setMood(MoodEnum.HAPPY);
  }

  /**
   * Advances the pet’s internal state by one unit of time.
   * This triggers mood-based behavior and can cause the pet’s needs to degrade.
   * Also checks for potential mood and health updates.
   */
  @Override
  public void step() {
    if (!alive) {
      return;
    }
    moodBehavior.applyMoodEffect(this);
    checkHealth();
    updateMood();
  }

  /**
   * Applies an interaction to the pet, such as feeding, playing, cleaning, or sleeping.
   * Each interaction adjusts specific needs and may have side effects on others.
   *
   * @param action the {@link Action} enum representing the type of interaction
   */
  @Override
  public void interactWith(Action action) {
    if (!alive) {
      return;
    }

    switch (action) {
      case FEED:
        hunger = clamp(hunger + 40);
        hygiene = clamp(hygiene - 4);
        social = clamp(social - 4);
        sleep = clamp(sleep - 4);
        break;
      case PLAY:
        social = clamp(social + 30);
        hunger = clamp(hunger - 4);
        hygiene = clamp(hygiene - 4);
        sleep = clamp(sleep - 4);
        break;
      case CLEAN:
        hygiene = clamp(hygiene + 80);
        hunger = clamp(hunger - 4);
        social = clamp(social - 4);
        sleep = clamp(sleep - 4);
        break;
      case SLEEP:
        sleep = clamp(sleep + 40);
        hunger = clamp(hunger - 4);
        social = clamp(social - 4);
        hygiene = clamp(hygiene - 4);
        break;
      default:
        break;
    }

    checkHealth();
    updateMood();
  }

  /**
   * Returns an immutable {@link HealthStatus} object representing
   * the current health levels of the pet.
   *
   * @return the pet's current health status
   */
  @Override
  public HealthStatus getHealth() {
    return new HealthStatus(hunger, hygiene, social, sleep);
  }

  /**
   * Returns the current mood of the pet.
   *
   * @return the current {@link MoodEnum} of the pet
   */
  @Override
  public MoodEnum getMood() {
    return mood;
  }

  @Override
  public Personality getPersonality() {
    return null;
  }

  /**
   * Sets the mood of the pet and updates the corresponding behavior strategy.
   *
   * @param mood the new mood to set
   */
  @Override
  public void setMood(MoodEnum mood) {
    this.mood = mood;
    switch (mood) {
      case HAPPY:
        moodBehavior = new HappyBehavior();
        break;
      case SAD:
        moodBehavior = new SadBehavior();
        break;
      default:
        break;
    }
  }

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public boolean needShower() {
    return hygiene <= 20;
  }

  @Override
  public boolean needFeed() {
    return hunger <= 20;
  }

  @Override
  public boolean needPlay() {
    return social <= 20;
  }

  @Override
  public boolean needSleep() {
    return sleep <= 20;
  }

  /**
   * Adjusts the pet’s internal need values directly.
   * Used by mood behaviors to simulate degradation or improvement of needs.
   *
   * @param hungerDelta  change to hunger level
   * @param hygieneDelta change to hygiene level
   * @param socialDelta  change to social level
   * @param sleepDelta   change to sleep level
   */
  public void adjustNeeds(int hungerDelta, int hygieneDelta, int socialDelta, int sleepDelta) {
    hunger = clamp(hunger + hungerDelta);
    hygiene = clamp(hygiene + hygieneDelta);
    social = clamp(social + socialDelta);
    sleep = clamp(sleep + sleepDelta);
  }

  /**
   * Updates the pet's mood based on current needs.
   * If any need is below 20, the pet becomes SAD. Otherwise, it is HAPPY.
   */
  private void updateMood() {
    if (hunger <= 20 || hygiene <= 20 || social <= 20 || sleep <= 20) {
      setMood(MoodEnum.SAD);
    } else {
      setMood(MoodEnum.HAPPY);
    }
  }

  /**
   * Checks whether the pet is still alive.
   * If any need reaches 0, the pet is considered dead.
   */
  private void checkHealth() {
    if (hunger == 0 || hygiene == 0 || social == 0 || sleep == 0) {
      alive = false;
      System.out.println("Your pet has died due to neglect.");
    }
  }

  /**
   * Clamps a value between 0 and 100 to keep needs within valid bounds.
   *
   * @param value the value to clamp
   * @return the clamped value
   */
  private int clamp(int value) {
    return Math.max(0, Math.min(100, value));
  }
}
