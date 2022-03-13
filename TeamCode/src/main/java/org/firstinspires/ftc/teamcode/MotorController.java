package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MotorController {

    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;
    Telemetry telemetry;

    private static final double ticksPerCm = 6.756756756756757;

    public int cmToTicks(double cm){
        return (int)(cm * ticksPerCm);
    }

    public static void sleep(long Time) {
        try {Thread.sleep(Time);} catch (InterruptedException e) {e.printStackTrace();}
    }

    private void resetPosition() {
        //motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //motorBackLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        //motorFrontLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        //motorFrontRight.setMode(DcMotor.RunMode.RESET_ENCODERS);

        sleep(100);
    }

    private void runTargetPosition(){
        //motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void waitForTarget(int ticks) {
        while (//motorBackRight.isBusy() &&
                Math.abs(motorBackLeft.getCurrentPosition()) <= ticks &&
                Math.abs(motorFrontLeft.getCurrentPosition()) <= ticks &&
                Math.abs(motorFrontRight.getCurrentPosition()) <= ticks) {
            telemetry.addData("Ticks", String.valueOf(ticks));
            telemetry.addData("Power front right", String.valueOf(motorFrontRight.getPower()));
            telemetry.addData("Power front left", String.valueOf(motorFrontLeft.getPower()));
            telemetry.addData("Encoder front right", String.valueOf(Math.abs(motorFrontRight.getCurrentPosition())));
            telemetry.addData("Encoder front left", String.valueOf(Math.abs(motorFrontLeft.getCurrentPosition())));
            telemetry.addData("Encoder back right", String.valueOf(Math.abs(motorBackRight.getCurrentPosition())));
            telemetry.addData("Encoder back left", String.valueOf(Math.abs(motorBackLeft.getCurrentPosition())));
            telemetry.update();
        }

    }

    public void MotorsStop() {
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);

        resetPosition();
    }

    public MotorController(DcMotor motorFrontLeft,
                           DcMotor motorBackLeft,
                           DcMotor motorFrontRight,
                           DcMotor motorBackRight,
                           Telemetry telemetry){
        this.motorFrontLeft = motorFrontLeft;
        this.motorBackLeft = motorBackLeft;
        this.motorFrontRight = motorFrontRight;
        this.motorBackRight = motorBackRight;
        this.telemetry = telemetry;
        resetPosition();
    }

    public void left(double power, int encoderTicks){
        motorBackRight.setPower(-power);
        motorBackLeft.setPower(-power);
        motorFrontLeft.setPower(power);
        motorFrontRight.setPower(power);
        //runTargetPosition();
        waitForTarget(encoderTicks);
        MotorsStop();
    }

    public void right(double power, int encoderTicks){
//        motorBackRight.setTargetPosition(encoderTicks);
//        motorBackLeft.setTargetPosition(-encoderTicks);
//        motorFrontLeft.setTargetPosition(encoderTicks);
//        motorFrontRight.setTargetPosition(-encoderTicks);
//
//        motorBackRight.setPower(power);
//        motorBackLeft.setPower(-power);
//        motorFrontLeft.setPower(power);
//        motorFrontRight.setPower(-power);
//        runTargetPosition();
//        waitForTarget(encoderTicks);
//        MotorsStop();
        motorBackRight.setPower(power);
        motorBackLeft.setPower(power);
        motorFrontLeft.setPower(-power);
        motorFrontRight.setPower(-power);
        //runTargetPosition();
        waitForTarget(encoderTicks);
        MotorsStop();
    }

    public void rotationRight(double power, int ticks){
        motorBackRight.setPower(-power);
        motorBackLeft.setPower(-power);
        motorFrontLeft.setPower(-power);
        motorFrontRight.setPower(-power);
        waitForTarget(ticks);
        MotorsStop();
    }

    public void forward(double power, int encoderTicks){
        //motorBackRight.setTargetPosition(encoderTicks);
        //motorBackLeft.setTargetPosition(-encoderTicks);
        //motorFrontLeft.setTargetPosition(-encoderTicks);
        //motorFrontRight.setTargetPosition(encoderTicks);

        motorBackRight.setPower(power);
        motorBackLeft.setPower(-power);
        motorFrontLeft.setPower(-power);
        motorFrontRight.setPower(power);
        //runTargetPosition();
        waitForTarget(encoderTicks);
        MotorsStop();
    }

    public void runMotorForTime(DcMotor motor,double power, long timeInMillis){
        motor.setPower(power);
        try {Thread.sleep(timeInMillis);} catch (InterruptedException e) {e.printStackTrace();}
        motor.setPower(0);
    }

}