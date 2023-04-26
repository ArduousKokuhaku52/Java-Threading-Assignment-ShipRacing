/***********************************************************
 * Name: Damien Gill
 * Description: This assignment demonstrates threading by having
 *  multiple threads running different actions and accessing
 *  resources
 ***********************************************************/
package racing;

public class GravityBomb {
    public long bombID;
    public double amount;

    /********************************************************
     * Description: Constructor method that sets the bombID
     *  and amount for the member variables
     * Precondition: Requires a bombID as a long and an amount
     *  as a double
     ********************************************************/
    public GravityBomb(long bombID, double amount) {
        this.bombID = bombID;
        this.amount = amount;
    }

    /********************************************************
     * Description: Overriden toString method. Returns member
     *  variables bombID and amount formatted in parentheses
     *  e.g. (0,0.9617)
     ********************************************************/
    @Override
    public String toString() {
        return String.format("(%d,%.4f)", this.bombID, this.amount);
    }
}
