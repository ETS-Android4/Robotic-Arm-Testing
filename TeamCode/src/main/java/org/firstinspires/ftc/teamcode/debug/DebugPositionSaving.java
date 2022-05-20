package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.controllers.ArmController;
import org.firstinspires.ftc.teamcode.controllers.PositionController;
import org.firstinspires.ftc.teamcode.kinematics.Position;
import org.firstinspires.ftc.teamcode.kinematics.PositionFinder;
import org.firstinspires.ftc.teamcode.kinematics.coords.Coordinate2D;

import java.util.HashMap;
import java.util.Map;

@TeleOp(name="A - Debug Position Saving")
public class DebugPositionSaving extends LinearOpMode {

    /**
     * To handle the arm movement
     */
    private ArmController armController;

    // Testing
    private HashMap<String, Position> positionMap = new HashMap<>();
    private String namingScheme = "pos";
    private int numSavedPositions = 0;

    // Naming
    private String className = "[Debug Position Saving]";

    // Internal
    private double initialPressTimestamp = 0;

    // Gamepad
    private boolean pressingX = false;
    private String xPositionBind = null;

    private boolean pressingA = false;
    private String aPositionBind = null;

    private boolean pressingB = false;

    @Override
    public void runOpMode() throws InterruptedException {
        armController = new ArmController(hardwareMap);

        telemetry.addData(className, "Ready...");
        telemetry.update();
        waitForStart();
        telemetry.clear();
        telemetry.update();

        while(!isStopRequested() && opModeIsActive()) {
            if(gamepad1.left_bumper) {
                armController.adjustX(gamepad1.left_stick_x);
                armController.adjustY(gamepad1.left_stick_y);
                armController.adjustZ(gamepad1.right_stick_y);
            } else {
                if(!armController.getDcMotorList()[0].isBusy()) {
                    armController.getDcMotorList()[0].setPower(0);
                }
            }


            // Handle setting logic (X)
            if(gamepad1.x && !pressingX) {
                initialPressTimestamp = getRuntime();
                pressingX = true;
            } else if(!gamepad1.x && pressingX) {
                initialPressTimestamp = 0;
                if(xPositionBind != null) {
                    goToPositions(armController, positionMap.get(xPositionBind));
                }
                pressingX = false;
            } else if(gamepad1.x && pressingX) {
                double diff = getRuntime() - initialPressTimestamp;
                if(diff >= 3) {
                    gamepad1.rumble(1000);
                    xPositionBind = savePosition();
                    initialPressTimestamp = 0;
                    pressingX = false;
                }
            }

            // Handle setting logic (A)
            if(gamepad1.a && !pressingA) {
                initialPressTimestamp = getRuntime();
                pressingA = true;
            } else if(!gamepad1.a && pressingA) {
                initialPressTimestamp = 0;
                if(aPositionBind != null) {
                    goToPositions(armController, positionMap.get(aPositionBind));
                }
                pressingA = false;
            } else if(gamepad1.a && pressingA) {
                double diff = getRuntime() - initialPressTimestamp;
                if(diff >= 3) {
                    gamepad1.rumble(1000);
                    aPositionBind = savePosition();
                    initialPressTimestamp = 0;
                    pressingA = false;
                }
            }

            // Handle deleting logic (B)
            if(gamepad1.b && !pressingB) {
                initialPressTimestamp = 0;
                pressingB = true;
            } else if(!gamepad1.b && pressingB) {
                pressingB = false;
            } else if(gamepad1.b && pressingB) {
                double diff = getRuntime() - initialPressTimestamp;
                if(diff >= 3) {
                    gamepad1.rumble(1000);
                    for (Map.Entry element : positionMap.entrySet()) {
                        positionMap.remove(element.getKey());
                    }
                }
            }

            // Print positions to telemetry
            telemetry.addData("Turntable Position: ", armController.getDcMotorList()[0].getCurrentPosition());
            telemetry.addData("servo1 Position: ", armController.getServoList()[0].getPosition());
            telemetry.addData("servo2 Position: ", armController.getServoList()[1].getPosition());
            telemetry.addLine();

            // Print testing stuff to telemetry
            telemetry.addLine("Joint Two Position: ");
            if(armController.getServoList()[0].getPosition() != 0) {
                Coordinate2D pos = PositionFinder.calcSecondJointPosition(armController.getServoList()[0].getPosition(), telemetry);
                telemetry.addData("     x: ", pos.getX());
                telemetry.addData("     y: ", pos.getY());
            }

            telemetry.addLine();

            // Print saved positions
            telemetry.addData("Number of saved positions: ", numSavedPositions);
            for (Map.Entry mapElement : positionMap.entrySet()) {
                telemetry.addData("Position Name: ", mapElement.getKey());
                telemetry.addData("     Turntable Position: ", positionMap.get(mapElement.getKey()).getTurntable_pos());
            }

            // Update telemetry
            telemetry.update();
        }


    }

    /**
     * Saves the position to a position map
     */
    private String savePosition() {
        numSavedPositions++;
        positionMap.put(namingScheme + String.valueOf(numSavedPositions), PositionController.getPositions(this.armController));
        return namingScheme + String.valueOf(numSavedPositions);
    }

    /**
     * Makes the servos and motors go to these positions
     * @param position
     */
    private void goToPositions(ArmController armcontroller, Position position) {
        armcontroller.getServoList()[0].setPosition(position.getServo1_pos());
        armcontroller.getServoList()[1].setPosition(position.getServo2_pos());
        armcontroller.motorRunTo(position.getTurntable_pos());
    }
}
