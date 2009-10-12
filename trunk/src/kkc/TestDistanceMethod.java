package kkc;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Illustrates JUnit testing of Robocode robots. This test simply verifies that the findDistance
 * function correctly calulates the distance.
 * 
 * @author Kevin Chiogioji
 * 
 */
public class TestDistanceMethod {

  /**
   * Tests that the findDistance function is working correctly.
   */
  @Test
  public void testDistanceFunction() {
    Double result;

    // Boolean used to check if the calculated result is correct
    Boolean correct = false;

    // Create new DiamondBot object
    DiamondBot temp = new DiamondBot();

    // Check that findDistance returns the expected value
    result = temp.findDistance(10.0, 0.0, 0.0, 10.0);
    if ((result < 14.143) && (result > 14.141)) {
      correct = true;
    }
    assertTrue("Check distance calculation", correct);
  }
}
