import java.util.Random;

/**
 *This class represents a ship in a ship harbor simulation. This class sets all of the variables for each 
 *ship being simulated such as the arrival time, the time it had to wait to unload, and others.
 * 
 * @author Tena Percy and Haley Daniels 
 * 
 */
public class Ship
{
    protected Random gen = new Random();
    protected int between = 0; //between 15 & 145 mins
    protected int unload = 0;   //between 45 & 90 mins
    protected double arrive = 0;   //previous ship arrival + between time
    protected double startUnload = 0;  //time the previous ship stops unloading(finishUnload) and current ship starts
    protected double shipWait = 0;     //time between arrive and startUnload
    protected double finishUnload = 0;     //startUnload + unload;
    protected double harborTime = 0;   //shipWait+unload;

    /**
     * Constructor for objects of class Ship
     */
    public Ship()
    {
        between = gen.nextInt(130) + 15;
        unload = gen.nextInt(45) + 45;
    }

    public int getBetween()
    {
        return between;
    }

    public int getUnload()
    {
        return unload;
    }

    public double getArrive()
    {
        return arrive;
    }

    public void setArrive(double prevShipArriv) 
    {
        arrive = (double) prevShipArriv + between;
    }

    public double getStartUnload()
    {
        return startUnload;
    }

    public void setStartUnload(double prevFinishUnload) 
    {
        if(prevFinishUnload < arrive) {
            startUnload = arrive;
        } else {
            startUnload = prevFinishUnload;
        }
    }

    public double getShipWait()
    {
        return shipWait;
    }

    public void setShipWait(double wait)
    {
        shipWait = wait;
    }

    public double getFinishUnload()
    {
        return finishUnload;
    }

    public void setFinishUnload()
    {
        finishUnload = startUnload + unload;
    }

    public double getHarborTime()
    {

        return harborTime;
    }

    public void setHarborTime()
    {
        harborTime = shipWait + unload;
    }

}
