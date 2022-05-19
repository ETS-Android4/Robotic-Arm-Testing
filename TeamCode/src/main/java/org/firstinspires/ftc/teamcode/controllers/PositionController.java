package org.firstinspires.ftc.teamcode.controllers;

import android.util.Log;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.kinematics.Position;

public class PositionController {

    /**
     * Save the current positions to a position object
     * @param armController the arm controller you are using
     * @return the saved position
     */
    public static Position getPositions(ArmController armController) {
        Log.d("[POSITION SET]", String.valueOf(armController.getDcMotorList()[0].getCurrentPosition()));
        return new Position(armController.getServoList()[0].getPosition(), armController.getServoList()[1].getPosition(), armController.getDcMotorList()[0].getCurrentPosition());

    }
}
