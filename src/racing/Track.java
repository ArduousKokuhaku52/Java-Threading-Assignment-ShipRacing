/***********************************************************
 * Name: Damien Gill
 * Description: This assignment demonstrates threading by having
 *  multiple threads running different actions and accessing
 *  resources
 ***********************************************************/
package racing;

import java.util.ArrayList;

public class Track {
	
	public enum Flag {
		IDLE, GO
	};
	
	public static double length;
	public static Flag flag;
	public static ArrayList<Ship> winners;
	public static ArrayList<GravityBomb> unusedBombs;
	public static ArrayList<GravityBomb> usedBombs;

	/********************************************************
	 * Description: Constructor method that initializes member
	 * 	variables
	 * Precondition: Requires a length as a double
	 ********************************************************/
	public Track(double length) {
		Track.length = length;
		Track.flag = Flag.IDLE;
		Track.winners = new ArrayList<>();
		Track.unusedBombs = new ArrayList<>();
		Track.usedBombs = new ArrayList<>();
	}

	/********************************************************
	 * Description: This method resets all ArrayLists and sets
	 * 	flag back to IDLE state.
	 ********************************************************/
	public void reset() {
		Track.flag = Flag.IDLE;
		Track.winners.clear();
		Track.unusedBombs.clear();
		Track.usedBombs.clear();
	}
}
