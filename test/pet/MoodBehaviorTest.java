package pet;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import pet.helper.mood.HappyBehavior;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodBehavior;
import pet.helper.mood.SadBehavior;

/**
 * Unit tests for {@link HappyBehavior} and {@link SadBehavior}.
 */
public class MoodBehaviorTest {

  private Pet pet;

  /**
   * Set up for the Pet and initialize the hunger, hygiene, social, and sleep.
   */
  @Before
  public void setUp() {
    pet = new Pet();

    // Manually set to known values for controlled testing
    pet.adjustNeeds(-10, -10, -10, -10);
  }

  /**
   * Tests the HappyBehavior decreases needs slowly.
   */
  @Test
  public void testHappyBehavior() {
    MoodBehavior happy = new HappyBehavior();
    happy.applyMoodEffect(pet);

    HealthStatus health = pet.getHealth();

    assertEquals(38, health.getHunger());   // 40 - 2
    assertEquals(38, health.getHygiene());  // 40 - 2
    assertEquals(38, health.getSocial());   // 40 - 2
    assertEquals(38, health.getSleep());    // 40 - 2
  }

  /**
   * Tests the SadBehavior decreases needs more rapidly.
   */
  @Test
  public void testSadBehavior() {
    MoodBehavior sad = new SadBehavior();
    sad.applyMoodEffect(pet);

    HealthStatus health = pet.getHealth();

    assertEquals(36, health.getHunger());   // 40 - 4
    assertEquals(36, health.getHygiene());  // 40 - 4
    assertEquals(36, health.getSocial());   // 40 - 4
    assertEquals(36, health.getSleep());    // 40 - 4
  }
}
