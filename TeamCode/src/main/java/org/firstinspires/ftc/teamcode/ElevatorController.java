package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

public class ElevatorController {

    private final DcMotor elevatorMotor, openingMotor;
    private final TouchSensor inSensor, outSensor;
    private AnalogInput poto;
    //private final double TICKS_PER_DEGREE = 1 / (28 * (125*(4/3.0)) / 360.0);
    private Servo box;
    private Servo gate;

    public ElevatorController(HardwareMap hardwareMap) {
        box = hardwareMap.get(Servo.class, "box");
        gate = hardwareMap.get(Servo.class, "gate");
        elevatorMotor = hardwareMap.get(DcMotor.class, "Barrel");
        elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        box.setDirection(Servo.Direction.FORWARD);
        gate.setDirection(Servo.Direction.FORWARD);


        poto = hardwareMap.get(AnalogInput.class, "poto");


        openingMotor = hardwareMap.get(DcMotor.class, "opening_elevator");
        openingMotor.setDirection(DcMotor.Direction.FORWARD);
        outSensor = hardwareMap.get(TouchSensor.class, "Limit");
        inSensor = hardwareMap.get(TouchSensor.class, "Bouton");
    }


    public void MoveElevatorToPosition(int degree) {
        double current = poto.getVoltage()*81.8;

        if (degree > 270 || degree < 0) {
            return;
        }

        if (current > degree) {
            elevatorMotor.setPower(-0.4);
            while (poto.getVoltage()*81.8 > degree)
                box.setPosition(Math.abs(Range.scale(poto.getVoltage()*81.8, 0, 270, -1, 1)));
        } else {
            // Move the elevator up.
            elevatorMotor.setPower(0.4);
            while (poto.getVoltage()*81.8 < degree)
                box.setPosition(Math.abs(Range.scale(poto.getVoltage()*81.8, 0, 270, -1, 1)));
        }

        // Wait until the elevator passes our target degree
        elevatorMotor.setPower(0);
    }

    public void flipBox(){
        box.setPosition(1);
    }

}