package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.controllers.ArmController;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="A - Main TeleOp")
public class TeleOp extends OpMode {

    /**
     * Drive Train Motors
     * @lf Index 0
     * @lb Index 1
     * @rf Index 2
     * @rb Index 3
     */
    private DcMotor[] driveTrain = new DcMotor[4];

    /**
     * Turntable Motor
     */
    private DcMotor turntableMotor = null;

    /**
     * To handle the arm movement
     */
    private ArmController armController;

    @Override
    public void init() {
        armController = new ArmController(hardwareMap);
        // Initialize the drivetrain motors
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

        // Get the turntable motor from the arm controller
        turntableMotor = armController.getDcMotorList()[0];
    }



    @Override
    public void loop() {
        // Handle the arm rotation and extension
        if(gamepad1.left_bumper) {
            armController.adjustX(gamepad1.left_stick_x);
            armController.adjustY(gamepad1.left_stick_y);
            armController.adjustZ(gamepad1.right_stick_y);
            freezeDriving();
        }
        else {
            armController.adjustX(0);
        }
        if(gamepad1.a)
        {
            armController.spinIntake();
        }
        else if(gamepad1.b) {
            armController.reverseIntake();
        }
        else {
            armController.stopIntake();
        }
        if(!gamepad1.left_bumper) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double lfPower = (y + x + rx) / denominator;
            double lbPower = (y - x + rx) / denominator;
            double rfPower = (y - x - rx) / denominator;
            double rbPower = (y + x - rx) / denominator;

            driveTrain[0].setPower(lfPower);
            driveTrain[1].setPower(lbPower);
            driveTrain[2].setPower(rfPower);
            driveTrain[3].setPower(rbPower);
        }


        telemetry.addData("Turntable Encoder Pos: ", turntableMotor.getCurrentPosition());
    }

    /**
     * Stops the driving by setting all motor powers to o
     */
    public void freezeDriving()
    {
        for(DcMotor motor : driveTrain) {
            motor.setPower(0);
        }
    }
}
