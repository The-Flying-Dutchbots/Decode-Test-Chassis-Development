package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.ArcadeDriveSys;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSys;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;

@Autonomous
public class CloseRedAuto extends OpMode {

    public enum AutoState{
        REV_SHOOTER_FOR_CLOSE,
        SHOOT_CLOSE,
        SHOOTING_WAIT_ONE,
        MOVE_TO_LINE_ONE_STRAIGHT,
        MOVE_TO_LINE_ONE_STRAIGHT_WAIT,
        MOVE_TO_LINE_ONE_STRAIGHT_DELAY,

        MOVE_TO_LINE_ONE_ROTATE,
        MOVE_TO_LINE_ONE_AND_INTAKE,
        MOVE_TO_SHOOTING_POSITION_ONE_STRAIGHT,
        MOVE_TO_SHOOTING_POSITION_ONE_ROTATE,
        SHOOT_MID,

    }
    ElapsedTime ShootTimer = new ElapsedTime();
    ElapsedTime UtilityTimer = new ElapsedTime();
    IntakeSys intakeSys = new IntakeSys();
    ShooterSys shooterSys = new ShooterSys();
    ArcadeDriveSys driveSys = new ArcadeDriveSys();
    private AutoState autoState = AutoState.REV_SHOOTER_FOR_CLOSE;

    @Override
    public void init(){
        intakeSys.init(hardwareMap);
        shooterSys.init(hardwareMap);
        driveSys.autoInit(hardwareMap);


    }

    public void loop(){
        switch (autoState){
            case REV_SHOOTER_FOR_CLOSE:
                shooterSys.startShooting();
                ShootTimer.reset();
                autoState = AutoState.SHOOT_CLOSE;
                break;

            case SHOOT_CLOSE:
                if(ShootTimer.seconds() > 1.0){
                    intakeSys.shootStart();
                    UtilityTimer.reset();
                    autoState = AutoState.SHOOTING_WAIT_ONE;
                }
                break;

            case SHOOTING_WAIT_ONE:
                if(UtilityTimer.seconds() > 2.5){
                    shooterSys.stopShooting();
                    intakeSys.shootStop();
                    autoState = AutoState.MOVE_TO_LINE_ONE_STRAIGHT;
                }
                break;

            // --- Move forward 20 inches ---
            case MOVE_TO_LINE_ONE_STRAIGHT:
                driveSys.moveFowardInchesAtPower(20, 0.5);  // sets RUN_TO_POSITION
                autoState = AutoState.MOVE_TO_LINE_ONE_STRAIGHT_WAIT;
                break;

            // --- Wait until motors finish moving ---
            case MOVE_TO_LINE_ONE_STRAIGHT_WAIT:
                if (!driveSys.isBusy()) {
                    UtilityTimer.reset();   // start 0.5s delay
                    autoState = AutoState.MOVE_TO_LINE_ONE_STRAIGHT_DELAY;
                }
                break;

            // --- Wait 0.5s after reaching target ---
            case MOVE_TO_LINE_ONE_STRAIGHT_DELAY:
                if (UtilityTimer.seconds() > 0.5) {
                    autoState = AutoState.MOVE_TO_LINE_ONE_ROTATE;  // next action
                }
                break;

        }

telemetry.addData("Auto State", autoState);
shooterSys.updateHood();
    }


}
