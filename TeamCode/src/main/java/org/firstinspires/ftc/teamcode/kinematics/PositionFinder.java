package org.firstinspires.ftc.teamcode.kinematics;

import org.firstinspires.ftc.teamcode.config.ArmConfiguration;
import org.firstinspires.ftc.teamcode.config.ServoPositions;
import org.firstinspires.ftc.teamcode.kinematics.coords.Coordinate2D;

public class PositionFinder {

    /**
     * Calculate the angle created between the first servo and the base of the bot
     * @param position the current position of the servo
     * @return the final position of the joint
     */
    public static Coordinate2D calcSecondJointPosition(double position) {
        // Find the first angle
        double ticksPerDegree = 1 / 180;
        double minAngleDegrees = ServoPositions.servo1_Minimum * ticksPerDegree;
        double internalAngle = Math.abs((position * ticksPerDegree) - minAngleDegrees);

        // Calculate the hypotenuse for the right triangle
        double bSqrd = Math.pow(ArmConfiguration.armBeamLength, 2);
        double aSqrd = Math.pow(ArmConfiguration.baseBeamLength, 2);
        double rest = 2 * ArmConfiguration.armBeamLength * ArmConfiguration.baseBeamLength * Math.cos(internalAngle);
        double cCalc = Math.sqrt(bSqrd + aSqrd - rest);
        double c = cCalc;

        // Calculate <B of the triangle
        double cSqrd = Math.pow(c, 2);
        double top = cSqrd + aSqrd + bSqrd;
        double bottom = 2 * c * ArmConfiguration.baseBeamLength;
        double triangleAngB = top / bottom;

        // Calculate the right triangle
        double angleA = 90 - triangleAngB;
        double height = c * Math.sin(angleA);
        double base = Math.sqrt(cSqrd - Math.pow(height, 2));

        return new Coordinate2D(height, base);
    }

    /**
     * Calculate the third joint position
     * @param position1 the position of servo1
     * @param position2 the position of servo2
     * @return a new coordinate object of the third joint
     */
    public static Coordinate2D calcThirdJointPosition(double position1, double position2) {
        double ticksPerDegree = 1 / 180;
        // Calculate the secondary joint to help find the third
        Coordinate2D secondJointPosition = calcSecondJointPosition(position1);

        // Find little a in the right triangle
        double angleA = Math.abs(position2 * ticksPerDegree) - 180;

        // Find the rest of the triangle
        double height = ArmConfiguration.thirdArmLength * Math.sin(angleA);
        double aSqrd = Math.pow(height, 2);
        double cSqrd = Math.pow(ArmConfiguration.thirdArmLength, 2);
        double base = Math.sqrt(cSqrd - aSqrd);
        Coordinate2D tempPosition = new Coordinate2D(height, base);

        // Final calculations
        return secondJointPosition.add(tempPosition);


    }



}
