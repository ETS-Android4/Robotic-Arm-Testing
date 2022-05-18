package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DebugTurntable extends LinearOpMode {
    private boolean qUp, qDown;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor turntable = hardwareMap.get(DcMotor.class,"turntable");
        turntable.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turntable.setTargetPosition(turntable.getCurrentPosition());
        turntable.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turntable.setPower(0);
        int pos = turntable.getCurrentPosition();
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            if(gamepad1.dpad_up && !qUp)
            {
                qUp = true;
                pos+=50;
                turntable.setTargetPosition(pos);
                turntable.setPower(0.5);
            }
            else if(!gamepad1.dpad_up && qUp)
            {
                qUp = false;
            }
            if(gamepad1.dpad_down && !qDown)
            {
                qDown = true;
                pos-=50;
                turntable.setTargetPosition(pos);
                turntable.setPower(0.5);
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
