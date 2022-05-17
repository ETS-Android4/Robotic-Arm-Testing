package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
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

    /**
     * DcMotor List
     * @motor1 Index 0
     */
    private DcMotor[] dcMotorList = new DcMotor[1];

    public ArmController(HardwareMap hardwareMap) {
        if(hardwareMap == null) { return; }

        // Initialize and set the servos to their default positions
        servoList[0] = hardwareMap.get(Servo.class, "servo1");
        servoList[1] = hardwareMap.get(Servo.class, "servo2");
        servoList[2] = hardwareMap.get(Servo.class, "servo3");
        // TODO: Add this back to make sure the arm starts inside the robot
        // resetServoPositions();

        // Add the turntable motor and set its Zero power to brake
        // TODO: Make sure that the motor spins the right way relative to the robot
        dcMotorList[0] = hardwareMap.get(DcMotor.class, "motor5");
        dcMotorList[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Rotate the turn table
     * @param x
     */
    public void adjustX(double x) {
        dcMotorList[0].setPower(x);
    }

    /**
     * Handle the up and down motion of the arm
     * @param y
     */
    public void adjustY(double y) {
        if(y > 0) {
            // Positive
            double calcTicks = (y + 0.01) * 0.02;
            servoList[0].setPosition(servoList[0].getPosition() + (calcTicks));
        } else {
            // Negative
            double calcTicks = (y - 0.01) * 0.02;
            servoList[0].setPosition(servoList[0].getPosition() - (calcTicks));
        }

    }

    /**
     * Handle the extension and retraction swinging motion
     * @param z
     */
    public void adjustZ(double z) {

    }


    /**
     * Set the servos to their default positions
     */
    public void resetServoPositions() {
        servoList[0].setPosition(ServoPositions.servo1_Default);
        servoList[1].setPosition(ServoPositions.servo2_Default);
        servoList[2].setPosition(ServoPositions.servo3_Default);
    }




}
