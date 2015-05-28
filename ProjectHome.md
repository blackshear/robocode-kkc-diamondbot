The DiamondBot robot implements the following strategy:

**Movement:** DiamondBot moves in a clockwise diamond pattern.  If a collision occurs, it turns 45 degrees, backs up 150 pixels, and continues with its normal movement.  If DiamondBot is within 200 pixels of an enemy robot, it stops moving.

**Targeting:** The radar constantly scans for enemy robots.

**Firing:** When an enemy robot is scanned, DiamondBot will fire a bullet if the enemy robot is within a distance of 400 pixels.  The strength of the bullet fired varies based on distance.  The closer the enemy, the stronger the bullet.