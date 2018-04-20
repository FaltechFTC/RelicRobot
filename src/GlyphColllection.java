package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.robot.Robot;
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

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Created by Faltech Team on 11/28/2017.
 */

public class GlyphCollection extends RobotPart {
    public DcMotor mtrHexFR = null;
    public DcMotor mtrHexFL = null;
    public CRServo csrvElevator = null;
    public CRServo csrvElevator2 = null;
    
    public void init(HardwareMap ahwMap, Telemetry myTelemetry) {
        super.init(ahwMap,myTelemetry);
        
        mtrHexFL = ahwMap.dcMotor.get("mtrHexFL");
        mtrHexFR = ahwMap.dcMotor.get("mtrHexFR");
        csrvElevator = ahwMap.crservo.get("csrvElevator");
        csrvElevator2 = ahwMap.crservo.get("csrvElevator2");
        
        mtrHexFL.setDirection(DcMotor.Direction.FORWARD);
        mtrHexFR.setDirection(DcMotor.Direction.REVERSE);

        mtrHexFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mtrHexFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    // private ElapsedTime runtime = new ElapsedTime();

    // private void timeOutExitCollect(double timeout){
    //     while ((runtime.seconds() < (timeout*1000))
    //             && mtrHexFL.isBusy() && mtrHexFL.isBusy()) {

    //         // Display it for the driver.
    //         privateTelemetry.addData("Path1",  "Running Collection");
    //         privateTelemetry.update();
    //     }
    // }
    public void stop(){
        mtrHexFL.setPower(0);
        mtrHexFR.setPower(0);
        csrvElevator.setPower(0);
        csrvElevator2.setPower(0);        
    }
     public void collectionIntake(double speed){
        mtrHexFL.setPower(speed * .9);
        mtrHexFR.setPower(speed);
     }
     public void collectionExpel(double speed){
         mtrHexFL.setPower(-speed);
         mtrHexFR.setPower(-speed);
     }
     public void elevatorUp(double speed){
         csrvElevator.setPower(-speed);
         csrvElevator2.setPower(speed);
     }
     public void elevatorDown(double speed){
         csrvElevator.setPower(speed);
         csrvElevator2.setPower(-speed);
     }
     
     
    public void collectionRotate(double speed){
        mtrHexFL.setPower(speed);
        mtrHexFR.setPower(-speed);
    }

    // public void glyphIntake(double speed, double timeout){
    //     runtime.reset();
    //     collectionIntake(speed);
    //     timeOutExitCollect(timeout);
    // }
    // public void glyphExpel(double speed, double timeout){
    //     runtime.reset();
    //     collectionExpel(speed);
    //     timeOutExitCollect(timeout);
    // }

}
