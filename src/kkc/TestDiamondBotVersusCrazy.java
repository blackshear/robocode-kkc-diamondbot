package kkc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import kkc.test.RobotTestBed;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;

/**
 * Illustrates JUnit testing of Robocode robots. This test simply verifies that DiamondBot beats
 * crazy more than 50% of the time.
 * 
 * @author Kevin Chiogioji
 * 
 */
public class TestDiamondBotVersusCrazy extends RobotTestBed {

  /**
   * Specifies that Crazy and DiamondBot are to be matched up in this test case.
   * 
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.Crazy,kkc.DiamondBot";
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
   * The actual test, which asserts that DiamondBot wins at least half the time against Crazy.
   * 
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    // Return the results in order of getRobotNames.
    BattleResults[] results = event.getIndexedResults();
    // Sanity check that results[1] is DiamondBot (not strictly necessary,
    // but
    // illustrative).
    assertEquals("Check results[1]", results[1].getTeamLeaderName(), "kkc.DiamondBot");

    // Check to make sure DiamondBot was able to beat Crazy more than half
    // of the time.
    assertTrue("Check DiamondBot wins at least half the time against Crazy", (results[1]
        .getFirsts() >= getNumRounds() / 2));
  }
}
