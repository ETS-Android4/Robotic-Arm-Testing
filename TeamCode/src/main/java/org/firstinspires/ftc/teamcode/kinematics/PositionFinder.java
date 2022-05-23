package org.firstinspires.ftc.teamcode.kinematics;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.ArmConfiguration;
import org.firstinspires.ftc.teamcode.config.ServoPositions;
import org.firstinspires.ftc.teamcode.kinematics.coords.Coordinate2D;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Config
public class PositionFinder {

    public static double angleOffset = 58.5;
    /**
     * Calculate the angle created between the first servo and the base of the bot
     * @param position the current position of the servo
     * @return the final position of the joint
     */
    public static Coordinate2D calcSecondJointPosition(double position, Telemetry telemetry) {
        // Find first angle
        double ticksPerDegree = 0.00555555555555555555555555555556;



        double internalAngle = 0;
        if(position == 0) {
            internalAngle = 59.5;
        } else {
            internalAngle = Math.abs((position / ticksPerDegree) + angleOffset);
        }

        // Calculate the hypotenuse for the right triangle
        double bSqrd = Math.pow(ArmConfiguration.armBeamLength, 2);
        double aSqrd = Math.pow(ArmConfiguration.baseBeamLength, 2);
        double rest = 2 * ArmConfiguration.armBeamLength * ArmConfiguration.robotBaseToServo1Length * Math.cos(internalAngle);
        double cCalc = Math.sqrt(bSqrd + aSqrd - rest);
        double c = cCalc;

        telemetry.addData("ANGLE", internalAngle);
        telemetry.addData("C", c);


        return new Coordinate2D(1, 1);
    }

    /**
     * Calculates the internal angle of the Savox servo
     * @param position the savox servo position
     * @return the internal angle that is calculated
     */
    public static double calcServoOneAngle(double position) {
        // Find first angle
        double ticksPerDegree = 0.00555555555555555555555555555556;

        double angleOffset = 58.5;

        double internalAngle = 0;
        if(position == 0) {
            internalAngle = 59.5;
        } else {
            internalAngle = Math.abs((position / ticksPerDegree) + angleOffset);
        }
        return internalAngle;
    }

    /**
     * Calculate the third joint position
     * @param position1 the position of servo1
     * @param position2 the position of servo2
     * @return a new coordinate object of the third joint
     */
    public static Coordinate2D calcThirdJointPosition(double position1, double position2) {
        // Calculate the secondary joint to help find the third
        return null;
    }



}
