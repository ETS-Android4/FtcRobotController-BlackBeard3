package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class RedAutonomous extends LinearOpMode {

    // runtime variable
    private ElapsedTime runtime = new ElapsedTime();

    //motors
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;
    DcMotor carousel;

    CRServo carousel2;

    //finals
    private static final double DRIVE_POWER = 0.5;
    private static final double CAROUSEL_POWER = 0.6D;

    @Override
    public void runOpMode() {
        //get all the required motors.
        motorFrontLeft = hardwareMap.dcMotor.get("fl");
        motorBackLeft = hardwareMap.dcMotor.get("bl");
        motorFrontRight = hardwareMap.dcMotor.get("fr");
        motorBackRight = hardwareMap.dcMotor.get("br");
        carousel = hardwareMap.get(DcMotor.class, "carusle");
        //carousel2 = hardwareMap.get(CRServo.class, "carousel2");
        MotorController controller = new MotorController(motorFrontLeft,motorBackLeft,motorFrontRight,motorBackRight,telemetry);
        ElevatorController elevator = new ElevatorController(hardwareMap);
        debug("Status", "Initialized"); //update the status in the Telemetry.
        waitForStart();// Wait for the game to start
        debug("Status", "Started!");
//        controller.forward(DRIVE_POWER, controller.cmToTicks(80));98
//        controller.rotationRight(DRIVE_POWER, controller.cmToTicks(240));
//        controller.backward(DRIVE_POWER, controller.cmToTicks(5));
//        elevator.MoveElevatorToPosition(140);
//        elevator.flipBox();
//        MotorController.sleep(500);
//
//        elevator.MoveElevatorToPosition(25);
//
//        controller.backward(DRIVE_POWER, controller.cmToTicks(90));
//
//        MotorController.sleep(500);
//
//        elevator.MoveElevatorToPosition(25);
//        controller.backward(DRIVE_POWER, controller.cmToTicks(100));

        //carousel mission
        controller.rotationRight(DRIVE_POWER, controller.cmToTicks(25));
        controller.right(DRIVE_POWER, controller.cmToTicksSide(180)); //drive left to get to the target
        //carousel2.setPower(1);
        controller.runMotorForTime(carousel, CAROUSEL_POWER, 5000); //spin the carousel to drop the duck
        //carousel2.setPower(0);
        controller.forward(DRIVE_POWER, controller.cmToTicks(40)); //drive forward
        controller.rotationLeft(DRIVE_POWER, controller.cmToTicks(60)); //TURN right
        controller.forward(DRIVE_POWER, controller.cmToTicks(360)); //drive forward
        /*
        controller.left(DRIVE_POWER, 5000); //drive right to get to the target
        controller.runMotorForTime(carousel, CAROUSEL_POWER, 3500); //spin the carousel to drop the duck
        controller.forward(DRIVE_POWER, 500); //drive forward
        controller.rotationRight(DRIVE_POWER, 1500);
        controller.left(DRIVE_POWER, 8000); //drive left to get to the storage area
        */
    }

    private void debug(String caption, String body){
        telemetry.addData(caption, body);
        telemetry.update();
    }

}