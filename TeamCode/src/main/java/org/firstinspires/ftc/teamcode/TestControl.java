package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.TestMotor;
import org.firstinspires.ftc.teamcode.subsystems.TestServo;

@TeleOp
public class TestControl extends OpMode {

    //MotorSys motorSys = new MotorSys();
    TestServo testservo = new TestServo();
    TestMotor testMotor = new TestMotor();


    @Override
    public void init() {
     testservo.init(hardwareMap);
     testMotor.init(hardwareMap);
    }

    @Override
    public void loop() {

telemetry.addData("gamepad a state:", gamepad1.a);

    if(gamepad1.a){
        testservo.SetPosition(1.0);

    }

    if(gamepad1.b){
        testservo.SetPosition(0.0);
    }
    if(gamepad1.x){
        testMotor.SetSpeed(.5);
    }
    if(gamepad1.y){
        testMotor.SetSpeed(-0.3);
    }

    }

}

