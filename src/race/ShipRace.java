/***********************************************************
 * Name: Damien Gill
 * Description: This assignment demonstrates threading by having
 *  multiple threads running different actions and accessing
 *  resources
 ***********************************************************/
package race;

import racing.ShipRacing;

import java.util.Scanner;

public class ShipRace {

    /********************************************************
     * Description: Main method that prompts the user for
     *  the length of the track, the number of ships, and
     *  the number of races. It then prints the results of
     *  the races to the console.
     ********************************************************/
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Length: ");
        double length = scnr.nextDouble();
        System.out.print("Ships: ");
        int ships = scnr.nextInt();
        System.out.print("Races: ");
        int races = scnr.nextInt();

        ShipRacing shipRacing = new ShipRacing(length, ships, races);
        shipRacing.run();

        scnr.close();
    }
}
