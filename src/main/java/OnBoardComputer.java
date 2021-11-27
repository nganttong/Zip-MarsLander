public class OnBoardComputer implements BurnStream {

    @Override
    public int getNextBurn(DescentEvent status) {
        int burn = 0;

//        int velo = status.getVelocity();
//        int alt = status.getAltitude();
//        int targetAlt = 4601;
//        int remainder = (alt - 1)%100;
//        if (alt > targetAlt && velo == 1000) {
//           if ((alt - 501)%1000 == 0){
//               burn = 100;
//           } else if ((alt - 1)%100 == 0){
//               burn = 200;
//           } else {
//               burn = 200 - remainder;
//           }
//        } else if(alt == (targetAlt + 900) && velo == 900) {
//            burn = 0;
//        } else if(velo < 1000 && alt > targetAlt && velo%100 == 0){
//            burn = 100;
//        } else if (alt > targetAlt){
//            burn = 100 + (velo%100);
//        } else {
//            if (alt == 1 && velo == 100){
//                burn = 199;
//            } else {
//                burn = Math.min(100, Math.max(100, 200 + velo));
//            }
//        }
        double slideyScale;
        if (status.getAltitude() > 6000){
            slideyScale = 0.05;
        } else if (status.getAltitude() < 500) {
            slideyScale = 0.5;
        } else {
            slideyScale = 0.1;
        }


        int targetVelo = (int) Math.ceil(status.getAltitude() * slideyScale);
        burn = (status.getVelocity() - targetVelo) + 100;
        burn = Math.min(200, burn);
        burn = Math.max(0, burn);

        System.out.println(burn); /*hack!*/
        return burn;
    }
}
//        V       A       B
//
//        1000    4501    200
//        900     3601    200
//        800     2801    200
//        700     2101    200
//        600     1501    200
//        500     1001    200
//        400     601     200
//        300     301     200
//        200     101     200
//        100     1       199
//        1       0       0