package pet;

import java.util.Random;
import pet.helper.Action;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodEnum;
import pet.helper.personality.Personality;

/**
 * The {@code Pet} class represents the internal model of a virtual pet.
 * <p>
 * It tracks four core needs: hunger, hygiene, social, and sleep (each between 0 and 100),
 * as well as the pet's current mood and alive status. The pet's state is updated over time
 * through periodic steps and interactions with the user.
 * <p>
 * This class uses a personality system that influences how needs degrade and respond to actions.
 */
public class Pet implements PetInterface {
  private static final int INITIAL_STATE = 50;

  private int hunger;
  private int hygiene;
  private int social;
  private int sleep;
  private boolean alive;
  private MoodEnum mood;
  private Personality personality;

  /**
   * Default constructor that leaves the pet uninitialized.
   * You must call {@link #startGame()} to begin the simulation.
   */
  public Pet() {}

  /**
   * Initializes the pet’s need values to a default midpoint, sets the pet as alive,
   * assigns a random personality, and starts with a HAPPY mood.
   */
  @Override
  public void startGame() {
    this.hunger = INITIAL_STATE;
    this.hygiene = INITIAL_STATE;
    this.social = INITIAL_STATE;
    this.sleep = INITIAL_STATE;
    this.alive = true;
    setMood(MoodEnum.HAPPY);
    setPersonality();
  }

  /**
   * Advances the simulation by one time step.
   * Triggers need decay according to the current mood and personality,
   * and checks for health and mood updates.
   */
  @Override
  public void step() {
    if (!alive) {
      return;
    }
    personality.getPersonality().modifyStep(this, mood);
    checkHealth();
    updateMood();
  }

  /**
   * Applies a user interaction (e.g., FEED, PLAY, CLEAN, SLEEP).
   * Delegates behavior to the personality logic.
   *
   * @param action the interaction to apply
   */
  @Override
  public void interactWith(Action action) {
    if (!alive) {
      return;
    }
    personality.getPersonality().applyPersonalityInteract(this, mood, action);
    checkHealth();
    updateMood();
  }

  /**
   * Returns the pet's current health status, including all four need values.
   *
   * @return an immutable {@link HealthStatus} snapshot
   */
  @Override
  public HealthStatus getHealth() {
    return new HealthStatus(hunger, hygiene, social, sleep);
  }

  /**
   * Returns the pet's current mood.
   *
   * @return {@link MoodEnum#HAPPY} or {@link MoodEnum#SAD}
   */
  @Override
  public MoodEnum getMood() {
    return mood;
  }

  /**
   * Returns the personality assigned to the pet.
   *
   * @return the pet’s {@link Personality}
   */
  @Override
  public Personality getPersonality() {
    return personality;
  }

  /**
   * Sets the pet's current mood.
   *
   * @param mood the new mood to assign
   */
  @Override
  public void setMood(MoodEnum mood) {
    this.mood = mood;
  }

  /**
   * Randomly assigns a personality to the pet from the list of available personalities.
   */
  private void setPersonality() {
    Random rand = new Random();
    Personality[] personalities = Personality.values();
    personality = personalities[rand.nextInt(personalities.length)];
  }

  /**
   * Sets the pet's personality manually.
   * <p>
   * <b>Note:</b> This method is intended for testing and debugging only.
   * In normal gameplay, the personality is assigned randomly when {@link #startGame()} is called.
   *
   * @param personality the {@link Personality} to assign to the pet
   */
  @Override
  public void setPersonality(Personality personality) {
    this.personality = personality;
  }


  /**
   * Returns whether the pet is currently alive.
   *
   * @return {@code true} if the pet is alive; {@code false} if dead
   */
  @Override
  public boolean isAlive() {
    return alive;
  }

  /**
   * Checks if the pet needs a shower (hygiene is at or below 20).
   *
   * @return {@code true} if hygiene is low
   */
  @Override
  public boolean needShower() {
    return hygiene <= 20;
  }

  /**
   * Checks if the pet needs to be fed (hunger is at or below 20).
   *
   * @return {@code true} if hunger is low
   */
  @Override
  public boolean needFeed() {
    return hunger <= 20;
  }

  /**
   * Checks if the pet needs to play (social is at or below 20).
   *
   * @return {@code true} if social is low
   */
  @Override
  public boolean needPlay() {
    return social <= 20;
  }

  /**
   * Checks if the pet needs to sleep (sleep is at or below 20).
   *
   * @return {@code true} if sleep is low
   */
  @Override
  public boolean needSleep() {
    return sleep <= 20;
  }

  /**
   * Adjusts the pet’s internal needs directly by specified deltas.
   * Values are clamped between 0 and 100.
   *
   * @param hungerDelta  amount to change hunger
   * @param hygieneDelta amount to change hygiene
   * @param socialDelta  amount to change social
   * @param sleepDelta   amount to change sleep
   */
  @Override
  public void adjustNeeds(int hungerDelta, int hygieneDelta, int socialDelta, int sleepDelta) {
    hunger = clamp(hunger + hungerDelta);
    hygiene = clamp(hygiene + hygieneDelta);
    social = clamp(social + socialDelta);
    sleep = clamp(sleep + sleepDelta);
  }

  /**
   * Updates the pet's mood based on its current needs.
   * If any need is below 20, mood becomes SAD; otherwise, it is HAPPY.
   */
  private void updateMood() {
    if (hunger <= 20 || hygiene <= 20 || social <= 20 || sleep <= 20) {
      setMood(MoodEnum.SAD);
    } else {
      setMood(MoodEnum.HAPPY);
    }
  }

  /**
   * Checks the pet's vital status. If any need reaches 0, the pet dies.
   */
  private void checkHealth() {
    if (hunger == 0 || hygiene == 0 || social == 0 || sleep == 0) {
      alive = false;
      System.out.println("Your pet has died due to neglect.");
    }
  }

  /**
   * Clamps a value to the range [0, 100].
   *
   * @param value the value to clamp
   * @return the clamped result
   */
  private int clamp(int value) {
    return Math.max(0, Math.min(100, value));
  }
}
