/***********************************************************
 * Name: Damien Gill
 * Description: This assignment demonstrates threading by having
 *  multiple threads running different actions and accessing
 *  resources
 ***********************************************************/
package racing;

import racing.Track.Flag;

public class Ship extends Thread {
	
	protected long startTime;
	protected long elapseTime;
	protected int ID;
	protected double distance;
	protected GravityBomb gravityBomb;

	/********************************************************
	 * Description: Constructor method that initializes the ID
	 * 	and distance member variables, and sets gravityBomb to
	 * 	null
	 * Precondition: Requires an ID as an int
	 ********************************************************/
	public Ship(int ID) {
		this.ID = ID;
		this.distance = Track.length;
		this.gravityBomb = null;
	}

	/********************************************************
	 * Description: Overriden run method that calculates the
	 * 	time the boats finish the race and handles the collision
	 *  of boats with gravity bombs
	 * Postcondition: Boats are added to Track's winners ArrayList
	 * 	at the end
	 ********************************************************/
	@Override
	public void run() {
		while(Track.flag == Flag.IDLE) {
			try {
				Thread.sleep(0, 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		startTime = System.currentTimeMillis();
		while(distance > 0) {
			synchronized(Track.unusedBombs) {
				if (Track.unusedBombs.isEmpty()) {	//Boat normal travel speed
					this.distance -= 1.0;
				} else {	//Boat distance calculation when hitting a gravity bomb
					this.gravityBomb = Track.unusedBombs.remove(0);
					Track.usedBombs.add(this.gravityBomb);
					this.distance -= (1.0 + this.gravityBomb.amount);
					this.gravityBomb = null;
				}
			}
		}
		//Returns time taken to complete race then adds the ship to Track ArrayList
		elapseTime = System.currentTimeMillis() - startTime;
		Track.winners.add(this);
	}

	/********************************************************
	 * Description: Overriden toString method. Returns the ID
	 * 	and elapsed time formatted in parentheses e.g. (4,16)
	 ********************************************************/
	@Override
	public String toString() {
		return String.format("(%d,%d)", this.ID, this.elapseTime);
	}
	
}
