package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.controllers.ArmController;

@TeleOp
public class DebugArtic1_2 extends LinearOpMode {
    private boolean qUp, qDown;
    private boolean qL, qR;
    private boolean qA;
    private boolean qB;
    private DcMotor[] driveTrain = new DcMotor[4];
    private boolean qX;
    private boolean qY;

    @Override
    public void runOpMode() throws InterruptedException {
        Servo artic = hardwareMap.get(Servo.class,"artic1");
        Servo artic2 = hardwareMap.get(Servo.class,"artic2");
        CRServo spinner = hardwareMap.get(CRServo.class,"spinner");
        ArmController armController = new ArmController(hardwareMap);
        double pos = artic.getPosition();
        double pos2 = artic2.getPosition();
        driveTrain[0] = hardwareMap.get(DcMotor.class, "lf");
        driveTrain[1] = hardwareMap.get(DcMotor.class, "lb");
        driveTrain[2] = hardwareMap.get(DcMotor.class, "rf");
        driveTrain[3] = hardwareMap.get(DcMotor.class, "rb");

        for(int i = 0; i < driveTrain.length; i++) {
            driveTrain[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            driveTrain[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            driveTrain[i].setPower(0);
            if(i == 0 || i == 3)
            {
                driveTrain[i].setDirection(DcMotorSimple.Direction.REVERSE);
            }
        }
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
            if (gamepad1.a && !qA) {
                qA = true;
                pos = 0.32;
                pos2 = 0.99;
                artic.setPosition(pos);
                artic2.setPosition(pos2);
            } else if (!gamepad1.a && qA) {
                qA = false;
            }
            if (gamepad1.b && !qB) {
                qB = true;
                pos = .28;
                pos2 = 0.3;
                artic.setPosition(pos);
                artic2.setPosition(pos2);
            } else if (!gamepad1.a && qB) {
                qB = false;
            }
            if (gamepad1.y && !qY) {
                qY = true;
                pos = .5;
                pos2 = 0.3;
                artic.setPosition(pos);
                artic2.setPosition(pos2);
            } else if (!gamepad1.y && qY)
            {
                qY = false;
            }
            if (gamepad1.x && !qX) {
                qX = true;
                spinner.setPower(spinner.getPower() == 1 ? 0 : 1);
            } else if (qX && !gamepad1.x)
            {
                qX = false;
            }
            if(gamepad1.right_bumper) {
                armController.adjustX(gamepad1.left_stick_x);
            }
            else {
                double y = -gamepad1.left_stick_y;
                double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                double rx = -gamepad1.right_stick_x;

                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                double lfPower =(y + x + rx) / denominator;
                double lbPower = (y - x + rx) / denominator*1.3;
                double rfPower = (y - x - rx) / denominator;
                double rbPower = (y + x - rx) / denominator;

                driveTrain[0].setPower(lfPower);
                driveTrain[1].setPower(lbPower);
                driveTrain[2].setPower(rfPower);
                driveTrain[3].setPower(rbPower);
            }



            telemetry.addData("Pos: ", pos);
            telemetry.addData("Pos2: ", pos2);
            telemetry.update();
        }
    }
}
