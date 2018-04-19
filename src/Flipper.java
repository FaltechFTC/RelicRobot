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
 * Created by Admin on 11/29/2017.
 */

public class Flipper extends RobotPart {
    public DcMotor mtrFlipper = null;

    public void init(HardwareMap ahwMap, Telemetry myTelemetry){
        super.init(ahwMap,myTelemetry);
        mtrFlipper = ahwMap.dcMotor.get("mtrFlipper");
        mtrFlipper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mtrFlipper.setDirection(DcMotor.Direction.REVERSE);
        
    }
    
    public void flipperUp(double position){
        mtrFlipper.setTargetPosition((int) (-position));
    }
    
    public void flipperDown(double position){
        mtrFlipper.setTargetPosition((int) (-position));
    }

    public void stop(){
        mtrFlipper.setPower(0);
    }
    
    public void mtrFlipperUp(double position,double speed, double sleep){
        flipperUp(-position);
        mtrFlipper.setPower(speed);
        mySleep(sleep);
        stop();
    }
    public void mtrFlipperDown(double position,double speed,double sleep){
        flipperDown(-position);
        mtrFlipper.setPower(speed);
        mySleep(sleep);
        stop();
    }
}
