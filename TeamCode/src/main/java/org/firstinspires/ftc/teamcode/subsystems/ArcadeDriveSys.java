package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ArcadeDriveSys {

    private DcMotor RR_motor, FR_motor, FL_motor, RL_motor;


    private IMU imu;

    public void init(HardwareMap hwmap){

        // initializing the drive chassis motors with the Driver Hub Config. file
        FL_motor = hwmap.get(DcMotor.class, "RF_drive_motor");
        RL_motor = hwmap.get(DcMotor.class, "RR_drive_motor");
        RR_motor = hwmap.get(DcMotor.class, "LF_drive_motor");
        FR_motor = hwmap.get(DcMotor.class, "LR_drive_motor");



        FL_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RL_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RR_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FL_motor.setDirection(DcMotor.Direction.REVERSE);
        RL_motor.setDirection(DcMotor.Direction.REVERSE);
        RR_motor.setDirection(DcMotor.Direction.REVERSE);
        FR_motor.setDirection(DcMotor.Direction.REVERSE);

        // initializing the IMU (Internal gyro)
        imu = hwmap.get(IMU.class, "imu");

        RevHubOrientationOnRobot HubOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(HubOrientation));
    }

    public void RobotOrientedDrive(double foward, double strafe, double rotate){
        double FL_Power = foward + strafe + rotate;
        double RL_Power = foward - strafe + rotate;
        double RR_Power = foward - strafe - rotate;
        double FR_Power = foward + strafe - rotate;

        double maxPower = 1.0;
        double maxSpeed = 1.0;

        maxPower = Math.max(maxPower, Math.abs(FL_Power));
        maxPower = Math.max(maxPower, Math.abs(RL_Power));
        maxPower = Math.max(maxPower, Math.abs(RR_Power));
        maxPower = Math.max(maxPower, Math.abs(FR_Power));

        FL_motor.setPower(maxSpeed * (FL_Power / maxPower));
        RL_motor.setPower(maxSpeed * (RL_Power / maxPower));
        RR_motor.setPower(maxSpeed * (RR_Power / maxPower));
        FR_motor.setPower(maxSpeed * (FR_Power / maxPower));


    }

    public void FieldOrientedDrive(double foward, double strafe, double rotate){
        double theta = Math.atan2(strafe, foward);
        double r = Math.hypot(strafe, foward);

        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newFoward = r * Math.cos(theta);
        double newStrafe = r * Math.sin(theta);

        this.RobotOrientedDrive(newFoward, newStrafe, rotate);
    }

    public void ResetPose(){
        imu.resetYaw();
    }
    public double getYaw(){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

}


