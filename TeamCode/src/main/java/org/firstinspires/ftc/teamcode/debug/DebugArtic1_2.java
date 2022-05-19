package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DebugArtic1_2 extends LinearOpMode {
    private boolean qUp, qDown;
    private boolean qL, qR;
    private boolean qA;
    private boolean qB;

    @Override
    public void runOpMode() throws InterruptedException {
        Servo artic = hardwareMap.get(Servo.class,"artic1");
        Servo artic2 = hardwareMap.get(Servo.class,"artic2");
        CRServo spinner = hardwareMap.get(CRServo.class,"spinner");

        double pos = artic.getPosition();
        double pos2 = artic2.getPosition();
        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            if (gamepad1.dpad_up && !qUp) {
                qUp = true;
                if (pos <= 0.9) {
                    pos += 0.1;
                }
                artic.setPosition(pos);
            } else if (!gamepad1.dpad_up && qUp) {
                qUp = false;
            }
            if (gamepad1.dpad_down && !qDown) {
                qDown = true;
                if (pos >= 0.1) {
                    pos -= 0.1;
                }
                artic.setPosition(pos);
            } else if (!gamepad1.dpad_down && qDown) {
                qDown = false;
            }
            if (gamepad1.dpad_right && !qR) {
                qR = true;
                if (pos2 <= 0.9) {
                    pos2 += 0.1;
                }
                artic2.setPosition(pos2);
            } else if (!gamepad1.dpad_right && qR) {
                qR = false;
            }
            if (gamepad1.dpad_left && !qL) {
                qL = true;
                if (pos2 >= 0.1) {
                    pos2 -= 0.1;
                }
                artic2.setPosition(pos2);
            } else if (!gamepad1.dpad_left && qL) {
                qL = false;
            }
            if (gamepad1.a && !qA)
            {
                qA = true;
                pos = 0.1;
                pos2 = 0.2;
                artic.setPosition(pos);
                artic2.setPosition(pos2);
                spinner.setPower(0);
            }
            else if(!gamepad1.a && qA)
            {
                qA = false;
            }
            if (gamepad1.b && !qB)
            {
                qB = true;
                pos = 0.05;
                pos2 = 0.7;
                artic.setPosition(pos);
                artic2.setPosition(pos2);
                spinner.setPower(1);
            }
            else if(!gamepad1.a && qB)
            {
                qB = false;
            }
            if(gamepad1.y)
            {
                spinner.setPower(0);
            }
            telemetry.addData("Pos: ", pos);
            telemetry.addData("Pos2: ", pos2);
            telemetry.update();
        }
    }
}
