package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "Basic: BlackBeardOpModeOne", group = "Linear Opmode")
public class BlackBeardOpModeOne extends LinearOpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    private Carousel carousel;
    private Intake intake;


    @Override
    public void runOpMode() {



        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("fl");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("bl");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("fr");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("br");

//        if ((getWristAngle(barrel)) < 80) {
//            box.setPosition(0.05);
//        } else if (getWristAngle(barrel) > 80 && getWristAngle(barrel) < 120) {
//            box.setPosition(0.3);
//        } else if (getWristAngle(barrel) >= 120 && getWristAngle(barrel) <= 180) {
//            box.setPosition(0.8);
//        }

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        Elevator elevator = new Elevator(hardwareMap);
        carousel = new Carousel(hardwareMap);
        intake = new Intake(hardwareMap);
        //new MotorController(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight);
        waitForStart();
        runtime.reset();


        waitForStart();
        runtime.reset();
        // run until the end of the match (driver presses STOP)
//        int elevator_state = 0;
//        double to_state_time = 0.0;
//        int to_state = 0;
        while (opModeIsActive()) {

//            if (gamepad1.b) { // extend
//                to_state = 1;
//                to_state_time = runtime.time();
//            } else if (gamepad1.a) { // retract
//                to_state = 0;
//                to_state_time = runtime.time();
//            }
//
//            if (runtime.time() - to_state_time > 0) {
//                if (limit_pressed) {
//                    elevator_state = !bouton_pressed ? 1 : 0;
//                }
//            }
            //opening elevator
            intake.loop(gamepad2);
            elevator.loop(gamepad2);
            carousel.loop(gamepad2);

            // Show the elapsed game time and wheel power.  telemetry.addData("Status", "Run Time: " + runtime);
            elevator.debug(telemetry);
            intake.debug(telemetry);
            telemetry.update();
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            double slow_down_mode;

            if (gamepad1.right_bumper) {
                slow_down_mode = 0.5;
            } else {
                slow_down_mode = 1.0;
            }

            motorFrontLeft.setPower(frontLeftPower * slow_down_mode);
            motorBackLeft.setPower(backLeftPower * slow_down_mode);
            motorFrontRight.setPower(frontRightPower * slow_down_mode);
            motorBackRight.setPower(backRightPower * slow_down_mode);




            // Show the elapsed game time and wheel power.
//            telemetry.addData("Status", "Run Time: " + runtime);
//            telemetry.addData("angle", getWristAngle(barrel));
//            telemetry.addData("angle ticks", (barrel.getCurrentPosition() + ZERO_POSITION));
//            telemetry.addData("Motors", "dyson (%.2f)", dyson_direction);
//            telemetry.addData("Motors", "Opening (%.2f)", opening_direction);
//            telemetry.addData("limit switch", limit.isPressed());
//            //telemetry.addData("BOUTON", bouton_pressed);
//            telemetry.addData("elevator_state", elevator_state);
//            telemetry.addData("to_state", to_state);
//            telemetry.addData("barrel_direction", barrel_direction);
//            telemetry.addData("servo", box.getPosition());
//            //telemetry.addData("box direction", box_direction);
//            telemetry.update();


        }
    }

    /**
     * Gets the angle of the wrist.
     *
     * @return the angle in degrees.
     */
   /*private double getWristAngle(DcMotor motor) {
        return (((int) (motor.getCurrentPosition() / 10)) * 10 + ZERO_POSITION) * TICKS_PER_DEGREE;
    }*/
//    private double getWristAngle(DcMotor motor) {
//        return (((int) (motor.getCurrentPosition() / 10)) * 10 + ZERO_POSITION) * TICKS_PER_DEGREE;
//
//    }

}