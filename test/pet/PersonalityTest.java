package pet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import pet.helper.personality.Personality;
import pet.helper.personality.PersonalityInterface;

/**
 * Unit tests for the {@link Personality} enum.
 * Verifies that each personality has a valid name and behavior implementation.
 */
public class PersonalityTest {

  /**
   * Tests that all enum constants return the correct display name
   * and have a non-null {@link PersonalityInterface} implementation.
   */
  @Test
  public void testAllPersonalities() {
    for (Personality p : Personality.values()) {
      assertNotNull("PersonalityInterface should not be null for: " + p.name(), p.getPersonality());
      assertEquals("Enum name should match getName()", p.name().toUpperCase(), p.getName());
    }
  }
}
