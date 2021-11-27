public class Vehicle {

    public Vehicle(int initialAltitude) {
        // initialize the altitude AND previous altitude to initialAltitude
        this.altitude = initialAltitude;
        this.prevAltitude = initialAltitude;
    }

    int Gravity = 100;
    /* The rate in which the spaceship descents in free fall (in ten seconds) */

    // Various end-of-game messages and status result codes.
    String dead = "\nCRASH!!\n\tThere were no survivors.\n\n";
    public static final int DEAD = -3;
    String crashed = "\nThe Starship crashed. Good luck getting back home. Elon is pissed.\n\n";
    public static final int CRASHED = -2;
    String emptyfuel = "\nThere is no fuel left. You're floating around like Major Tom.\n\n";
    public static final int EMPTYFUEL = -1;
    String success = "\nYou made it! Good job!\n\n";
    public static final int SUCCESS = 0;
    public static final int FLYING = 1;

    // this is initial vehicle setup
    int altitude = 8000;
    int prevAltitude = 8000;

    int Velocity= 1000;
    int Fuel = 12000;
    int Burn = 0;
    int Flying = FLYING;

    public Vehicle() {}

    public String checkFinalStatus() {
        String s = "";
        if (this.altitude <= 0) {
            if (this.Velocity > 10) {
                s = dead;
                Flying = DEAD;
            }
            if (this.Velocity < 10 && this.Velocity > 3) {
                s = crashed;
                Flying = CRASHED;
            }
            if (this.Velocity < 3) {
                s = success;
                Flying = SUCCESS;
            }
        } else {
            s = emptyfuel;
            Flying = EMPTYFUEL;
        }
        return s;
    }

    public int computeDeltaV() {
        // return velocity + gravity - burn amount
        return Velocity + Gravity - Burn;
    }

    public void adjustForBurn(int burnAmount) {
        // set burn to burnAmount requested
        this.Burn = burnAmount;
        // save previousAltitude with current Altitude
        this.prevAltitude = altitude;
        // set new velocity to result of computeDeltaV function.
        this.Velocity = computeDeltaV();
        // subtract speed from Altitude
        this.altitude = this.altitude - this.Velocity;
        // subtract burn amount fuel used from tank
        this.Fuel = this.Fuel - this.Burn;
    }

    public boolean stillFlying() {
        // return true if altitude is positive
        return altitude > 0;
    }
    public boolean outOfFuel() {
        // return true if fuel is less than or equal to zero
        return Fuel <= 0;
    }

    public DescentEvent getStatus(int tick) {
        // create a return a new DescentEvent object
        // filled in with the state of the vehicle.
        return new DescentEvent(tick, this.Velocity, this.Fuel, this.altitude, statusSomething());
    }

    public int statusSomething(){
        if (stillFlying()){
            return FLYING;
        } else if (3 <= this.Velocity && this.Velocity < 10){
            return CRASHED;
        } else if (this.Velocity >= 10){
            return DEAD;
        }else {
            return SUCCESS;
        }
    }
}
