package kkc;

import static org.junit.Assert.assertTrue;
import kkc.test.RobotTestBed;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;

/**
 * Illustrates JUnit testing of Robocode robots. This test simply verifies that DiamondBot moves to
 * the four positions of the diamond.
 * 
 * @author Kevin Chiogioji
 * 
 */
public class TestDiamondBotMovement extends RobotTestBed {

  /** True if the robot moves to the top position. */
  Boolean visitedUpper = false;
  /** True if the robot moves to the right position. */
  Boolean visitedRight = false;
  /** True if the robot moves to the bottom position. */
  Boolean visitedBottom = false;
  /** True if the robot moves to the left position. */
  Boolean visitedLeft = false;

  /**
   * Specifies that SittingDuck and DiamondBit are to be matched up in this test case.
   * 
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.SittingDuck,kkc.DiamondBot";
  }

  /**
   * This test runs for 10 rounds.
   * 
   * @return The number of rounds.
   */
  @Override
  public int getNumRounds() {
    return 10;
  }

  /**
   * After each turn check to see if we are at one of the four positions. If so, set the
   * corresponding flag.
   * 
   * @param event Information about the current state of the battle.
   */

  @Override
  public void onTurnEnded(TurnEndedEvent event) {
    IRobotSnapshot robot = event.getTurnSnapshot().getRobots()[1];
    double xPos = robot.getX();
    double yPos = robot.getY();

    // Check that all four positions were visited
    if ((xPos < ((width) / 2) + 50) && (xPos > ((width) / 2) - 50) && (yPos > (height - 150))
        && (yPos < (height - 50))) {
      visitedUpper = true;
    }
    if ((xPos > (width - 150)) && (xPos < (width - 50)) && (yPos < (height / 2) + 50)
        && (yPos > (height / 2) - 50)) {
      visitedRight = true;
    }
    if ((xPos < ((width) / 2) + 50) && (xPos > ((width) / 2) - 50) && (yPos > (50))
        && (yPos < (150))) {
      visitedBottom = true;
    }
    if ((xPos > (50)) && (xPos < (150) && (yPos < (height / 2) + 50)) 
      && (yPos > (height / 2) - 50)) {
      visitedLeft = true;
    }
  }

  /**
   * After the battle, check to see that the four positions were visited.
   * 
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    assertTrue("Check upper position", visitedUpper);
    assertTrue("Check right position", visitedRight);
    assertTrue("Check bottom position", visitedBottom);
    assertTrue("Check left position", visitedLeft);
  }
}
