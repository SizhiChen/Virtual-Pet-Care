package pet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import pet.helper.HealthStatus;


/**
 * Unit tests for the {@link HealthStatus} class.
 */
public class HealthStatusTest {

  /**
   * Tests that the constructor stores the correct values.
   */
  @Test
  public void testConstructorAndGetters() {
    HealthStatus health = new HealthStatus(25, 50, 75, 90);

    assertEquals(25, health.getHunger());
    assertEquals(50, health.getHygiene());
    assertEquals(75, health.getSocial());
    assertEquals(90, health.getSleep());
  }

  /**
   * Tests the string representation of the HealthStatus object.
   */
  @Test
  public void testToString() {
    HealthStatus health = new HealthStatus(10, 20, 30, 40);

    String expected = "HealthStatus{hunger=10, hygiene=20, social=30, sleep=40}";
    assertEquals(expected, health.toString());
  }
}
