package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class IntakeSys {

    CRServo indexerServo;
    DcMotor intakeMotor;

    DigitalChannel touchOne,touchTwo;

    public void init(HardwareMap hwmap){

        indexerServo = hwmap.get(CRServo.class, "indexer_servo");
        indexerServo.setDirection(CRServo.Direction.REVERSE);

        intakeMotor = hwmap.get(DcMotor.class, "intake_motor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

       // beamBreak = hwmap.get(DigitalChannel.class, "beam_break");
       // beamBreak.setMode(DigitalChannel.Mode.INPUT);

        touchOne = hwmap.get(DigitalChannel.class, "touch_one");
        touchTwo = hwmap.get(DigitalChannel.class, "touch_two");
        touchOne.setMode(DigitalChannel.Mode.INPUT);
        touchTwo.setMode(DigitalChannel.Mode.INPUT);
    }
public void intakeStart(){
        boolean isWaiting = ((!touchOne.getState()) || (!touchTwo.getState()));
    intakeMotor.setPower(Constants.INTAKE_POWER);
    //indexerServo.setPower(0.1);

    if (isWaiting) {
        indexerServo.setPower(Constants.INDEXER_INTAKE_POWER);
    }
    else{
       indexerServo.setPower(0);
    }

}
public boolean getTouchOne(){
       return touchOne.getState();
}
    public boolean getTouchTwo(){
        return touchTwo.getState();
    }

public void indexerStart(){
        indexerServo.setPower(Constants.INDEXER_INTAKE_POWER);
}
public void indexerStop(){
        indexerServo.setPower(0);
}
public void indexerReverse(){
        indexerServo.setPower(-Constants.INDEXER_INTAKE_POWER);
}
public void shootStart(){
        intakeMotor.setPower(Constants.INTAKE_POWER);
        indexerServo.setPower(Constants.INDEXER_INTAKE_POWER);
}public void shootStop(){
        intakeMotor.setPower(0);
        indexerServo.setPower(0);
    }
public void intakeStop(){
        intakeMotor.setPower(0);
        indexerServo.setPower(0);
}

    public void setIndexerPower(double power){
        indexerServo.setPower(power);
    }
    public void setIntakePower(double power){
        intakeMotor.setPower(power);
    }
}
