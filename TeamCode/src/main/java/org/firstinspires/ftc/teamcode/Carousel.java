package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Carousel {
    DcMotor motor;
    private boolean direction = true;
    Carousel(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "carusle");
    }

    public void loop(Gamepad gamepad2) {
        double carouselPower = 0;

        if (gamepad2.b){
            direction = !direction;
        }
        if (gamepad2.y && direction) {
            carouselPower = 0.5;
        }
        else{
            if(gamepad2.y && !direction){
                carouselPower = -0.5;
            }
            else{
                carouselPower = 0.0;
            }
        }
        motor.setPower(carouselPower);
    }
}
