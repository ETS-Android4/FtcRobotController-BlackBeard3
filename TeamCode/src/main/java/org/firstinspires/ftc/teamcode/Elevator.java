package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;


//New Op Mode <- LinearOpMode => has hardwareMap


//Elevator <= hardwareMap at construction
//new Elevator(...) =>
public class Elevator {
    public static final double kFF = 0.01;
    private static final double ZERO_POSITION = 0; //-380
    private static final double ZERO_POTO_POSITION = 1.12; //-380
    private static final double TICKS_PER_DEGREE = 1 / (28 * (80*(4/3.0)) / 360.0);

    private double openingMotorPower, feedforward=0, elevatorPower, potoPosition;
    private final DcMotor elevatorMotor, openingMotor;
    private final TouchSensor inSensor, outSensor;
    private AnalogInput poto;
    //private final double TICKS_PER_DEGREE = 1 / (28 * (125*(4/3.0)) / 360.0);
    private Servo box;
    private boolean ari_in_box = false;

    public double calculateFeedForward() {
        return Math.cos(Math.toRadians(getWristAngle(elevatorMotor))) * kFF;
    }

    public Elevator(@NonNull HardwareMap hardwareMap) {
        box = hardwareMap.get(Servo.class, "box");
        elevatorMotor = hardwareMap.get(DcMotor.class, "Barrel");
        elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        box.setDirection(Servo.Direction.FORWARD);

        poto = hardwareMap.get(AnalogInput.class, "poto");


        openingMotor = hardwareMap.get(DcMotor.class, "opening_elevator");
        openingMotor.setDirection(DcMotor.Direction.FORWARD);
        outSensor = hardwareMap.get(TouchSensor.class, "Limit");
        inSensor = hardwareMap.get(TouchSensor.class, "Bouton");
    }



    public void loop(Gamepad gamepad2) {
        potoPosition = Range.scale(poto.getVoltage()*81.8, 0, 270, -1, 1) ;

        openingMotorPower = 0;
        //feedforward = calculateFeedForward();
        //double angle = (elevatorMotor.getCurrentPosition()) * TICKS_PER_DEGREE;
        //if (getWristAngle(elevatorMotor) > 90) {

//        elevatorMotor.setPower(-gamepad2.left_stick_y + feedforward); // - feedforward
        //} else {
        elevatorMotor.setPower((-gamepad2.left_stick_y  + feedforward)/3);
        //abs(1-(2/pi)abs(x))
//        double angle = getWristAngle(elevatorMotor);
//        if (Math.abs(angle) > 1e-8)
//            box.setPosition(Math.abs(1-(2/Math.PI)*Math.abs(Math.toRadians(angle))));
//        else
//            box.setPosition(box.getPosition() + 0.01);

        //dropping freights with gamepad2 bouton:
        if(gamepad2.a){
            ari_in_box = true;
        }


        if(gamepad2.left_bumper){
            box.setPosition(1);
        }
        else if(potoPosition>=0.5){
            box.setPosition(0.35);
        }
        else if(ari_in_box && potoPosition<=-0.7){
            box.setPosition(0.65);
        }
        else{
            box.setPosition(Math.abs(potoPosition));
            ari_in_box=false;
        }
//        box.setPosition(Math.abs(Math.cos(Math.toRadians(getWristAngle(elevatorMotor)))));
//        box.setPosition(gamepad2.left_trigger);


        if (gamepad2.left_trigger > 0.1) { // extend
//            if (!outSensor.isPressed())
            openingMotorPower = -gamepad2.left_trigger;
            //else
                //openingMotorPower = 0;
        }
        else if (gamepad2.right_trigger>0.1) { // retract
                //if (!inSensor.isPressed())
            openingMotorPower = gamepad2.right_trigger;
        }
        else{
            openingMotorPower = 0.0;
        }

        openingMotor.setPower(openingMotorPower);
    }


    private double map(double input, double minInput, double maxInput, double minOutput, double maxOutput) {
        return input * (maxInput - minInput) / (maxOutput - minOutput);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("angle", getWristAngle(elevatorMotor));
        telemetry.addData("angle ticks", (elevatorMotor.getCurrentPosition() + ZERO_POSITION));
        telemetry.addData("Motors", "Opening (%.2f)", openingMotorPower);
        telemetry.addData("outsensor is pressed", outSensor.isPressed());
        telemetry.addData("outsensor pressed", inSensor.isPressed());
        telemetry.addData("elevator power", elevatorPower);
        telemetry.addData("feedforward val", feedforward);
        telemetry.addData("servo", box.getPosition());
        telemetry.addData("poto",potoPosition);
    }

    public double getWristAngle(DcMotor motor) {
        return  (motor.getCurrentPosition()  + ZERO_POSITION) * TICKS_PER_DEGREE;
    }

}






