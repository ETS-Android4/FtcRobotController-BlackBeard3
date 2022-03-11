//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.TouchSensor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.util.Range;
////@Autonomous
//@TeleOp(name = "Basic: BlackBeardOpModeOne", group = "Linear Opmode")
//public class BlackBeardOpModeOne extends LinearOpMode {
//    private static final double TICKS_PER_DEGREE = 1 / ( 80 / 360.0);
//    private static final double ZERO_POSITION = 0; // ticks
//
//    // Declare OpMode members.
//    private final ElapsedTime runtime = new ElapsedTime();
//    public DcMotor barrel = null;
//
//
//
//    @Override
//    public void runOpMode() {
//        DcMotor dyson = hardwareMap.get(DcMotor.class, "dyson");
//        dyson.setDirection(DcMotor.Direction.FORWARD);
//
//        DcMotor opening_motor = hardwareMap.get(DcMotor.class, "opening_elevator");
//        opening_motor.setDirection(DcMotor.Direction.FORWARD);
//
//        Servo servo = hardwareMap.get(Servo.class, "opening_elevator");
//
//        DcMotor carusle = hardwareMap.get(DcMotor.class, "carusle");
//        TouchSensor limit = hardwareMap.get(TouchSensor.class, "Limit");
//        TouchSensor bouton = hardwareMap.get(TouchSensor.class, "Bouton");
//
//        barrel = hardwareMap.get(DcMotor.class, "Barrel");
//        // Declare our motors
//        // Make sure your ID's match your configuration
//        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("fl");
//        DcMotor motorBackLeft = hardwareMap.dcMotor.get("bl");
//        DcMotor motorFrontRight = hardwareMap.dcMotor.get("fr");
//        DcMotor motorBackRight = hardwareMap.dcMotor.get("br");
//
//        // Reverse the right side motors
//        // Reverse left motors if you are using NeveRests
//        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
//
//
//        waitForStart();
//        runtime.reset();
//
//        // run until the end of the match (driver presses STOP)
//        int elevator_state = 1;
//        double to_state_time = 0.0;
//        int to_state = 1;
//        int diff_state = 0;
//        while (opModeIsActive()) {
//            // carousel
//            boolean limit_pressed = limit.isPressed();
//            //boolean bouton_pressed = bouton.isPressed();
//
//            // opening_elevator
//
//            if (gamepad1.y) { // extend
//                to_state = 2;
//                to_state_time = runtime.time();
//            } else if (gamepad1.a) { // retract
//                to_state = 0;
//                to_state_time = runtime.time();
//            }
//            else if (gamepad1.b){
//                to_state = 1;
//                to_state_time = runtime.time();
//            }
//            diff_state = to_state - elevator_state;
//            if(diff_state<0){
//                for(int i = 0; i > diff_state; i--){
//                    if(runtime.time()- to_state_time < 0.1){
//                        opening_motor.setPower(-1);
//                    }
//                    else{
//                        while(!limit_pressed){
//                            opening_motor.setPower(-1);
//                        }
//                    }
//                   elevator_state --;
//                }
//            }
//            else if(diff_state>0) {
//                for (int i = 0; i < diff_state; i++) {
//                    if (runtime.time() - to_state_time < 0.1) {
//                        opening_motor.setPower(1);
//                    } else {
//                        while (!limit_pressed) {
//                            opening_motor.setPower(1);
//                        }
//                    }
//                    elevator_state++;
//                }
//            }
//
//
////            if (runtime.time() - to_state_time > 0) {
////                if (limit_pressed) {
////                    elevator_state = !bouton_pressed ? 1 : 0;
////                }
////            }
//
//            if (gamepad2.y) {
//                carusle.setPower(0.5);
//            } else {
//                carusle.setPower(0.0);
//            }
//
//            barrel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//            // turning the elevator
//            double barrel_direction;
//            if (gamepad1.right_trigger > 0) {
//                barrel_direction = 0.5 * gamepad1.right_trigger;
//            } else if (gamepad1.left_trigger > 0) {
//                barrel_direction = -0.5 * gamepad1.left_trigger;
//            } else if (gamepad1.right_bumper) {
//                barrel_direction = 0.2;
//            } else if (gamepad1.left_bumper) {
//                barrel_direction = -0.2;
//            } else {
//                barrel_direction = 0.07 * Math.cos(Math.toRadians((barrel.getCurrentPosition()*80) / 360.0));//maybe -0.1???
//            }
//
//
//            double dyson_direction = Range.clip(-gamepad1.right_stick_y, -1, 1);
//            double opening_direction = 0.5 * Math.signum(to_state - elevator_state);
//            // driving
//            double y = gamepad2.left_stick_y; // Remember, this is reversed!
//            double x = -gamepad2.left_stick_x * 1.1; // Counteract imperfect strafing
//            double rx = -gamepad2.right_stick_x;
//
//
//            barrel.setPower(barrel_direction);
//            dyson.setPower(dyson_direction);
//            opening_motor.setPower(opening_direction);
//
//            // Denominator is the largest motor power (absolute value) or 1
//            // This ensures all the powers maintain the same ratio, but only when
//            // at least one is out of the range [-1, 1]
//
//            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//            double frontLeftPower = (y + x + rx) / denominator;
//            double backLeftPower = (y - x + rx) / denominator;
//            double frontRightPower = (y - x - rx) / denominator;
//            double backRightPower = (y + x - rx) / denominator;
//            double slow_down_mode;
//            if(gamepad2.right_bumper) {
//                slow_down_mode = 0.5;
//            }
//            else{
//                slow_down_mode=1.0;
//            }
//
//            motorFrontLeft.setPower(frontLeftPower * slow_down_mode);
//            motorBackLeft.setPower(backLeftPower * slow_down_mode);
//            motorFrontRight.setPower(frontRightPower * slow_down_mode);
//            motorBackRight.setPower(backRightPower * slow_down_mode);
//            // Show the elapsed game time and wheel power.
//            telemetry.addData("Status", "Run Time: " + runtime);
//            telemetry.addData("angle", (barrel.getCurrentPosition()*80) / 360.0);
//            telemetry.addData("Motors", "dyson (%.2f)", dyson_direction);
//            telemetry.addData("Motors", "Opening (%.2f)", opening_direction);
//            telemetry.addData("limit switch", limit_pressed);
//            //telemetry.addData("BOUTON", bouton_pressed);
//            telemetry.addData("elevator_state", elevator_state);
//            telemetry.addData("to_state", to_state);
//            telemetry.update();
//        }
//    }
//
//    /**
//     * Gets the angle of the wrist.
//     *
//     * @return the angle in degrees.
//     */
//    public double getWristAngle() {
//        return (barrel.getCurrentPosition() - ZERO_POSITION) * TICKS_PER_DEGREE;
//    }
//}
