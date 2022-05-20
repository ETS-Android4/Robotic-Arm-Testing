package org.firstinspires.ftc.teamcode.config;

public class ArmConfiguration {

    /**
     * TurnTable Power
     * @usage This power is set when the DcMotor is told to run to a certain tick
     */
    public static double TurntableRunToPositionPower = 0.8;

    /**
     * Servo Speed
     * @usage This number divides the given gamepad position and returns the number (used for gradient speeds)
     * @info The servo gradient is 0 - 1 (0 degrees to 180)
     * @info A degree to a gradient number is 1 / 180 so 0.0055555555555556 ticks per degree
     * @example 0.85 / 250 = 0.0034, whereas 1 / 250 = 0.004
     * @info So by using a higher value, we are able to scale the servo speed up and down
     */
    public static int ServoSpeedScalar = 250;

    /**
     * The length of the beam that is holding the arm up
     * @unit MILLIMETERS
     */
    public static double baseBeamLength = 125;

    /**
     * The length of the arm that is connected to the first joint of servo1
     * @unit MILLIMETERS
     */
    public static double armBeamLength = 312.42;

    /**
     * The length of the bucket arm that is joined to the first joint of servo2
     * @unit MILLIMETERS
     */
    public static double thirdArmLength = 120;

    public static double robotBaseToServo1Length = 98;
}
