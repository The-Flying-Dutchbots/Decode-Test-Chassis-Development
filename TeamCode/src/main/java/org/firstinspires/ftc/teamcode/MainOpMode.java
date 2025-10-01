package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.ArcadeDriveSys;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSys;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;

@TeleOp
public class MainOpMode extends OpMode {

    ArcadeDriveSys driveSys = new ArcadeDriveSys();
    ShooterSys shooterSys = new ShooterSys();


    double foward, rotate, strafe;

    @Override
    public void init() {
        driveSys.init(hardwareMap);
        shooterSys.init(hardwareMap);


    }

    public void loop(){


        foward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        driveSys.FieldOrientedDrive(foward, strafe, rotate);

        if(gamepad1.left_bumper && gamepad1.right_bumper){
            driveSys.ResetPose();
        }

        if(gamepad1.a){
            shooterSys.AdjustPower(-0.005);
            telemetry.addData("shooter power", ShooterSys.shooterPower);
        }
        if(gamepad1.y){
            shooterSys.AdjustPower(0.005);
            telemetry.addData("shooter power", ShooterSys.shooterPower);
        }
        if(gamepad1.right_trigger > 0.5){
            shooterSys.EnableShoot();
            telemetry.addData("shooter power", ShooterSys.shooterPower);

        }else{
            shooterSys.DisableShoot();
        }


    }
}
