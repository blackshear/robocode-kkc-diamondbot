package kkc;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Illustrates JUnit testing of Robocode robots. This test simply verifies that the findAngle
 * function correctly calulates the angle.
 * 
 * @author Kevin Chiogioji
 * 
 */
public class TestAngleMethod {

  /**
   * Tests that the findAngle function is working correctly.
   */
  @Test
  public void testAngleFunction() {
    Double result;
    // Boolean used to check if the calculated result is correct
    Boolean correct = false;

    // Create new DiamondBot object
    DiamondBot temp = new DiamondBot();

    // Check that findAngle returns the expected value
    result = temp.findAngle(40.0, 10.0, 20.0, 30.0, 100.0);
    if ((result < 168.464) && (result > 14.462)) {
      correct = true;
    }
    assertTrue("Check angle calculation", correct);
  }
}