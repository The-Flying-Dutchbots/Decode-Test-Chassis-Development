package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSys {

    CRServo indexerServo;
    DcMotor intakeMotor;

    public void init(HardwareMap hwmap){

        indexerServo = hwmap.get(CRServo.class, "indexer_servo");
        indexerServo.setDirection(CRServo.Direction.REVERSE);

        intakeMotor = hwmap.get(DcMotor.class, "intake_motor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
    }


    public void setIndexerPower(double power){
        indexerServo.setPower(power);
    }
    public void setIntakePower(double power){
        intakeMotor.setPower(power);
    }
}
