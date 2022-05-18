package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DebugSpinner extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        CRServo spinner = hardwareMap.get(CRServo.class,"spinner");
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            if(gamepad1.dpad_up)
            {
                spinner.setPower(1);
            }
            else if(gamepad1.dpad_down)
            {
                spinner.setPower(-1);
            }
            else
            {
                spinner.setPower(0);
            }
            telemetry.addData("Power: ", spinner.getPower());
            telemetry.update();
        }
    }
}
