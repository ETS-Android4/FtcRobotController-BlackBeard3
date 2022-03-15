package org.firstinspires.ftc.teamcode;

//import androidx.annotation.NonNull;

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
    private Servo gate;
    //private boolean ari_in_box = false;
    private boolean gate_down = false;
    private boolean move_to_100_degree = false;

    public double calculateFeedForward() {
        return Math.cos(Math.toRadians(getWristAngle(elevatorMotor))) * kFF;
    }

    public Elevator(HardwareMap hardwareMap) {
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

//    public void move_elevator_to_position(int degree) {
//        double current = poto.getVoltage()*81.8;
//        //boolean is_reached = false;
//        if (degree > 270 || degree < 0) {
//            return;
//        }
//
////        if (current > degree) {
////            elevatorMotor.setPower(-1.0);
//        //current = poto.getVoltage()*81.8;
//        //while (poto.getVoltage()*81.8 > degree) {}
//        // } else
//        if (move_to_100_degree && current < degree){
//            // Move the elevator up.
//            elevatorMotor.setPower(1.0);
//            //while (poto.getVoltage()*81.8 < degree) {}
//        }
//        else{
//            move_to_100_degree = false;
//        }
//
//        // Wait until the elevator passes our target degree
//        //elevatorMotor.setPower(0);
//    }

    public void move_elevator_to_position(int degree) {
        double current = poto.getVoltage()*81.8;

        if (degree > 270 || degree < 0) {
            return;
        }

        if (current > degree) {
            elevatorMotor.setPower(-0.4);
            while (poto.getVoltage()*81.8 > degree) {}
        } else {
            // Move the elevator up.
            elevatorMotor.setPower(0.4);
            while (poto.getVoltage()*81.8 < degree) {}
        }

        // Wait until the elevator passes our target degree
        elevatorMotor.setPower(0);
    }

    public void loop(Gamepad gamepad2) {
        potoPosition = Range.scale(poto.getVoltage()*81.8, 0, 270, -1, 1) ;

        openingMotorPower = 0;
        //feedforward = calculateFeedForward();
        //double angle = (elevatorMotor.getCurrentPosition()) * TICKS_PER_DEGREE;
        //if (getWristAngle(elevatorMotor) > 90) {

        //move elevator to a certain angle:
//        if(gamepad2.dpad_left){
//            move_to_100_degree = true;
//        }
//        move_elevator_to_position(100);
//        if(move_to_100_degree){
//            move_elevator_to_position(100);
//            if(99 < poto.getVoltage()*81.8 && poto.getVoltage()*81.8<101){
//                move_to_100_degree = false;
//                elevatorMotor.setPower(0.0);
//            }
//        }
//        if(gamepad2.dpad_right){
//            move_elevator_to_position(50);
//        }


//        elevatorMotor.setPower(-gamepad2.left_stick_y + feedforward); // - feedforward
        //} else {
        if(gamepad2.dpad_right){
            new Thread(() -> move_elevator_to_position(50));
        }
        if (gamepad2.x){ //to open the intake system
            elevatorMotor.setPower(0.7);
        }
        else {
            elevatorMotor.setPower((-gamepad2.left_stick_y) / 3.5);
        }
        //abs(1-(2/pi)abs(x))
//        double angle = getWristAngle(elevatorMotor);
//        if (Math.abs(angle) > 1e-[[8)]]
//            box.setPosition(Math.abs(1-(2/Math.PI)*Math.abs(Math.toRadians(angle))));
//        else
//            box.setPosition(box.getPosition() + 0.01);

        //dropping freights with gamepad2 bouton:
//        if(gamepad2.a){
//            ari_in_box = true;
//        }


        if(gamepad2.left_bumper){
            box.setPosition(1);
            gate_down = false;
        }
        else if(potoPosition>=0.5){
            box.setPosition(0.35);
        }
        //else if(ari_in_box && potoPosition<=-0.7){
        //    box.setPosition(0.65);
        //}
        else{
            box.setPosition(Math.abs(potoPosition));
            //ari_in_box=false;
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

        //gate servo logic:
        if(gamepad2.dpad_down){
            gate_down = true;
        }
        else if (gamepad2.dpad_up){
            gate_down = false;
        }
        if(gate_down){ //the stick closes the box
            gate.setPosition(0.4);
        }
        else{ //the stick does not close the box
            gate.setPosition(1.0);
        }
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
        //telemetry.addData("feedforward val", feedforward);
        telemetry.addData("servo", box.getPosition());
        telemetry.addData("poto voltage",poto.getVoltage());
        telemetry.addData("poto degree", poto.getVoltage()*81.8);
    }

    public double getWristAngle(DcMotor motor) {
        return  (motor.getCurrentPosition()  + ZERO_POSITION) * TICKS_PER_DEGREE;
    }

}






