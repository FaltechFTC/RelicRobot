package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Faltech Team on 11/29/2017.
 */

public class Flipper extends RobotPart {
    public DcMotor mtrFlipper = null;
    double flipStartPosition = 0;

    public void init(HardwareMap ahwMap, Telemetry myTelemetry) {
        super.init(ahwMap, myTelemetry);
        mtrFlipper = ahwMap.dcMotor.get("mtrFlipper");
        mtrFlipper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mtrFlipper.setDirection(DcMotor.Direction.REVERSE);

        // TODO: Ask Rohit why this is commented out.
        // There used to be a concept of picking the "zero position" based on actual physical zero of the flipper.
        //flipStartPosition = robot.flipper.mtrFlipper.getCurrentPosition();
        //telemetry.addData("mtrFlipIntPos",flipStartPosition);    
        //telemetry.update();
    }

    public void flipperUp(double position) {
        mtrFlipper.setTargetPosition((int) (-position));
    }

    public void flipperDown(double position) {
        mtrFlipper.setTargetPosition((int) (-position));
    }

    public void stop() {
        mtrFlipper.setPower(0);
    }

    public void mtrFlipperUp(double position, double speed, double sleep) {
        flipperUp(-position);
        mtrFlipper.setPower(speed);
        mySleep(sleep);
        stop();
    }

    public void mtrFlipperDown(double position, double speed, double sleep) {
        flipperDown(-position);
        mtrFlipper.setPower(speed);
        mySleep(sleep);
        stop();
    }

    public void processInput(double flipPos_D) {
        double flipCurrentPosition = mtrFlipper.getCurrentPosition();

        //Stopping when at rest
        if (Math.abs(flipStartPosition - flipCurrentPosition) < 2 && flipPos_D == 0) {
            stop();
        } else {
            double newPos = flipStartPosition + 175 * flipPos_D;
            double powerVar = (Math.abs(newPos - flipCurrentPosition) / 100);
            flipperUp(-newPos);
            mtrFlipper.setPower(-(0.5 * powerVar) - 0.2);
        }
    }
}
