package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class MotorSys{

    private DcMotor motor1;

    public void init(HardwareMap hwMap) {
        setTargetPosition(0);

        motor1 = hwMap.get(DcMotor.class, "insert what we call it on CtrlHub");
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor1.setPower(0.3);
    }



    public void setTargetPosition(int targetPosition){
        motor1.setTargetPosition(targetPosition);
    }
}
