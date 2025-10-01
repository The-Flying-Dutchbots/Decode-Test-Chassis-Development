package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterSys {

    DcMotor shooterMotor;

    public static double shooterPower = 0.8;

    public void init(HardwareMap hwmap){

        shooterMotor = hwmap.get(DcMotor.class, "shooter_motor");
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void EnableShoot(){
        shooterMotor.setPower(shooterPower);
    }

    public void DisableShoot(){
        shooterMotor.setPower(0);
    }

    public void AdjustPower(double incriment){
            shooterPower += incriment;

    }

}
