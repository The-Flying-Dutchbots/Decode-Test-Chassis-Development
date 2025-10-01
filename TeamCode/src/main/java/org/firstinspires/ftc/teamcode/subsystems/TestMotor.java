package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestMotor {

    private DcMotor testMotor;

    public void init(HardwareMap hwmap){
        testMotor = hwmap.get(DcMotor.class, "test_motor");
        testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void SetSpeed(double speed){
        testMotor.setPower(speed);
    }
}
