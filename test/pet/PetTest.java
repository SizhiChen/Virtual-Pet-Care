package pet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import pet.helper.Action;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodEnum;

/**
 * Unit tests for the {@link Pet} class.
 */
public class PetTest {

  private Pet pet;
  private ByteArrayOutputStream outputStream;
  private PrintStream originalOut;

  /**
   * Initializes a fresh pet before each test.
   */
  @Before
  public void setUp() {
    pet = new Pet();
    outputStream = new ByteArrayOutputStream();
    originalOut = System.out;
  }

  /**
   * Tests the pet's initial state upon creation.
   */
  @Test
  public void testInitialState() {
    HealthStatus health = pet.getHealth();
    assertEquals(50, health.getHunger());
    assertEquals(50, health.getHygiene());
    assertEquals(50, health.getSocial());
    assertEquals(50, health.getSleep());
    assertEquals(MoodEnum.HAPPY, pet.getMood());
  }

  /**
   * Tests the FEED interaction.
   * Hunger should increase, while hygiene, social, and sleep should decrease.
   */
  @Test
  public void testFeedInteraction() {
    pet.interactWith(Action.FEED);
    HealthStatus health = pet.getHealth();

    assertEquals(90, health.getHunger());    // 50 + 40 (max 100)
    assertEquals(42, health.getHygiene());   // 50 - 8
    assertEquals(48, health.getSocial());    // 50 - 2
    assertEquals(48, health.getSleep());     // 50 - 2
  }

  /**
   * Tests the PLAY interaction.
   * Social should increase, others should decrease.
   */
  @Test
  public void testPlayInteraction() {
    pet.interactWith(Action.PLAY);
    HealthStatus health = pet.getHealth();

    assertEquals(42, health.getHunger());    // 50 - 8
    assertEquals(42, health.getHygiene());   // 50 - 8
    assertEquals(80, health.getSocial());    // 50 + 30
    assertEquals(42, health.getSleep());     // 50 - 8
  }

  /**
   * Tests the CLEAN interaction.
   * Hygiene should increase significantly, others should decrease slightly.
   */
  @Test
  public void testCleanInteraction() {
    pet.interactWith(Action.CLEAN);
    HealthStatus health = pet.getHealth();

    assertEquals(45, health.getHunger());    // 50 - 5
    assertEquals(100, health.getHygiene());  // 50 + 80 (max 100)
    assertEquals(45, health.getSocial());    // 50 - 5
    assertEquals(45, health.getSleep());     // 50 - 5
  }

  /**
   * Tests the SLEEP interaction.
   * Sleep should increase significantly, others decrease more.
   */
  @Test
  public void testSleepInteraction() {
    pet.interactWith(Action.SLEEP);
    HealthStatus health = pet.getHealth();

    assertEquals(20, health.getHunger());    // 50 - 30
    assertEquals(40, health.getHygiene());   // 50 - 10
    assertEquals(20, health.getSocial());    // 50 - 30
    assertEquals(100, health.getSleep());    // 50 + 80 (max 100)
  }

  /**
   * Tests the step() behavior in HAPPY mood.
   * All needs should decrease slightly by 2.
   */
  @Test
  public void testStepInHappyMood() {
    pet.setMood(MoodEnum.HAPPY); // Explicit, but default is HAPPY
    pet.step();
    HealthStatus health = pet.getHealth();

    assertEquals(48, health.getHunger());   // 50 - 2
    assertEquals(48, health.getHygiene());  // 50 - 2
    assertEquals(48, health.getSocial());   // 50 - 2
    assertEquals(48, health.getSleep());    // 50 - 2
  }

  /**
   * Tests the step() behavior in SAD mood.
   * Needs should degrade more rapidly.
   */
  @Test
  public void testStepInSadMood() {
    // Lower a stat to trigger SAD mood
    pet.interactWith(Action.SLEEP); // hunger: 20 hygiene: 40 social: 20 sleep: 100
    pet.interactWith(Action.CLEAN); // hunger: 15 hygiene: 100 social: 15 sleep: 95

    HealthStatus health = pet.getHealth();
    assertEquals(15, health.getHunger());
    assertEquals(100, health.getHygiene());
    assertEquals(15, health.getSocial());
    assertEquals(95, health.getSleep());

    assertEquals(MoodEnum.SAD, pet.getMood()); // Ensure SAD mood

    pet.step(); // hunger: 11 hygiene: 96 social: 11 sleep: 91
    health = pet.getHealth();

    // Check that needs have decreased by 4
    assertEquals(11, health.getHunger());
    assertEquals(96, health.getHygiene());
    assertEquals(11, health.getSocial());
    assertEquals(91, health.getSleep());
  }

  /**
   * Tests that getHealth() returns accurate values
   * after performing a sequence of interactions.
   */
  @Test
  public void testGetHealthAfterInteractions() {
    // Initial hunger = 50
    pet.interactWith(Action.FEED); // hunger +40 -> 90
    pet.interactWith(Action.PLAY); // hunger -8 -> 82

    HealthStatus health = pet.getHealth();

    assertEquals(82, health.getHunger());
    assertEquals(34, health.getHygiene());  // 50 -8 (FEED) -8 (PLAY)
    assertEquals(78, health.getSocial());   // 50 -2 (FEED) +30 (PLAY)
    assertEquals(40, health.getSleep());    // 50 -2 (FEED) -8 (PLAY)
  }

  /**
   * Tests that the pet starts in a HAPPY mood by default.
   */
  @Test
  public void testInitialMoodIsHappy() {
    assertEquals(MoodEnum.HAPPY, pet.getMood());
  }


  /**
   * Tests that the pet switches to SAD mood when a need drops below 20.
   */
  @Test
  public void testMoodBecomesSad() {
    for (int i = 0; i < 16; i++) {
      pet.step();
    }

    assertEquals(MoodEnum.SAD, pet.getMood());
  }

  /**
   * Tests manually setting the pet's mood to SAD.
   */
  @Test
  public void testSetMoodToSad() {
    pet.setMood(MoodEnum.SAD);
    assertEquals(MoodEnum.SAD, pet.getMood());
  }

  /**
   * Tests manually setting the pet's mood to HAPPY.
   */
  @Test
  public void testSetMoodToHappy() {
    pet.setMood(MoodEnum.SAD); // switch to SAD first
    assertEquals(MoodEnum.SAD, pet.getMood());
    pet.setMood(MoodEnum.HAPPY); // switch back to HAPPY
    assertEquals(MoodEnum.HAPPY, pet.getMood());
  }

  /**
   * Tests that adjustNeeds correctly adds and subtracts values within bounds.
   */
  @Test
  public void testAdjustNeeds() {
    pet.adjustNeeds(10, -5, 20, -10); // hunger +10, hygiene -5, social +20, sleep -10
    HealthStatus health = pet.getHealth();

    assertEquals(60, health.getHunger());   // 50 + 10
    assertEquals(45, health.getHygiene());  // 50 - 5
    assertEquals(70, health.getSocial());   // 50 + 20
    assertEquals(40, health.getSleep());    // 50 - 10
  }

  /**
   * Tests that the pet dies when a stat (e.g., hunger) reaches 0.
   */
  @Test
  public void testPetDiesWhenStatReachesZero() {
    // Repeatedly put pet to sleep to drop hunger and social
    for (int i = 0; i < 2; i++) {
      pet.interactWith(Action.SLEEP); // each time hunger -30, social -30
    }

    HealthStatus health = pet.getHealth();

    // Hunger or social should now be 0
    assertTrue(health.getHunger() == 0 && health.getSocial() == 0);

    // Try another interaction: should have no effect
    pet.interactWith(Action.FEED);
    pet.step();

    // Values shouldn't change after death
    HealthStatus afterDeath = pet.getHealth();
    assertEquals(health.getHunger(), afterDeath.getHunger());
    assertEquals(health.getHygiene(), afterDeath.getHygiene());
    assertEquals(health.getSocial(), afterDeath.getSocial());
    assertEquals(health.getSleep(), afterDeath.getSleep());
  }

  /**
   * Tests that hunger decreases over time if the pet is not fed.
   * Verifies degradation over multiple steps while in HAPPY mood.
   */
  @Test
  public void testHungerDecreasesOverTime() {
    pet.setMood(MoodEnum.HAPPY);
    int initialHunger = pet.getHealth().getHunger();

    // Simulate 5 time steps without feeding
    for (int i = 0; i < 5; i++) {
      pet.step();
    }

    int expectedHunger = initialHunger - (2 * 5);
    assertEquals(expectedHunger, pet.getHealth().getHunger());
  }

  /**
   * Tests that hygiene decreases over time without cleaning.
   */
  @Test
  public void testHygieneDecreasesOverTime() {
    pet.setMood(MoodEnum.HAPPY);
    int initialHygiene = pet.getHealth().getHygiene();

    // Simulate 5 time steps
    for (int i = 0; i < 5; i++) {
      pet.step();
    }

    int expectedHygiene = initialHygiene - (2 * 5);
    assertEquals(expectedHygiene, pet.getHealth().getHygiene());
  }

  /**
   * Tests that the pet's social stat decreases over time if not played with.
   */
  @Test
  public void testSocialDecreasesOverTime() {
    int initialSocial = pet.getHealth().getSocial();

    // Simulate 5 steps of time passing
    for (int i = 0; i < 5; i++) {
      pet.setMood(MoodEnum.SAD); // Manually set mood to sad
      pet.step();
    }

    int expectedSocial = initialSocial - (4 * 5);
    assertEquals(expectedSocial, pet.getHealth().getSocial());
  }

  /**
   * Tests that neglecting the pet's needs over time decreases its overall health.
   */
  @Test
  public void testHealthDecreasesWithNeglect() {
    pet.setMood(MoodEnum.HAPPY);

    // Simulate 10 steps of time with no interactions
    for (int i = 0; i < 10; i++) {
      pet.step();
    }

    HealthStatus health = pet.getHealth();

    assertEquals(30, health.getHunger());
    assertEquals(30, health.getHygiene());
    assertEquals(30, health.getSocial());
    assertEquals(30, health.getSleep());
  }

  /**
   * Tests that after prolonged neglect, the pet displays multiple warning messages
   * indicating which needs have become low, and that the pet is in a SAD mood.
   */
  @Test
  public void testNeedsImproves() {
    // Step 1: Capture System.out
    System.setOut(new PrintStream(outputStream));

    // Simulate 10 steps of time with no interactions
    for (int i = 0; i < 16; i++) {
      pet.step();
    }
    // Step 3: Restore System.out
    System.setOut(originalOut);
    // Step 4: Verify output
    String capturedOutput = outputStream.toString().trim();
    assertTrue(capturedOutput.contains("Your pet is Sad, please take care of them!!"));
    assertTrue(capturedOutput.contains("Pet need to feed"));
    assertTrue(capturedOutput.contains("Pet need to clean"));
    assertTrue(capturedOutput.contains("Pet need to play"));
    assertTrue(capturedOutput.contains("Pet need to sleep"));
  }

  /**
   * Tests that the pet switches behavior strategies dynamically when mood changes.
   * Verifies that the correct MoodBehavior is applied at runtime.
   */
  @Test
  public void testSwitchesBehaviorStrategyDynamically() {
    pet.setMood(MoodEnum.HAPPY);
    pet.step(); // Apply HappyBehavior: -2 to all

    HealthStatus afterHappyStep = pet.getHealth();

    pet.setMood(MoodEnum.SAD);
    pet.step(); // Apply SadBehavior: -4 to all

    HealthStatus afterSadStep = pet.getHealth();

    // Happy step: -2, Sad step: -4 → total -6 from initial 50
    assertEquals(44, afterSadStep.getHunger());
    assertEquals(44, afterSadStep.getHygiene());
    assertEquals(44, afterSadStep.getSocial());
    assertEquals(44, afterSadStep.getSleep());
  }

}
