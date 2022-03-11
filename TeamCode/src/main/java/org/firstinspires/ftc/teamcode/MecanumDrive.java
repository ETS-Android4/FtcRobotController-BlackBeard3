package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Gamepad;

public class MecanumDrive {

    private final DcMotor motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight;

    MecanumDrive(HardwareMap hardwareMap) {
        motorFrontLeft = hardwareMap.dcMotor.get("fl");
        motorBackLeft = hardwareMap.dcMotor.get("bl");
        motorFrontRight = hardwareMap.dcMotor.get("fr");
        motorBackRight = hardwareMap.dcMotor.get("br");
    }



      /**
     * Calculates the velocities for each module.
     *
     * @param forward  the velocity in the forward direction.
     * @param strafe   the side velocity.
     * @param rotation the rotational velocity.
     */
    public void drive(double forward, double strafe, double rotation) {
        double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotation), 1);
        double fl = (forward + strafe + rotation) / denominator; // front left
        double rl = (forward - strafe + rotation) / denominator; // rear left
        double fr = (forward - strafe - rotation) / denominator; // front right
        double rr = (forward + strafe - rotation) / denominator; // rear right
        motorFrontLeft.setPower(fl);
        motorBackLeft.setPower(rl);
        motorFrontRight.setPower(fr);
        motorBackRight.setPower(rr);
    }
//        public void loop(Gamepad gamepad1){
//            double y = gamepad1.left_stick_y; // Remember, this is reversed!
//            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
//            double rx = -gamepad1.right_stick_x;
//            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//            double frontLeftPower = (y + x + rx) / denominator;
//            double backLeftPower = (y - x + rx) / denominator;
//            double frontRightPower = (y - x - rx) / denominator;
//            double backRightPower = (y + x - rx) / denominator;
//            double slow_down_mode;
//            //double y = gamepad1.left_stick_y; // Remember, this is reversed!
//            //double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
//            //double rx = -gamepad1.right_stick_x;
//            if (gamepad1.right_bumper) {
//                slow_down_mode = 0.5;
//            } else {
//                slow_down_mode = 1.0;
//            }
//
//            motorFrontLeft.setPower(frontLeftPower * slow_down_mode);
//            motorBackLeft.setPower(backLeftPower * slow_down_mode);
//            motorFrontRight.setPower(frontRightPower * slow_down_mode);
//            motorBackRight.setPower(backRightPower * slow_down_mode);
//    }
}
