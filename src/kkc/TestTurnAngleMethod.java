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
public class TestTurnAngleMethod {

  /**
   * Tests that the turnRobot method is working correctly.
   */
  @Test
  public void testTurnAngleFunction() {
    Double result;
    // Booleans used to check if the calculated results are correct
    Boolean correct1 = false;
    Boolean correct2 = false;

    // Create new DiamondBot object
    DiamondBot temp = new DiamondBot();

    // Check that turnRobot returns the expected value
    result = temp.turnRobot(180.0, 60.0);
    if (result == 120.0) {
      correct1 = true;
    }
    assertTrue("Check that turn angle is correct", correct1);

    result = temp.turnRobot(75.0, 90.0);
    if (result == 345.0) {
      correct2 = true;
    }
    assertTrue("Check that turn angle is correct", correct2);
  }
}