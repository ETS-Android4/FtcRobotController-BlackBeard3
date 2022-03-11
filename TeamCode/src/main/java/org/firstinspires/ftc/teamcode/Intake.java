package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    private final DcMotor intake;
    private double intakePower;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "dyson");
        intake.setDirection(DcMotor.Direction.FORWARD);
    }

    public void loop(Gamepad gamepad2) {
        intakePower = -gamepad2.right_stick_y;
        intake.setPower(intakePower);
    }
    public void debug(Telemetry telemetry) {
        telemetry.addData("Motors", "dyson (%.2f)", intakePower);
    }

}
