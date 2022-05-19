package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DebugArtic3 extends LinearOpMode {
    private boolean qUp, qDown;

    @Override
    public void runOpMode() throws InterruptedException {
        CRServo artic = hardwareMap.get(CRServo.class,"artic3");
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            if(gamepad1.dpad_up && !qUp)
            {
                artic.setPower(1);
                qUp = true;
            }
            else if(!gamepad1.dpad_up && qUp)
            {
                artic.setPower(0);
                qUp = false;
            }

            if(gamepad1.dpad_down && !qDown)
            {
                artic.setPower(-1);
                qDown = true;
            }
            else if(!gamepad1.dpad_down && qDown)
            {
                artic.setPower(0);
                qDown = false;

            }
            telemetry.addData("Power: ", artic.getPower());
            telemetry.update();
        }
    }
}
