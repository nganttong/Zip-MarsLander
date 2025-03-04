public class DescentEvent {
    int Seconds = 0;
    int Velocity = 0;
    int Fuel = 0;
    int Altitude = 0;
    int Status = 0;

    public DescentEvent(int t, int sp, int f, int h, int st) {
        this.Seconds = t * 10;
        this.Velocity = sp;
        this.Fuel = f;
        this.Altitude = h;
        this.Status = st;
    }

    public int getVelocity() {
        return this.Velocity;
    }

    public int getAltitude() {
        return this.Altitude;
    }
    public int getStatus() { return this.Status; }
    @Override
    public String toString() {
        String s = Simulation.betterLookingFormat(this.Seconds)
                + Simulation.betterLookingFormat(this.Velocity)
                + Simulation.betterLookingFormat(this.Fuel)
                + Simulation.betterLookingFormat(this.Altitude);
        return s;
    }
}
