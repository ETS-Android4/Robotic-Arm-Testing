package org.firstinspires.ftc.teamcode.controllers;

import android.util.Log;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.ServoPositions;

public class ArmController {

    /**
     * Servo List
     * @servo1 Index 0
     * @servo2 Index 1
     * @servo3 Index 2
     */
    private Servo[] servoList =  new Servo[3];
    private CRServo ejector;

    /**
     * DcMotor List
     * @motor1 Index 0
     */
    private DcMotor[] dcMotorList = new DcMotor[1];

    /**
     * Classname within the debug log
     */
    private String className = "[Arm Controller]";
    private int callcount = 0; // the amount of times that this class' functions were called
    private int adjustxCallCount = 0; // the amount of times that call count was updated
    private int adjustyCallCount = 0; // ''
    private int adjustzCallCount = 0; // ''
    private final boolean debug = false;


    public ArmController(HardwareMap hardwareMap) {
        if(hardwareMap == null) { return; }
        callcount++;

        // Initialize and set the servos to their default positions
        servoList[0] = hardwareMap.get(Servo.class, "artic1");
        servoList[1] = hardwareMap.get(Servo.class, "artic2");
        servoList[2] = hardwareMap.get(Servo.class, "artic3");
        ejector = hardwareMap.get(CRServo.class,"spinner");
        // TODO: Add this back to make sure the arm starts inside the robot
        resetServoPositions();

        // Add the turntable motor and set its Zero power to brake
        // TODO: Make sure that the motor spins the right way relative to the robot
        dcMotorList[0] = hardwareMap.get(DcMotor.class, "turntable");
        // dcMotorList[0].setDirection(DcMotorSimple.Direction.REVERSE);
        dcMotorList[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Spin the intake with positive power (hopefully forwards)
     */
    public void spinIntake() {
        callcount++;
        ejector.setPower(1);
    }

    /**
     * Spin the intake with 0 power (hopefully it stops)
     */
    public void stopIntake() {
        callcount++;
        ejector.setPower(0);
    }

    /**
     * Reverse the intake (spin the intake with negative power, hopefully backwards)
     */
    public void reverseIntake() {
        ejector.setPower(-1);
    }

    /**
     * Rotate the turn table
     * @param x power to set
     */
    public void adjustX(double x) {
        dcMotorList[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dcMotorList[0].setPower(x);
    }


    /**
     * Handle the up and down motion of the arm
     * @param y
     * @max 0.95
     * @min 0.25
     */
    public void adjustY(double y) {
        // flip the values so up is up and down is down
        y = -y;
        if(y != 0) {
            double calcTicks = (y + 0.01) / 250;
            if(servoList[0].getPosition() + calcTicks < ServoPositions.servo1_Maximum || servoList[0].getPosition() + calcTicks > ServoPositions.servo1_Minimum) {
                servoList[0].setPosition(servoList[0].getPosition() + (calcTicks));
            } else {
                if(calcTicks + servoList[0].getPosition() > ServoPositions.servo1_Maximum) {
                    Log.d(className, "Could not adjust servo1 because it's next position was above " + ServoPositions.servo1_Maximum);
                } else if(calcTicks + servoList[0].getPosition() < ServoPositions.servo1_Minimum) {
                    Log.d(className, "Could not adjust servo1 because it's next position was below " + ServoPositions.servo1_Minimum);
                }
            }
        }
    }

    /**
     * Handle the extension and retraction swinging motion
     * @param z
     * @max
     * @min
     */
    public void adjustZ(double z) {
        // z = -z;
        if(z != 0) {
            double calcTicks = (z + 0.01) / 250;
            if(servoList[1].getPosition() + calcTicks < ServoPositions.servo2_Maximum || servoList[1].getPosition() + calcTicks > ServoPositions.servo2_Minimum) {
                servoList[1].setPosition(servoList[1].getPosition() + (calcTicks));
            } else {
                if(calcTicks + servoList[1].getPosition() > ServoPositions.servo2_Maximum) {
                    Log.d(className, "Could not adjust servo2 because it's next position was above " + ServoPositions.servo2_Maximum);
                } else if(calcTicks + servoList[1].getPosition() < ServoPositions.servo2_Minimum) {
                    Log.d(className, "Could not adjust servo2 because it's next position was below " + ServoPositions.servo2_Minimum);
                }
            }
        }
    }


    /**
     * Makes the motor run to a certain encoder position
     */
    public void motorRunTo() {

    }

    /**
     * Makes a motor run with a certain amount of ticks
     */
    public void motorRunWith() {


    }

    /**
     * Set the servos to their default positions
     */
    public void resetServoPositions() {
        servoList[0].setPosition(ServoPositions.servo1_Default);
        servoList[1].setPosition(ServoPositions.servo2_Default);
        // servoList[2].setPosition(ServoPositions.servo3_Default);
    }

    /**
     * Getter
     */
    public DcMotor[] getDcMotorList() {
        return this.dcMotorList;
    }


}
