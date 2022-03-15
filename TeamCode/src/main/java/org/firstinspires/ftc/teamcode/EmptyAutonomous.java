package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class EmptyAutonomous extends LinearOpMode {

    // runtime variable
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        debug("Status", "Initialized"); //update the status in the Telemetry.
        waitForStart();// Wait for the game to start
        debug("Status", "Started!");

        while (runtime.seconds() < 30){}
        stop();
        requestOpModeStop();
    }

    private void debug(String caption, String body){
        telemetry.addData(caption, body);
        telemetry.update();
    }

}
