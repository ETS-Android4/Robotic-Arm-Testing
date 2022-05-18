package org.firstinspires.ftc.teamcode.debug;

import android.graphics.LinearGradient;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DebugArtic1 extends LinearOpMode {
    private boolean qUp, qDown;

    @Override
    public void runOpMode() throws InterruptedException {
        Servo artic = hardwareMap.get(Servo.class,"artic1");
        double pos = artic.getPosition();
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            if(gamepad1.dpad_up && !qUp)
            {
                qUp = true;
                if(pos <=0.9) {
                    pos+=0.1;
                }
                artic.setPosition(pos);
            }
            else if(!gamepad1.dpad_up && qUp)
            {
                qUp = false;
            }
            if(gamepad1.dpad_down && !qDown)
            {
                qDown = true;
                if(pos >=0.1) {
                    pos-=0.1;
                }
                artic.setPosition(pos);
            }
            else if(!gamepad1.dpad_down && qDown)
            {
                qDown = false;
            }
            telemetry.addData("Pos: ", pos);
            telemetry.update();
        }
    }
}
