public class OnBoardComputer implements BurnStream {

    @Override
    public int getNextBurn(DescentEvent status) {
        int burn = 0;

        double slideyScale;
        if (status.getAltitude() > 10000){
            slideyScale = 0.05;
        } else if (status.getAltitude() > 1000) {
            slideyScale = 0.15;
        }else if (status.getAltitude() > 10) {
            slideyScale = 0.4;
        } else {
            slideyScale = 0.2;
        }

        int targetVelo = (int) Math.ceil(status.getAltitude() * slideyScale);
        burn = (status.getVelocity() - targetVelo) + 100;
        burn = Math.min(200, burn);
        burn = Math.max(0, burn);

        System.out.println(burn);
        return burn;
    }
}
