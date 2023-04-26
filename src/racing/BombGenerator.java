/***********************************************************
 * Name: Damien Gill
 * Description: This assignment demonstrates threading by having
 *  multiple threads running different actions and accessing
 *  resources
 ***********************************************************/
package racing;

import java.util.Random;

public class BombGenerator extends Thread{
    private Random rand;
    private GravityBomb gravityBomb;
    private long ID;

    /********************************************************
     * Description: Constructor method that initializes random
     *  seeded to System's current time, sets GravityBomb object
     *  to null, and initializes ID to 0.
     ********************************************************/
    public BombGenerator(){
        this.rand = new Random(System.currentTimeMillis());
        this.gravityBomb = null;
        this.ID = 0;
    }

    //generic getter for ID member variable; returns long
    public long getID() {
        return this.ID;
    }

    /********************************************************
     * Description: Overriden run method from Thread. Sleeps
     *  until Track's flag is GO and then generates GravityBomb
     *  objects that are added to Track's unusedbombs ArrayList.
     *  Creates GravityBombs until race is finished.
     ********************************************************/
    @Override
    public void run(){
        while(Track.flag == Track.Flag.IDLE) {  //Sleeps until the start of the race
            try {
                Thread.sleep(0, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(Track.flag == Track.Flag.GO) {    //Creates gravity bombs and adds them to the track during the race
            this.gravityBomb = new GravityBomb(this.ID++, this.rand.nextDouble());
            Track.unusedBombs.add(this.gravityBomb);
            try {
                Thread.sleep(0, 1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
