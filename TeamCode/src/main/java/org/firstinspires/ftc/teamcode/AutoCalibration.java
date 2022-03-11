package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "AutoCalibration", group = "", preselectTeleOp = "")
public class AutoCalibration extends LinearOpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    private static final double TICKS_PER_DEGREE = 1 / (28 * 80 / 360.0);

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor barrel = hardwareMap.get(DcMotor.class, "Barrel");
        barrel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int current_position = barrel.getCurrentPosition();
        double barrel_direction = 0.000;

        waitForStart();
        runtime.reset();
        do{
            sleep(1000);
            barrel_direction+= 0.001;
            barrel.setPower(barrel_direction);
            telemetry.addData("barrel tick", barrel.getCurrentPosition());
            telemetry.update();
        } while ((current_position*1.1+100) >= (barrel.getCurrentPosition()+100));
        telemetry.addData("barrel_direction", barrel_direction);
        telemetry.addData("barrel tick", (barrel.getCurrentPosition()+100));
        telemetry.addData("barrel current tick", (current_position*1.1+100));
        telemetry.update();
        barrel.setPower(0.0);

        while (runtime.time()<30){


        }
    }
}