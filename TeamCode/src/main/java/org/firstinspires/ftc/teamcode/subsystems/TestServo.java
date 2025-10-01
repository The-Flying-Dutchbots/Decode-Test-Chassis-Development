package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TestServo {

    private Servo testservo;

    public void init(HardwareMap hwMap){
        testservo = hwMap.get(Servo.class, "test_servo");

    }

    public void SetPosition(double targetPosition){
        testservo.setPosition(targetPosition);
    }

}
