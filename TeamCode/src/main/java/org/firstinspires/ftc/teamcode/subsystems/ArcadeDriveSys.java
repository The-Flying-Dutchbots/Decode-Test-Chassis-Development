package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Constants;

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

        FL_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RL_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RR_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // initializing the IMU (Internal gyro)
        imu = hwmap.get(IMU.class, "imu");

        RevHubOrientationOnRobot HubOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(HubOrientation));
    }

    public void autoInit(HardwareMap hwmap){

        // initializing the drive chassis motors with the Driver Hub Config. file
        FL_motor = hwmap.get(DcMotor.class, "RF_drive_motor");
        RL_motor = hwmap.get(DcMotor.class, "RR_drive_motor");
        RR_motor = hwmap.get(DcMotor.class, "LF_drive_motor");
        FR_motor = hwmap.get(DcMotor.class, "LR_drive_motor");

        FL_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RL_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RR_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        FL_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RL_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RR_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FL_motor.setDirection(DcMotor.Direction.REVERSE);
        RL_motor.setDirection(DcMotor.Direction.REVERSE);
        RR_motor.setDirection(DcMotor.Direction.REVERSE);
        FR_motor.setDirection(DcMotor.Direction.REVERSE);

        FL_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RL_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RR_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // initializing the IMU (Internal gyro)
        imu = hwmap.get(IMU.class, "imu");

        RevHubOrientationOnRobot HubOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(HubOrientation));
    }



    public void moveFowardInchesAtPower(double inches, double power){
       int distance = (int)(inches * Constants.ENCODER_TICKS_PER_INCH);

        FL_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RL_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RR_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RL_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RR_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FL_motor.setTargetPosition(distance);
        FR_motor.setTargetPosition(distance);
        RR_motor.setTargetPosition(distance);
        RL_motor.setTargetPosition(distance);

        FL_motor.setPower(power);
        FR_motor.setPower(power);
        RR_motor.setPower(power);
        RL_motor.setPower(power);

    }
    public int getFLPosition(){
       return FL_motor.getCurrentPosition();
    }
    public int getFRPosition(){
        return FR_motor.getCurrentPosition();
    }
    public int getRRPosition(){
        return RR_motor.getCurrentPosition();
    }
    public int getRLPosition(){
        return RL_motor.getCurrentPosition();
    }

    public void turnDegrees(double degrees, double power) {
        int ticks = (int)(Constants.TICKS_PER_DEGREE * degrees); // target ticks based on turn factor


        // Stop & reset encoders
        FL_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RL_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RR_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set RUN_TO_POSITION
        FL_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RL_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RR_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set target positions for turning
        FL_motor.setTargetPosition(ticks);   // left wheels forward
        RL_motor.setTargetPosition(ticks);
        FR_motor.setTargetPosition(-ticks);  // right wheels backward
        RR_motor.setTargetPosition(-ticks);

        // Set power
        FL_motor.setPower(power);
        RL_motor.setPower(power);
        FR_motor.setPower(power);
        RR_motor.setPower(power);
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

    public boolean isBusy() {
        return FL_motor.isBusy() || FR_motor.isBusy() || RR_motor.isBusy() || RL_motor.isBusy();
    }

}


