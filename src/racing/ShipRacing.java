/***********************************************************
 * Name: Damien Gill
 * Description: This assignment demonstrates threading by having
 *  multiple threads running different actions and accessing
 *  resources
 ***********************************************************/
package racing;

import racing.Track.Flag;

import java.util.ArrayList;

public class ShipRacing {
	protected Track track;
	protected ArrayList<Ship> ships;
	protected int races;
	protected int numships;
	protected BombGenerator bombGenerator;

	/********************************************************
	 * Description: Constructor method that initializes member
	 * 	variables
	 * Precondition: Requires a length as a double, the number
	 * 	of ships as an int, and the number of races as an int
	 ********************************************************/
	public ShipRacing(double length, int numships, int races) {
		this.track = new Track(length);
		this.ships = new ArrayList<>();
		this.races = races;
		this.numships = numships;
	}

	/********************************************************
	 * Description: Overriden run method from Thread. Runs
	 * 	all races set from the constructor. Instantiates a
	 * 	BombGenerator to create the bombs during the race.
	 * 	Instantiates the number of ships set from the constructor.
	 * 	All ships are then run. At the end the results of the
	 * 	race are print as well as all the bombs that were
	 * 	created during the race.
	 ********************************************************/
	public void run() {
		for(int race = 0; race < races; race++) {	//Runs through all the races
			track.reset();
			this.bombGenerator = new BombGenerator();
			this.bombGenerator.start();
			for(int ship = 0; ship < numships; ship++) {	//instantiates all ships and starts the thread
				ships.add(new Ship(ship));
				ships.get(ship).start();
			}
			countdown();
			for(int ship = 0; ship < numships; ship++) {	//waits until all ships are finished with the race
				try {
					ships.get(ship).join();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Track.flag = Flag.IDLE;
			try {	//waits until BombGenerator finishes
				bombGenerator.join();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
			//prints results
			System.out.println(Track.winners);
			System.out.println("Used   : " + Track.usedBombs);
			System.out.println("Unused : " + Track.unusedBombs);
			System.out.println("Last ID: " + bombGenerator.getID());
			ships.clear();
		}
	}

	/********************************************************
	 * Description: Prints the countdown to the console then
	 * 	sets the flag state to GO.
	 ********************************************************/
	public static void countdown() {
		try {
			System.out.print("3...");
			Thread.sleep(1000);
			System.out.print("2...");
			Thread.sleep(1000);
			System.out.print("1...");
			Thread.sleep(1000);
			Track.flag = Flag.GO;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
