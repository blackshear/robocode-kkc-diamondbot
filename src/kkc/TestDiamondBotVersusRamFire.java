package kkc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import kkc.test.RobotTestBed;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;

/**
 * Illustrates JUnit testing of Robocode robots. This test simply verifies that DiamondBot beats
 * RamFire more than 50% of the time.
 * 
 * @author Kevin Chiogioji
 * 
 */
public class TestDiamondBotVersusRamFire extends RobotTestBed {

  /**
   * Specifies that RamFire and DiamondBot are to be matched up in this test case.
   * 
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.RamFire,kkc.DiamondBot";
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
   * The actual test, which asserts that DiamondBot wins at least half the time against RamFire.
   * 
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    // Return the results in order of getRobotNames.
    BattleResults[] results = event.getIndexedResults();
    // Sanity check that results[1] is DiamondBot (not strictly necessary, but illustrative).
    assertEquals("Check results[1]", results[1].getTeamLeaderName(), "kkc.DiamondBot");

    // Check to make sure DiamondBot was able to beat RamFire more than half of the time.
    assertTrue("Check DiamondBot wins at least half the time against RamFire", (results[1]
        .getFirsts() >= getNumRounds() / 2));
  }
}
