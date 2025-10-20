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
    IntakeSys intakeSys = new IntakeSys();


    double foward, rotate, strafe;

    @Override
    public void init() {
        driveSys.init(hardwareMap);
        shooterSys.init(hardwareMap);
        intakeSys.init(hardwareMap);


    }

    public void loop(){

        telemetry.addData("robotYaw",driveSys.getYaw());
        foward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        driveSys.FieldOrientedDrive(foward, strafe, rotate);

        if(gamepad1.left_bumper && gamepad1.right_bumper){
            driveSys.ResetPose();
        }




        if (gamepad1.a) {
            shooterSys.setHoodState(ShooterSys.HoodState.STOWED);
            shooterSys.setShooterPower(Constants.CLOSE_SHOT_POWER);
        }
        else if (gamepad1.x) {
            shooterSys.setHoodState(ShooterSys.HoodState.MID);
            shooterSys.setShooterPower(Constants.MID_SHOT_POWER);
        }
        else if (gamepad1.y) {
            shooterSys.setHoodState(ShooterSys.HoodState.FAR);
            shooterSys.setShooterPower(Constants.FAR_SHOT_POWER);
        }
        else {
            shooterSys.setShooterPower(0);
        }

        // Call this every loop to handle timed hood movement
        shooterSys.updateHood();

        telemetry.addData("Hood Pos", shooterSys.getCurrentHoodPos());
        telemetry.addData("Is Moving", shooterSys.getIsMoving());
        telemetry.update();


        // intake control

        if (gamepad1.right_trigger > 0.5){
            intakeSys.setIndexerPower(0.8);
            intakeSys.setIntakePower(Constants.INTAKE_POWER);
        }
        else{
            intakeSys.setIndexerPower(0);
            intakeSys.setIntakePower(0);
        }

    }
}

