package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "Basic: NewOpMode 2", group = "Linear Opmode")
public class NewOpMode extends LinearOpMode{
    private final ElapsedTime runtime = new ElapsedTime();
    private Carousel carousel;
    private Intake intake;

    @Override
    public void runOpMode() {
        // gamepad1 - the driver
        // gamepad2 - the operator
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap);
        Elevator elevator = new Elevator(hardwareMap);
        carousel = new Carousel(hardwareMap);
        intake = new Intake(hardwareMap);
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get( "fl");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("bl");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("fr");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("br");
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            // drivetrain logic
            double ySpeed = gamepad1.left_stick_y;
            double xSpeed = -gamepad1.left_stick_x*1.1;
            double rotationalSpeed = -gamepad1.right_stick_x;

            mecanumDrive.drive(ySpeed, xSpeed, rotationalSpeed);
            //mecanumDrive.loop(gamepad1);
//            double y = gamepad2.left_stick_y; // Remember, this is reversed!
//            double x = -gamepad2.left_stick_x * 1.1; // Counteract imperfect strafing
//            double rx = -gamepad2.right_stick_x;
//            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//            double frontLeftPower = (y + x + rx) / denominator;
//            double backLeftPower = (y - x + rx) / denominator;
//            double frontRightPower = (y - x - rx) / denominator;
//            double backRightPower = (y + x - rx) / denominator;
//            double slow_down_mode;
//
//            if (gamepad2.right_bumper) {
//                slow_down_mode = 0.5;
//            } else {
//                slow_down_mode = 1.0;
//            }
//
//            motorFrontLeft.setPower(frontLeftPower * slow_down_mode);
//            motorBackLeft.setPower(backLeftPower * slow_down_mode);
//            motorFrontRight.setPower(frontRightPower * slow_down_mode);
//            motorBackRight.setPower(backRightPower * slow_down_mode);
            intake.loop(gamepad2);
            elevator.loop(gamepad2);
            carousel.loop(gamepad2);

            // Show the elapsed game time and wheel power.  telemetry.addData("Status", "Run Time: " + runtime);
            elevator.debug(telemetry);
            intake.debug(telemetry);
            telemetry.addData("Power front right", String.valueOf(motorFrontRight.getPower()));
            telemetry.addData("Power front left", String.valueOf(motorFrontLeft.getPower()));
            telemetry.addData("Encoder front right", String.valueOf(motorFrontRight.getCurrentPosition()));
            telemetry.addData("Encoder front left", String.valueOf(motorFrontLeft.getCurrentPosition()));
            telemetry.addData("Encoder back right", String.valueOf(motorBackRight.getCurrentPosition()));
            telemetry.addData("Encoder back left", String.valueOf(motorBackLeft.getCurrentPosition()));
            telemetry.update();
        }
    }
}