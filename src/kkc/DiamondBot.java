package kkc;

import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * The DiamondBot class creates a robot that attempts to defeat sample robots Walls, Ramfire,
 * Spinbot, Crazy, Fire, Corners, Tracker, and Sitting Duck.
 * 
 * @author Kevin Chiogioji
 * @version 1.0
 */

public class DiamondBot extends Robot {

  private Integer movement = 0;
  private Double width = 0.0;
  private Double height = 0.0;
  private Integer missed = 0;
  private Boolean move = true;

  /**
   * Creates a competitive robot that moves in a diamond pattern and tries to defeat enemy robots.
   */
  public void run() {

    setColors(Color.DARK_GRAY, Color.BLACK, Color.GRAY);

    while (true) {

      Double xCoord;
      Double yCoord;
      Double distance = 0.0;
      Double angle;

      // Get coordinates of robot
      xCoord = getX();
      yCoord = getY();

      // Get heading of robot
      Double heading = getHeading();

      Double battleFieldWidth = getBattleFieldWidth();
      Double battleFieldHeight = getBattleFieldHeight();

      // Scan for enemies
      turnRadarRight(360);

      // Set coordinates of movement
      if (move) {
        switch (movement % 4) {
        case 0:
          width = battleFieldWidth / 2;
          height = battleFieldHeight - 100;
          break;
        case 1:
          width = battleFieldWidth - 100;
          height = battleFieldHeight / 2;
          break;
        case 2:
          width = battleFieldWidth / 2;
          height = 100.0;
          break;
        default:
          width = 100.0;
          height = battleFieldHeight / 2;
          break;
        }

        // Calculate distance and angle for next move
        distance = findDistance(width, height, xCoord, yCoord);

        // Turn robot for next movement on the battlefield
        angle = turnRobot(findAngle(width, height, xCoord, yCoord, distance), heading);
        turnRight(angle);

        // Move robot forward
        ahead(distance);

        // Increment for next movement to make on the battlefield
        movement++;
      }
    }
  }

  /**
   * Calculates distance needed to move robot to next position.
   * 
   * @param width X coordinate of next position
   * @param height Y coordinate of next position
   * @param xCoord current X coordinate of robot
   * @param yCoord current Y coordinate of robot
   * @return distance returns the calculated distance the robot is away from the next position
   */
  public Double findDistance(Double width, Double height, Double xCoord, Double yCoord) {
    Double distance = 0.0;
    // Calculate distance robot is away from center using c^2 = a^2 + b^2 (Pythagorian Theorem)
    distance = Math.sqrt(Math.pow((width) - xCoord, 2) + Math.pow((height) - yCoord, 2));
    return distance;
  }

  /**
   * Calculates the slope between 0 and 360 degrees from current position to next position.
   * 
   * @param width X coordinate of next position
   * @param height Y coordinate of next position
   * @param xCoord current X coordinate of robot
   * @param yCoord current Y coordinate of robot
   * @param distance distance the robot is from the next position
   * @return angle returns the slope between 0 and 360 degrees from current position to next
   * position
   */
  public Double findAngle(Double width, Double height, Double xCoord, Double yCoord, 
      Double distance) {

    Double angle = 0.0;
    // Check if robot is in top left quadrant with respect to width and height coordinates
    if (xCoord < (width) && yCoord > (height)) {
      // Calculates angle in degrees that robot must be turned to face point
      angle = 180 - (Math.toDegrees((Math.asin(((width) - xCoord) / distance))));
    }

    // Check if robot is in bottom left quadrant with respect to width and height coordinates
    else if (xCoord < (width) && yCoord < (height)) {
      // Calculates angle in degrees that robot must be turned to face point
      angle = Math.toDegrees(Math.asin(((width) - xCoord) / distance));
    }

    // Check if robot is in top right quadrant with respect to width and height coordinates
    else if (xCoord > (width) && yCoord > (height)) {
      // Calculates angle in degrees that robot must be turned to face point
      angle = Math.toDegrees(Math.asin((xCoord - (width)) / distance)) + 180;
    }

    // Check if robot is in bottom right quadrant with respect to width and height coordinates
    else if (xCoord > (width) && yCoord < (height)) {
      // Calculates angle in degrees that robot must be turned to face point
      angle = 360 - Math.toDegrees(Math.asin((xCoord - (width)) / distance));
    }
    return angle;
  }

  /**
   * Calculates the amount the robot needs to turn for the next movement.
   * 
   * @param angle contains the slope of between the current position and next position
   * @param heading contains the current heading of the robot
   * @return difference the difference between the two angles for the robot to turn
   */
  public Double turnRobot(Double angle, Double heading) {

    Double difference;

    if (angle > heading) {
      difference = angle - heading;
    }
    else {
      difference = 360 - (heading - angle);
    }
    return difference;
  }

  /**
   * Fires a bullet that is proportional to the enemy's distance.
   * 
   * @param e contains various information used to make the robot do some action
   */
  public void onScannedRobot(ScannedRobotEvent e) {

    Double enemyEnergy = e.getEnergy();
    Double enemyDistance = e.getDistance();
    out.println("Distance = " + enemyDistance);

    // Credits to Mathew A. Nelson
    double angle = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getGunHeading()));

    // Targeting and firing to use if hitting enemy or if enemy runs out of energy
    if (missed < 6 || enemyEnergy == 0.0) {
      // Stay still if robot is less than 200 pixels away
      if (enemyDistance < 200.0) {
        move = false;
      }
      else {
        move = true;
      }

      turnGunRight(angle);

      // Fire at enemy robot based on distance, closer = stronger, farther = weaker
      if (enemyDistance < 100) {
        fire(4);
        out.println("Power = 4");
      }
      else if (enemyDistance < 200) {
        fire(2);
        out.println("Power = 2");
      }
      else if (enemyDistance < 400) {
        fire(1);
        out.println("Power = 1");
      }
    }
    // Targeting and firing to use if missing enemy, lead right
    else {
      if (missed < 6 && missed <= 12) {
        // Adjust gun angle to lead target
        if (enemyDistance < 100) {
          turnGunRight(angle + 12);
        }
        else if (enemyDistance < 200) {
          turnGunRight(angle + 15);
        }
        else if (enemyDistance < 300) {
          turnGunRight(angle + 17);
        }
        else if (enemyDistance < 400) {
          turnGunRight(angle + 20);
        }
        // Fire at enemy robot based on distance, closer = stronger, farther = weaker
        if (enemyDistance < 400) {
          fire(2);
          out.println("Power = 2");
        }
        else {
          fire(1);
          out.println("Power = 1");
        }
      }
      else {
        // Adjust gun angle to lead target, lead left
        if (enemyDistance < 100) {
          turnGunRight(angle - 12);
        }
        else if (enemyDistance < 200) {
          turnGunRight(angle - 15);
        }
        else if (enemyDistance < 300) {
          turnGunRight(angle - 17);
        }
        else if (enemyDistance < 400) {
          turnGunRight(angle - 20);
        }

        // Fire at enemy robot based on distance, closer = stronger, farther = weaker
        if (enemyDistance < 400) {
          fire(2);
          out.println("Power = 2");
        }
        else {
          fire(1);
          out.println("Power = 1");
        }
      }
    }
    turnRadarRight(90);
  }

  /**
   * Keeps track of how many times we missed the enemy.
   * 
   * @param e contains various information used to make the robot do some action
   */
  public void onBulletMissed(BulletMissedEvent e) {
    missed = (missed + 1) % 18;
  }

  /**
   * Used to reset number of misses if we hit an enemy.
   * 
   * @param e contains various information used to make the robot do some action
   */
  public void onBulletHit(BulletHitEvent e) {
    if (missed < 6) {
      missed = 0;
    }
    else if (missed <= 6 && missed < 12) {
      missed = 6;
    }
    else {
      missed = 12;
    }
  }

  /**
   * Moves the robot out of the way if a collision occurs.
   * 
   * @param e contains various information used to make the robot do some action
   */
  public void onHitRobot(HitRobotEvent e) {
    turnLeft(45);
    back(150);
  }
}
