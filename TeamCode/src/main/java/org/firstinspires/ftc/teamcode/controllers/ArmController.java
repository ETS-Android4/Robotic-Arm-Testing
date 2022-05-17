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

        // Add the turntable motor
        dcMotorList[0] = hardwareMap.get(DcMotor.class, "motor5");
    }

    public void adjustX(int ticks) {

    }

    /**
     * Handle the
     * @param amount
     */
    public void adjustY(double amount) {

    }

    /**
     * Handle the up and down motion of the arm
     * @param amount
     */
    public void adjustZ(double amount) {

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
