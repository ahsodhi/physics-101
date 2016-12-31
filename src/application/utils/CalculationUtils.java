package application.utils;

import application.structures.Force;
import application.structures.Vector;

import java.util.ArrayList;

/**
 * Created by:  Anirudh Sodhi
 * Date:        2016-01-14
 * File:        ${FILE_NAME}
 * Description:
 */
public class CalculationUtils {

    //region Vectors
    public static double getXComponent(double magnitude, double angle) {
        return Math.cos(Math.toRadians(angle)) * magnitude;
    }

    public static double getYComponent(double magnitude, double angle) {
        return Math.sin(Math.toRadians(angle)) * magnitude;
    }

    public static double getSumMagnitude(Vector[] vectors) {
        double x = getSumX(vectors);
        double y = getSumY(vectors);

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static double getSumAngle(Vector[] vectors) {
        double x = getSumX(vectors);
        double y = getSumY(vectors);

        return Math.toDegrees(Math.atan(y / x));
    }

    public static double getSumX(Vector[] vectors) {
        double x = 0;

        for (Vector v : vectors)
            x += getXComponent(v.getMagnitude(), v.getAngle());

        return x;
    }

    public static double getSumY(Vector[] vectors) {
        double y = 0;

        for (Vector v : vectors)
            y += getYComponent(v.getMagnitude(), v.getAngle());

        return y;
    }
    //endregion

    //region Forces
    public static int netForceX(ArrayList<Force> forces){
        double x = 0;
        for (int i = 0; i < forces.size(); i++){
            x += forces.get(i).getEndX();
        }
        return (int) x;
    }

    public static int netForceY (ArrayList<Force> forces){
        double y = 0;
        for (int i = 0; i < forces.size(); i++){
            y += forces.get(i).getEndY();
        }
        return (int) y;
    }

    public static int netForceAngle (ArrayList<Force> forces){
        int x = netForceX(forces);
        int y = netForceY(forces);

        if (x == 0 || y == 0)
            return 0;

        return (int) Math.toDegrees(Math.atan(y/x));
    }

    public static double netForce (ArrayList<Force> forces){
        return Math.sqrt(Math.pow(netForceX(forces), 2) + Math.pow(netForceY(forces), 2));
    }

    public static double energy (ArrayList<Force> forces, double distance){
        return netForce(forces) * distance;
    }
    //endregion

    @SuppressWarnings("SameParameterValue")
    public static double round(double num, int decimals) {
        return Math.round(num * Math.pow(10, decimals)) / Math.pow(10, decimals);
    }
}
