package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AlonCatavEtZE", group = "", preselectTeleOp = "Basic: NewOpMode 2")
public class Auto extends LinearOpMode {
    private MecanumDrive mecanumDrive;
    private Elevator elevator;
    private final ElapsedTime runtime = new ElapsedTime();
    private Carousel carousel;
    private Intake intake;

    @Override
    public void runOpMode() {
        mecanumDrive = new MecanumDrive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        carousel = new Carousel(hardwareMap);
        intake = new Intake(hardwareMap);
        waitForStart();
        runtime.reset();
        while (runtime.seconds() < 30) {
            
        }
    }
}