public class Simulation {
    private final Vehicle vehicle;
    public static final int digitPadding = 16;

    public Simulation(Vehicle v) {
        this.vehicle = v;
    }
    // Mars Simulation Source Code.
    static String version = "2.0"; /* The Version of the program */

    public static int randomaltitude() {
        int max = 20000;
        int min = 10000;
        int r = (int)(Math.random() * (max - min)) + min;
        return (r % 15000 + 4501); //changes +4000 to 4501 for minimum viable initial altitude
    }


    public String gameHeader() {
        String s = "";
        s = s + "\nMars Simulation - Version " + version + "\n";
        s = s + "Elon Musk has sent a really expensive Starship to land on Mars.\n";
        s = s + "The on-board computer has failed! You have to land the spacecraft manually.\n";
        s = s + "Set burn rate of retro rockets to any value between 0 (free fall) and 200\n";
        s = s + "(maximum burn) kilo per second. Set burn rate every 10 seconds.\n"; /* That's why we have to go with 10 second-steps. */
        s = s + "You must land at a speed of 2 or 1. Good Luck!\n\n";
        return s;
    }

    public String getHeader() {
//        String s = "";
//        s = s + "\nTime\t";
//        s = s + "Velocity\t\t"; s = s + "Fuel\t\t";
//        s = s + "Altitude\t\t"; s = s + "Burn\n";
//        s = s + "----\t";
//        s = s + "-----\t\t";
//        s = s + "----\t\t";
//        s = s + "------\t\t"; s = s + "----\n";
//        return s;
        StringBuilder s = new StringBuilder("\n");
        s.append(Simulation.betterLookingFormat("Time (s)"));
        s.append(Simulation.betterLookingFormat("Velocity (m/s)"));
        s.append(Simulation.betterLookingFormat("Fuel (kg)"));
        s.append(Simulation.betterLookingFormat("Altitude (m)"));
        s.append("Burn\n");
        s.append(Simulation.betterLookingFormat("~~~~"));
        s.append(Simulation.betterLookingFormat("~~~~~"));
        s.append(Simulation.betterLookingFormat("~~~~"));
        s.append(Simulation.betterLookingFormat("~~~~~~"));
        s.append("~~~~\n");
        return s.toString();
    }


    public void printString(String string) {
// print long strings with new lines the them.
    String[] a = string.split("\r?\n");
        for (String s : a) {
            System.out.println(s);
        }
    }

    // main game loop
    public int runSimulation(BurnStream burnSource) {
        DescentEvent status = null;
        int burnInterval = 0;
        printString(gameHeader());
        printString(getHeader());
        while (vehicle.stillFlying()) {
            status = vehicle.getStatus(burnInterval);
            System.out.print(status.toString());
            vehicle.adjustForBurn(burnSource.getNextBurn(status));
            status = vehicle.getStatus(burnInterval); //little janky
            if (vehicle.outOfFuel()) {
                break;
            }
            burnInterval++;
            if (burnInterval % 9 == 0) {
                printString(getHeader());
            }
        }
        printString(vehicle.checkFinalStatus());
        if (status != null) {
            return status.getStatus();
        }
        return -1;
    }

    public static void main(String[] args) {
        // create a new Simulation object with a random starting altitude
        Simulation runGame = new Simulation(new Vehicle(randomaltitude()));
        // create a new BurnInputStream
        BurnInputStream userInput = new BurnInputStream();
        // pass the new BurnInputStream to the runSimulation method
        runGame.runSimulation(userInput);
    }

    public static String betterLookingFormat(String display){
        return String.format("%1$-" + Simulation.digitPadding+ "s", display);
    }

    public static String betterLookingFormat(int display){
        return betterLookingFormat(String.valueOf(display));
    }

}
