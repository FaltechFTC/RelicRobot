package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Admin on 11/25/2017.
 */

public class JewelArm extends RobotPart {

    public CRServo csrvJewel = null;
    public ColorSensor sensorRed = null;
    public ColorSensor sensorBlue = null;

    public void init(HardwareMap ahwMap, Telemetry myTelemetry){
        super.init(ahwMap,myTelemetry);

        csrvJewel = ahwMap.crservo.get("csrvJewel");
        sensorRed = ahwMap.colorSensor.get("sensorRed");
        sensorBlue = ahwMap.colorSensor.get("sensorBlue");
    }

    private void jewelArmLeft(double speed){
        csrvJewel.setPower(-speed);
    }
    private void jewelArmRight(double speed){
        csrvJewel.setPower(speed);
    }
    public void stop(){
        csrvJewel.setPower(0);
    }

    public void moveJewelArmRight(double speed, double timeout){
        jewelArmRight(speed);
        mySleep(timeout);
        stop();
    }
    public void moveJewelArmLeft(double speed, double timeout){
        jewelArmLeft(speed);
        mySleep(timeout);
        stop();
    }
		
}
