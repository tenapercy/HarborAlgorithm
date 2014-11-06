import java.util.Scanner;

/**
 * This is a simulation of a ship harbor. It calculates the average time ships were in the harbor, the maximum time a ship was in the
 * harbor, the average time a ship had to wait to unload the cargo, the maximum time a ship had to wait, and the percentage of time 
 * the harbor was idle (had no ships).
 * 
 * @author Tena Percy and Haley Daniels 
 * 
 */
public class Harbor
{
    protected static double avgHarTime = 0;
    protected static double maxHarTime = 0;
    protected static double totalHarTime = 0; 
    protected static double avgWaitTime = 0;
    protected static double maxWaitTime = 0;
    protected static double totalWaitTime = 0;
    protected static double avgIdleTime = 0;
    protected static double perIdleTime = 0;
    protected static double totalIdleTime = 0;
    protected static double totalTime = 0.0;
    protected static int numOfShips = 0;
    protected static String result = "";
    protected static Ship[] array;

    /**
     * Constructor for objects of class Harbor
     */
    public static void main()
    {

        Scanner conIn = new Scanner(System.in);  
        String more = null;
        do
        {

            System.out.println("Input number of ships: ");
            numOfShips = conIn.nextInt();
            more = conIn.nextLine();
            result = getResults();
            System.out.println("Result: " + result);

            //Determine if there is another number to evaluate
            System.out.println();    
            System.out.println("Run simulation again? (Y=Yes): ");
            more = conIn.nextLine();
            if (!more.equalsIgnoreCase("y")) {
                System.out.println("Program Finished.");
            }
            System.out.println();
        }
        while (more.equalsIgnoreCase("y"));

    } 

    public static String getResults()
    {
        setAll();
        //the average harbor time per ship
        avgHarTime = (totalHarTime / numOfShips);
        //the average time a ship had to wait to unload its cargo
        avgWaitTime = (totalWaitTime / numOfShips);
        //the percentage of time the harbor was idle
        avgIdleTime = (float)(totalIdleTime / totalTime) * 100.0;
        perIdleTime = (avgIdleTime);
        result = ("\nTotal time ships were in the harbor: " + totalHarTime +" minutes \n" +
                  "Average harbor time per ship: " + avgHarTime + " minutes \n" +
                  "Maximum harbor time per ship: " + maxHarTime + " minutes \n" + 
                  "Total time ships had to wait to unload: " + totalWaitTime + " minutes \n" +
                  "Average wait time per ship: " + avgWaitTime + " minutes \n" +
                  "Maximum wait time per ship: " + maxWaitTime + " minutes \n" + 
                  "Total idle time in the harbor: " + totalIdleTime + " minutes \n" +
                  "Percentage of time idle: " + perIdleTime + "%\n" +
                  "Total simulation time: " + totalTime + " minutes \n");
                  
        return result;
    }

    public static void setAll()
    {
        //hold the ships
        array = new Ship[numOfShips];
        double wait = 0.0;
        for (int i = 0; i < numOfShips; i++) {
            Ship ship = new Ship();
            array[i] = ship;
            if(i == 0) {
                //sets the variables for the first ship in the harbor
                int arrival = ship.getBetween();
                ship.setArrive(0);
                ship.setStartUnload(arrival);
                ship.setShipWait(0);
                ship.setFinishUnload();
                ship.setHarborTime();
                double har = ship.getHarborTime();
                totalIdleTime = arrival;
                maxHarTime = har;
                totalHarTime = har;
                wait = ship.getShipWait();
                maxWaitTime = wait;
                totalWaitTime = wait;
            }
            else {
                //sets the variables for all other ships
                double prevShipArrive = array[i-1].getArrive();
                double prevShipFinish = array[i-1].getFinishUnload();
                ship.setArrive(prevShipArrive);
                ship.setStartUnload(prevShipFinish);
                ship.setFinishUnload();
                ship.setHarborTime();
                double har = ship.getHarborTime();
                if (har > maxHarTime) {
                    maxHarTime = har;
                }
                totalHarTime += har;
                double idle = ship.getArrive() - prevShipFinish;
                if (idle >= 0) {
                    //if there is a nonnegative difference in a ship's arrival and the previous ship's departure
                    //the harbor was idle
                    totalIdleTime += idle;
                } else {
                    wait = -idle;
                }
                ship.setShipWait(wait);
                
                if (wait > maxWaitTime) {
                    maxWaitTime = wait;
                }
                totalWaitTime += wait;
                
            }
            
            if( i == (numOfShips - 1)) {
               totalTime = ship.getFinishUnload();
            }

        }

    }
}
