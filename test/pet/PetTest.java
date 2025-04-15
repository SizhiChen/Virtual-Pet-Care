package pet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import pet.helper.Action;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodEnum;
import pet.helper.personality.Personality;

/**
 * Unit tests for the {@link Pet} class using the {@link PetInterface}.
 * <p>
 * This test class verifies the internal behavior of the pet model, including
 * need updates, mood transitions, and life state changes.
 */
public class PetTest {

  private PetInterface pet;

  /**
   * Sets up a new {@link Pet} instance before each test.
   * Initializes the game state using {@code startGame()}.
   */
  @Before
  public void setUp() {
    pet = new Pet();
    pet.startGame();
  }

  /**
   * Verifies that the pet is properly initialized after {@code startGame()}.
   * All needs should be set to 50, mood should be {@code HAPPY}, and the pet should be alive.
   */
  @Test
  public void testInitialization() {
    HealthStatus health = pet.getHealth();

    assertEquals("Hunger should be 50", 50, health.getHunger());
    assertEquals("Hygiene should be 50", 50, health.getHygiene());
    assertEquals("Social should be 50", 50, health.getSocial());
    assertEquals("Sleep should be 50", 50, health.getSleep());

    assertEquals("Mood should be HAPPY", MoodEnum.HAPPY, pet.getMood());
    assertTrue("Pet should be alive", pet.isAlive());
  }

  /**
   * Tests the {@code step()} method with a fixed personality.
   * Specifically tests the {@link Personality#Glutton} behavior,
   * where hunger decays twice as fast as other needs.
   */
  @Test
  public void testStepWithGluttonPersonality() {
    pet.setPersonality(Personality.Glutton);

    // === Step while HAPPY ===
    pet.step();
    HealthStatus health = pet.getHealth();

    assertEquals("Hunger should decrease by 4 (HAPPY)", 46, health.getHunger());
    assertEquals("Hygiene should decrease by 2 (HAPPY)", 48, health.getHygiene());
    assertEquals("Social should decrease by 2 (HAPPY)", 48, health.getSocial());
    assertEquals("Sleep should decrease by 2 (HAPPY)", 48, health.getSleep());

    // === Force SAD mood and step again ===
    pet.setMood(MoodEnum.SAD);
    pet.step();
    HealthStatus health2 = pet.getHealth();

    assertEquals("Hunger should decrease by 6 (SAD)", 40, health2.getHunger());
    assertEquals("Hygiene should decrease by 3 (SAD)", 45, health2.getHygiene());
    assertEquals("Social should decrease by 3 (SAD)", 45, health2.getSocial());
    assertEquals("Sleep should decrease by 3 (SAD)", 45, health2.getSleep());
  }

  /**
   * Tests {@link PetInterface#interactWith(Action)} for all four action types
   * under the {@link Personality#Glutton} behavior.
   */
  @Test
  public void testInteractWithAllActions() {
    pet.setPersonality(Personality.Glutton);

    // Set to known base (30)
    pet.adjustNeeds(-20, -20, -20, -20);
    // === FEED ===
    pet.interactWith(Action.FEED);
    HealthStatus health1 = pet.getHealth();
    assertEquals("After FEED: Hunger should be 90", 90, health1.getHunger());
    assertEquals("After FEED: Hygiene should be 28", 28, health1.getHygiene());
    assertEquals("After FEED: Social should be 28", 28, health1.getSocial());
    assertEquals("After FEED: Sleep should be 28", 28, health1.getSleep());

    // === PLAY ===
    pet.interactWith(Action.PLAY);
    HealthStatus health2 = pet.getHealth();
    assertEquals("After PLAY: Hunger should be 88", 88, health2.getHunger());
    assertEquals("After PLAY: Hygiene should be 26", 26, health2.getHygiene());
    assertEquals("After PLAY: Social should be 68", 68, health2.getSocial());
    assertEquals("After PLAY: Sleep should be 26", 26, health2.getSleep());

    // === CLEAN ===
    pet.interactWith(Action.CLEAN);
    HealthStatus health3 = pet.getHealth();
    assertEquals("After CLEAN: Hunger should be 86", 86, health3.getHunger());
    assertEquals("After CLEAN: Hygiene should be 66", 66, health3.getHygiene());
    assertEquals("After CLEAN: Social should be 66", 66, health3.getSocial());
    assertEquals("After CLEAN: Sleep should be 24", 24, health3.getSleep());

    // === SLEEP ===
    pet.interactWith(Action.SLEEP);
    HealthStatus health4 = pet.getHealth();
    assertEquals("After SLEEP: Hunger should be 84", 84, health4.getHunger());
    assertEquals("After SLEEP: Hygiene should be 64", 64, health4.getHygiene());
    assertEquals("After SLEEP: Social should be 64", 64, health4.getSocial());
    assertEquals("After SLEEP: Sleep should be 64", 64, health4.getSleep());
  }

  /**
   * Tests the {@code step()} method with a {@link Personality#Lazy} personality.
   * Verifies mood-dependent need degradation behavior.
   */
  @Test
  public void testStepWithLazyPersonality() {
    pet.setPersonality(Personality.Lazy);

    // === Step while HAPPY ===
    pet.step();
    HealthStatus health = pet.getHealth();

    assertEquals("Hunger should decrease by 2", 48, health.getHunger());
    assertEquals("Hygiene should decrease by 2", 48, health.getHygiene());
    assertEquals("Social should decrease by 1", 49, health.getSocial());
    assertEquals("Sleep should decrease by 3", 47, health.getSleep());

    // === Force SAD mood and step again ===
    pet.setMood(MoodEnum.SAD);
    pet.step();
    HealthStatus health2 = pet.getHealth();

    assertEquals("Hunger should decrease by 3", 45, health2.getHunger());
    assertEquals("Hygiene should decrease by 3", 45, health2.getHygiene());
    assertEquals("Social should decrease by 1", 48, health2.getSocial());
    assertEquals("Sleep should decrease by 4", 43, health2.getSleep());
  }

  /**
   * Tests {@link PetInterface#interactWith(Action)} for all four action types
   * under the {@link Personality#Lazy} behavior.
   */
  @Test
  public void testInteractWithAllActions_Lazy() {
    pet.setPersonality(Personality.Lazy);

    // Set to known base (30)
    pet.adjustNeeds(-20, -20, -20, -20);

    // === FEED ===
    pet.interactWith(Action.FEED);
    HealthStatus health1 = pet.getHealth();
    assertEquals("After FEED: Hunger should be 70", 70, health1.getHunger());
    assertEquals("After FEED: Hygiene should be 28", 28, health1.getHygiene());
    assertEquals("After FEED: Social should be 28", 28, health1.getSocial());
    assertEquals("After FEED: Sleep should be 28", 28, health1.getSleep());

    // === PLAY ===
    pet.interactWith(Action.PLAY);
    HealthStatus health2 = pet.getHealth();
    assertEquals("After PLAY: Hunger should be 68", 68, health2.getHunger());
    assertEquals("After PLAY: Hygiene should be 26", 26, health2.getHygiene());
    assertEquals("After PLAY: Social should be 48", 48, health2.getSocial());
    assertEquals("After PLAY: Sleep should be 26", 26, health2.getSleep());

    // === CLEAN ===
    pet.interactWith(Action.CLEAN);
    HealthStatus health3 = pet.getHealth();
    assertEquals("After CLEAN: Hunger should be 66", 66, health3.getHunger());
    assertEquals("After CLEAN: Hygiene should be 66", 66, health3.getHygiene());
    assertEquals("After CLEAN: Social should be 46", 46, health3.getSocial());
    assertEquals("After CLEAN: Sleep should be 24", 24, health3.getSleep());

    // === SLEEP ===
    pet.interactWith(Action.SLEEP);
    HealthStatus health4 = pet.getHealth();
    assertEquals("After SLEEP: Hunger should be 64", 64, health4.getHunger());
    assertEquals("After SLEEP: Hygiene should be 64", 64, health4.getHygiene());
    assertEquals("After SLEEP: Social should be 44", 44, health4.getSocial());
    assertEquals("After SLEEP: Sleep should be 100", 100, health4.getSleep());
  }

  /**
   * Tests the {@code step()} method with a {@link Personality#Energetic} personality.
   * Verifies need degradation, especially that sleep decreases faster.
   */
  @Test
  public void testStepWithEnergeticPersonality() {
    pet.setPersonality(Personality.Energetic);

    // === Step while HAPPY ===
    pet.step();
    HealthStatus health = pet.getHealth();

    assertEquals("Hunger should decrease by 2", 48, health.getHunger());
    assertEquals("Hygiene should decrease by 2", 48, health.getHygiene());
    assertEquals("Social should decrease by 2", 48, health.getSocial());
    assertEquals("Sleep should decrease by 3", 47, health.getSleep());

    // === Step while SAD ===
    pet.setMood(MoodEnum.SAD);
    pet.step();
    HealthStatus health2 = pet.getHealth();

    assertEquals("Hunger should decrease by 3", 45, health2.getHunger());
    assertEquals("Hygiene should decrease by 3", 45, health2.getHygiene());
    assertEquals("Social should decrease by 3", 45, health2.getSocial());
    assertEquals("Sleep should decrease by 4", 43, health2.getSleep());
  }

  /**
   * Tests {@link PetInterface#interactWith(Action)} for all four actions
   * using the {@link Personality#Energetic} behavior.
   */
  @Test
  public void testInteractWithAllActions_Energetic() {
    pet.setPersonality(Personality.Energetic);

    // All = 30
    pet.adjustNeeds(-20, -20, -20, -20);

    // === FEED ===
    pet.interactWith(Action.FEED);
    HealthStatus h1 = pet.getHealth();
    assertEquals("FEED: Hunger", 70, h1.getHunger());
    assertEquals("FEED: Hygiene", 28, h1.getHygiene());
    assertEquals("FEED: Social", 28, h1.getSocial());
    assertEquals("FEED: Sleep", 28, h1.getSleep());

    // === PLAY ===
    pet.interactWith(Action.PLAY);
    HealthStatus h2 = pet.getHealth();
    assertEquals("PLAY: Hunger", 68, h2.getHunger());
    assertEquals("PLAY: Hygiene", 26, h2.getHygiene());
    assertEquals("PLAY: Social", 88, h2.getSocial());
    assertEquals("PLAY: Sleep", 26, h2.getSleep());

    // === CLEAN ===
    pet.interactWith(Action.CLEAN);
    HealthStatus h3 = pet.getHealth();
    assertEquals("CLEAN: Hunger", 66, h3.getHunger());
    assertEquals("CLEAN: Hygiene", 66, h3.getHygiene());
    assertEquals("CLEAN: Social", 86, h3.getSocial());
    assertEquals("CLEAN: Sleep", 24, h3.getSleep());

    // === SLEEP ===
    pet.interactWith(Action.SLEEP);
    HealthStatus h4 = pet.getHealth();
    assertEquals("SLEEP: Hunger", 64, h4.getHunger());
    assertEquals("SLEEP: Hygiene", 64, h4.getHygiene());
    assertEquals("SLEEP: Social", 84, h4.getSocial());
    assertEquals("SLEEP: Sleep", 64, h4.getSleep());
  }

  /**
   * Tests the {@code step()} method with a {@link Personality#Mysophobia} personality.
   * Verifies that hygiene decays twice as fast as other needs.
   */
  @Test
  public void testStepWithMysophobiaPersonality() {
    pet.setPersonality(Personality.Mysophobia);

    // === Step while HAPPY ===
    pet.step();
    HealthStatus health = pet.getHealth();

    assertEquals("Hunger should decrease by 2", 48, health.getHunger());
    assertEquals("Hygiene should decrease by 4", 46, health.getHygiene());
    assertEquals("Social should decrease by 2", 48, health.getSocial());
    assertEquals("Sleep should decrease by 2", 48, health.getSleep());

    // === Step while SAD ===
    pet.setMood(MoodEnum.SAD);
    pet.step();
    HealthStatus health2 = pet.getHealth();

    assertEquals("Hunger should decrease by 3", 45, health2.getHunger());
    assertEquals("Hygiene should decrease by 6", 40, health2.getHygiene());
    assertEquals("Social should decrease by 3", 45, health2.getSocial());
    assertEquals("Sleep should decrease by 3", 45, health2.getSleep());
  }

  /**
   * Tests {@link PetInterface#interactWith(Action)} for all four actions
   * using the {@link Personality#Mysophobia} behavior.
   */
  @Test
  public void testInteractWithAllActions_Mysophobia() {
    pet.setPersonality(Personality.Mysophobia);

    // All = 30
    pet.adjustNeeds(-20, -20, -20, -20);

    // === FEED ===
    pet.interactWith(Action.FEED);
    HealthStatus h1 = pet.getHealth();
    assertEquals("FEED: Hunger", 70, h1.getHunger());
    assertEquals("FEED: Hygiene", 28, h1.getHygiene());
    assertEquals("FEED: Social", 28, h1.getSocial());
    assertEquals("FEED: Sleep", 28, h1.getSleep());

    // === PLAY ===
    pet.interactWith(Action.PLAY);
    HealthStatus h2 = pet.getHealth();
    assertEquals("PLAY: Hunger", 68, h2.getHunger());
    assertEquals("PLAY: Hygiene", 26, h2.getHygiene());
    assertEquals("PLAY: Social", 68, h2.getSocial());
    assertEquals("PLAY: Sleep", 26, h2.getSleep());

    // === CLEAN ===
    pet.interactWith(Action.CLEAN);
    HealthStatus h3 = pet.getHealth();
    assertEquals("CLEAN: Hunger", 66, h3.getHunger());
    assertEquals("CLEAN: Hygiene", 100, h3.getHygiene()); // should be clamped
    assertEquals("CLEAN: Social", 66, h3.getSocial());
    assertEquals("CLEAN: Sleep", 24, h3.getSleep());

    // === SLEEP ===
    pet.interactWith(Action.SLEEP);
    HealthStatus h4 = pet.getHealth();
    assertEquals("SLEEP: Hunger", 64, h4.getHunger());
    assertEquals("SLEEP: Hygiene", 98, h4.getHygiene());
    assertEquals("SLEEP: Social", 64, h4.getSocial());
    assertEquals("SLEEP: Sleep", 64, h4.getSleep());
  }

  /**
   * Tests the {@code step()} method with a {@link Personality#Needy} personality.
   * Verifies that social decays faster than other needs.
   */
  @Test
  public void testStepWithNeedyPersonality() {
    pet.setPersonality(Personality.Needy);

    // === Step while HAPPY ===
    pet.step();
    HealthStatus health = pet.getHealth();

    assertEquals("Hunger should decrease by 2 (HAPPY)", 48, health.getHunger());
    assertEquals("Hygiene should decrease by 2 (HAPPY)", 48, health.getHygiene());
    assertEquals("Social should decrease by 4 (HAPPY*2)", 46, health.getSocial());
    assertEquals("Sleep should decrease by 2 (HAPPY)", 48, health.getSleep());

    // === Step while SAD ===
    pet.setMood(MoodEnum.SAD);
    pet.step();
    HealthStatus health2 = pet.getHealth();

    assertEquals("Hunger should decrease by 3 (SAD)", 45, health2.getHunger());
    assertEquals("Hygiene should decrease by 3 (SAD)", 45, health2.getHygiene());
    assertEquals("Social should decrease by 6 (SAD*2)", 40, health2.getSocial());
    assertEquals("Sleep should decrease by 3 (SAD)", 45, health2.getSleep());
  }

  /**
   * Tests {@link PetInterface#interactWith(Action)} for all four actions
   * using the {@link Personality#Needy} behavior.
   */
  @Test
  public void testInteractWithAllActions_Needy() {
    pet.setPersonality(Personality.Needy);

    // All = 30
    pet.adjustNeeds(-20, -20, -20, -20);

    // === FEED ===
    pet.interactWith(Action.FEED);
    HealthStatus h1 = pet.getHealth();
    assertEquals("FEED: Hunger", 70, h1.getHunger());
    assertEquals("FEED: Hygiene", 28, h1.getHygiene());
    assertEquals("FEED: Social", 28, h1.getSocial());
    assertEquals("FEED: Sleep", 28, h1.getSleep());

    // === PLAY ===
    pet.interactWith(Action.PLAY);
    HealthStatus h2 = pet.getHealth();
    assertEquals("PLAY: Hunger", 68, h2.getHunger());
    assertEquals("PLAY: Hygiene", 26, h2.getHygiene());
    assertEquals("PLAY: Social", 98, h2.getSocial());
    assertEquals("PLAY: Sleep", 26, h2.getSleep());

    // === CLEAN ===
    pet.interactWith(Action.CLEAN);
    HealthStatus h3 = pet.getHealth();
    assertEquals("CLEAN: Hunger", 66, h3.getHunger());
    assertEquals("CLEAN: Hygiene", 66, h3.getHygiene());
    assertEquals("CLEAN: Social", 96, h3.getSocial());
    assertEquals("CLEAN: Sleep", 24, h3.getSleep());

    // === SLEEP ===
    pet.interactWith(Action.SLEEP);
    HealthStatus h4 = pet.getHealth();
    assertEquals("SLEEP: Hunger", 64, h4.getHunger());
    assertEquals("SLEEP: Hygiene", 64, h4.getHygiene());
    assertEquals("SLEEP: Social", 94, h4.getSocial());
    assertEquals("SLEEP: Sleep", 64, h4.getSleep());
  }

  /**
   * Tests the {@code step()} method with a {@link Personality#Aloof} personality.
   * Verifies that all needs decay equally based on mood.
   */
  @Test
  public void testStepWithAloofPersonality() {
    pet.setPersonality(Personality.Aloof);

    // === Step while HAPPY ===
    pet.step();
    HealthStatus health = pet.getHealth();

    assertEquals("Hunger should decrease by 2 (HAPPY)", 48, health.getHunger());
    assertEquals("Hygiene should decrease by 2 (HAPPY)", 48, health.getHygiene());
    assertEquals("Social should decrease by 2 (HAPPY)", 48, health.getSocial());
    assertEquals("Sleep should decrease by 2 (HAPPY)", 48, health.getSleep());

    // === Step while SAD ===
    pet.setMood(MoodEnum.SAD);
    pet.step();
    HealthStatus health2 = pet.getHealth();

    assertEquals("Hunger should decrease by 3 (SAD)", 45, health2.getHunger());
    assertEquals("Hygiene should decrease by 3 (SAD)", 45, health2.getHygiene());
    assertEquals("Social should decrease by 3 (SAD)", 45, health2.getSocial());
    assertEquals("Sleep should decrease by 3 (SAD)", 45, health2.getSleep());
  }

  /**
   * Tests {@link PetInterface#interactWith(Action)} for all four actions
   * using the {@link Personality#Aloof} behavior.
   */
  @Test
  public void testInteractWithAllActions_Aloof() {
    pet.setPersonality(Personality.Aloof);

    // All = 30
    pet.adjustNeeds(-20, -20, -20, -20);

    // === FEED ===
    pet.interactWith(Action.FEED);
    HealthStatus h1 = pet.getHealth();
    assertEquals("FEED: Hunger", 70, h1.getHunger());
    assertEquals("FEED: Hygiene", 28, h1.getHygiene());
    assertEquals("FEED: Social", 28, h1.getSocial());
    assertEquals("FEED: Sleep", 28, h1.getSleep());

    // === PLAY ===
    pet.interactWith(Action.PLAY);
    HealthStatus h2 = pet.getHealth();
    assertEquals("PLAY: Hunger", 68, h2.getHunger());
    assertEquals("PLAY: Hygiene", 26, h2.getHygiene());
    assertEquals("PLAY: Social", 58, h2.getSocial());
    assertEquals("PLAY: Sleep", 26, h2.getSleep());

    // === CLEAN ===
    pet.interactWith(Action.CLEAN);
    HealthStatus h3 = pet.getHealth();
    assertEquals("CLEAN: Hunger", 66, h3.getHunger());
    assertEquals("CLEAN: Hygiene", 66, h3.getHygiene());
    assertEquals("CLEAN: Social", 56, h3.getSocial());
    assertEquals("CLEAN: Sleep", 24, h3.getSleep());

    // === SLEEP ===
    pet.interactWith(Action.SLEEP);
    HealthStatus h4 = pet.getHealth();
    assertEquals("SLEEP: Hunger", 64, h4.getHunger());
    assertEquals("SLEEP: Hygiene", 64, h4.getHygiene());
    assertEquals("SLEEP: Social", 54, h4.getSocial());
    assertEquals("SLEEP: Sleep", 64, h4.getSleep());
  }

  /**
   * Tests {@link PetInterface#interactWith(Action)} with {@link Personality#Smart}.
   */
  @Test
  public void testInteractWithAllActions_Smart() {
    pet.setPersonality(Personality.Smart);

    pet.adjustNeeds(-20, -20, -20, -20); // All = 30

    // === FEED ===
    pet.interactWith(Action.FEED);
    HealthStatus h1 = pet.getHealth();
    assertEquals("FEED: Hunger", 70, h1.getHunger());
    assertEquals("FEED: Hygiene", 28, h1.getHygiene());
    assertEquals("FEED: Social", 28, h1.getSocial());
    assertEquals("FEED: Sleep", 28, h1.getSleep());

    // === PLAY ===
    pet.interactWith(Action.PLAY);
    HealthStatus h2 = pet.getHealth();
    assertEquals("PLAY: Hunger", 68, h2.getHunger());
    assertEquals("PLAY: Hygiene", 26, h2.getHygiene());
    assertEquals("PLAY: Social", 68, h2.getSocial());
    assertEquals("PLAY: Sleep", 26, h2.getSleep());

    // === CLEAN ===
    pet.interactWith(Action.CLEAN);
    HealthStatus h3 = pet.getHealth();
    assertEquals("CLEAN: Hunger", 66, h3.getHunger());
    assertEquals("CLEAN: Hygiene", 66, h3.getHygiene());
    assertEquals("CLEAN: Social", 66, h3.getSocial());
    assertEquals("CLEAN: Sleep", 24, h3.getSleep());

    // === SLEEP ===
    pet.interactWith(Action.SLEEP);
    HealthStatus h4 = pet.getHealth();
    assertEquals("SLEEP: Hunger", 64, h4.getHunger());
    assertEquals("SLEEP: Hygiene", 64, h4.getHygiene());
    assertEquals("SLEEP: Social", 64, h4.getSocial());
    assertEquals("SLEEP: Sleep", 64, h4.getSleep());
  }

  /**
   * Tests that {@link PetInterface#getHealth()} correctly returns the pet's current need levels.
   * Verifies the values match those modified by {@code adjustNeeds()}.
   */
  @Test
  public void testGetHealth() {
    // Reduce hunger by 10, hygiene by 5, increase social by 3, sleep stays the same
    pet.adjustNeeds(-10, -5, 3, 0);

    HealthStatus health = pet.getHealth();

    assertEquals("Hunger should be 40", 40, health.getHunger());
    assertEquals("Hygiene should be 45", 45, health.getHygiene());
    assertEquals("Social should be 53", 53, health.getSocial());
    assertEquals("Sleep should be 50", 50, health.getSleep());
  }

  /**
   * Tests that {@link PetInterface#getMood()} returns the correct mood based on need levels.
   */
  @Test
  public void testGetMood() {
    // Initially, pet should be HAPPY
    assertEquals(MoodEnum.HAPPY, pet.getMood());

    // Drop one need below 20 to trigger SAD mood
    pet.adjustNeeds(-35, 0, 0, 0); // Hunger = 15
    pet.step(); // triggers mood reevaluation

    assertEquals(MoodEnum.SAD, pet.getMood());
  }

  /**
   * Tests that {@link PetInterface#getPersonality()} returns the personality previously set.
   */
  @Test
  public void testGetPersonality() {
    pet.setPersonality(Personality.Lazy);

    assertEquals("Personality should be Lazy", Personality.Lazy, pet.getPersonality());
  }

  /**
   * Tests that {@link PetInterface#setMood(MoodEnum)} correctly updates the pet's mood,
   * and that {@link PetInterface#getMood()} returns the new value.
   */
  @Test
  public void testSetMood() {
    // Set mood to SAD
    pet.setMood(MoodEnum.SAD);
    assertEquals(MoodEnum.SAD, pet.getMood());

    // Set mood back to HAPPY
    pet.setMood(MoodEnum.HAPPY);
    assertEquals(MoodEnum.HAPPY, pet.getMood());
  }

  /**
   * Tests that {@link PetInterface#setPersonality(Personality)} correctly assigns
   * the personality, and {@link PetInterface#getPersonality()} returns the same.
   */
  @Test
  public void testSetPersonality() {
    pet.setPersonality(Personality.Energetic);
    assertEquals(Personality.Energetic, pet.getPersonality());

    pet.setPersonality(Personality.Mysophobia);
    assertEquals(Personality.Mysophobia, pet.getPersonality());
  }

  /**
   * Tests that {@link PetInterface#isAlive()} reflects whether the pet is alive.
   * Pet should die if any need reaches zero after a {@code step()}.
   */
  @Test
  public void testIsAlive() {
    // Initially alive
    assertTrue("Pet should be alive after start", pet.isAlive());

    // Drop hunger to 0
    pet.adjustNeeds(-50, 0, 0, 0);  // hunger = 0
    pet.step();  // triggers health check

    assertFalse("Pet should be dead if any need is 0", pet.isAlive());
  }

  /**
   * Tests all four need threshold methods:
   * {@link PetInterface#needShower()}, {@link PetInterface#needFeed()},
   * {@link PetInterface#needPlay()}, and {@link PetInterface#needSleep()}.
   */
  @Test
  public void testNeedThresholds() {
    // Lower each stat to 20, just at the threshold
    pet.adjustNeeds(-30, -30, -30, -30);

    assertTrue("Should need shower (hygiene ≤ 20)", pet.needShower());
    assertTrue("Should need feed (hunger ≤ 20)", pet.needFeed());
    assertTrue("Should need play (social ≤ 20)", pet.needPlay());
    assertTrue("Should need sleep (sleep ≤ 20)", pet.needSleep());

    // Raise each stat slightly above threshold
    pet.adjustNeeds(1, 1, 1, 1);

    assertFalse("Should NOT need shower (hygiene > 20)", pet.needShower());
    assertFalse("Should NOT need feed (hunger > 20)", pet.needFeed());
    assertFalse("Should NOT need play (social > 20)", pet.needPlay());
    assertFalse("Should NOT need sleep (sleep > 20)", pet.needSleep());
  }

  /**
   * Tests that {@link PetInterface#step()} and {@link PetInterface#interactWith(Action)}
   * have no effect after the pet has died (any stat reaches 0).
   */
  @Test
  public void testNoEffectAfterDeath() {
    // Drop hunger to 0 → pet will die on step
    pet.adjustNeeds(-50, 0, 0, 0);  // hunger = 0
    pet.step();  // triggers death

    assertFalse("Pet should be dead", pet.isAlive());

    // Capture current state after death
    HealthStatus before = pet.getHealth();

    // Try to interact and step again
    pet.interactWith(Action.FEED);
    pet.step();

    // State should remain unchanged
    HealthStatus after = pet.getHealth();

    assertEquals(before.getHunger(), after.getHunger());
    assertEquals(before.getHygiene(), after.getHygiene());
    assertEquals(before.getSocial(), after.getSocial());
    assertEquals(before.getSleep(), after.getSleep());
  }

  /**
   * Tests that each individual need at the threshold correctly triggers SAD mood.
   */
  @Test
  public void testMoodTriggers() {
    // Hunger
    pet.adjustNeeds(-30, 0, 0, 0); // hygiene = 20
    pet.step();
    assertEquals("Mood should be SAD if hunger ≤ 20", MoodEnum.SAD, pet.getMood());

    // Hygiene
    pet.adjustNeeds(0, -30, 0, 0); // hygiene = 20
    pet.step();
    assertEquals("Mood should be SAD if hygiene ≤ 20", MoodEnum.SAD, pet.getMood());

    pet.startGame(); // reset
    // Social
    pet.adjustNeeds(0, 0, -30, 0); // social = 20
    pet.step();
    assertEquals("Mood should be SAD if social ≤ 20", MoodEnum.SAD, pet.getMood());

    pet.startGame();
    // Sleep
    pet.adjustNeeds(0, 0, 0, -30); // sleep = 20
    pet.step();
    assertEquals("Mood should be SAD if sleep ≤ 20", MoodEnum.SAD, pet.getMood());
  }

  /**
   * Tests that death is triggered when any individual need reaches 0.
   */
  @Test
  public void testDeathTriggers() {
    // Hunger
    pet.adjustNeeds(-50, 0, 0, 0);
    pet.step();
    assertFalse("Pet should die if hygiene = 0", pet.isAlive());

    // Hygiene
    pet.adjustNeeds(0, -50, 0, 0);
    pet.step();
    assertFalse("Pet should die if hygiene = 0", pet.isAlive());

    pet.startGame();
    // Social
    pet.adjustNeeds(0, 0, -50, 0);
    pet.step();
    assertFalse("Pet should die if social = 0", pet.isAlive());

    pet.startGame();
    // Sleep
    pet.adjustNeeds(0, 0, 0, -50);
    pet.step();
    assertFalse("Pet should die if sleep = 0", pet.isAlive());
  }

  /**
   * Tests the internal clamp logic indirectly through
   * {@link PetInterface#adjustNeeds(int, int, int, int)}.
   * Ensures values are clamped between 0 and 100.
   */
  @Test
  public void testClampBehaviorThroughAdjustNeeds() {
    // Push all needs far above 100
    pet.adjustNeeds(1000, 1000, 1000, 1000);
    HealthStatus over = pet.getHealth();
    assertEquals("Hunger should be clamped to 100", 100, over.getHunger());
    assertEquals("Hygiene should be clamped to 100", 100, over.getHygiene());
    assertEquals("Social should be clamped to 100", 100, over.getSocial());
    assertEquals("Sleep should be clamped to 100", 100, over.getSleep());

    // Push all needs far below 0
    pet.adjustNeeds(-200, -200, -200, -200);
    HealthStatus under = pet.getHealth();
    assertEquals("Hunger should be clamped to 0", 0, under.getHunger());
    assertEquals("Hygiene should be clamped to 0", 0, under.getHygiene());
    assertEquals("Social should be clamped to 0", 0, under.getSocial());
    assertEquals("Sleep should be clamped to 0", 0, under.getSleep());

    // Push all needs to a mid-range value
    pet.startGame(); // reset to 50
    // new: 60, 45, 70, 40
    pet.adjustNeeds(10, -5, 20, -10);
    HealthStatus mid = pet.getHealth();
    assertEquals("Hunger should be 60", 60, mid.getHunger());
    assertEquals("Hygiene should be 45", 45, mid.getHygiene());
    assertEquals("Social should be 70", 70, mid.getSocial());
    assertEquals("Sleep should be 40", 40, mid.getSleep());
  }

}
