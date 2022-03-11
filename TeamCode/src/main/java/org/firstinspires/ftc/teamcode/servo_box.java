//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//
//public class servo_box {
//    public Servo box = null;
//    public final static double box_home = 0.0;
//    public final static double box_min = 0.0;
//    public final static double box_max = 0.0;
//    private HardwareMap hardwareMap;
//    private final BlackBeardOpModeOne blackBeardOpModeOne;
//
//    public servo_box(HardwareMap hardwareMap, BlackBeardOpModeOne blackBeardOpModeOne) {
//        this.hardwareMap = hardwareMap;
//        this.blackBeardOpModeOne = blackBeardOpModeOne;
//
//    }
//
//    public void init_box(){
//        box = hardwareMap.get(Servo.class, "box");
//        box.setDirection(Servo.Direction.FORWARD);
//        box.setPosition(box_home);
//        box.setPosition();
//    }
//
//    public static double getBoxPosition(double wristAngle){
//
//        return
//    }
//
//
//  box = hardwareMap.get(Servo.class, "box");
// box.setDirection(Servo.Direction.FORWARD);
//  box.setPosition(box_home);
//
//            for (double i = 0; i < 360; i++) {
//                    box.setPosition(i / 360);
//            }
//  System.out.println("wrist angle " + getWristAngle());
//        }
//    }
//
//
//
//}
