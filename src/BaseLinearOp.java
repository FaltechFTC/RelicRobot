package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


/* 

   This BaseLinearOp class is not directly runnable from the driver control.
   This is a common team base class that builds on top of LinearOpMode.
   Rather, we extend BaseLinearOp class multiple times to expose different
   variations for the driver to select from.
   
   Example:  
   @Autonomous(name="AutoB1", group ="7079")
	public class AutoB1 extends BaseLinearOp {

		@Override
		public void runOpMode() {
			setBlue();
			setPosition(1);
			run();
		}
	}
*/

public class BaseLinearOp extends LinearOpMode {
    FaltechRobot robot = new FaltechRobot();
    ElapsedTime runtime = new ElapsedTime();
    boolean finished = false;
    int LCR = 0;     //  Left = -1,   Center=0,  Right= +1
	boolean isBlue=false;
	boolean isRed=false;
	boolean isPos1=false;
	boolean isPos2=false;
	
	
	// call setBlue() or setRed() to inform which alliance
	
	public void setBlue() {
		isBlue=true;
		isRed=false;
	}
	public void setRed() {
		isRed=true;
		isBlue=false;	
	}
	
	// call setPosition(1) or setPosition(2) to inform which starting position
	public void setPosition (int pos) {
		isPos1= pos==1;
		isPos2= pos==2;
	}
	
	// the final exposed class must define a runOpMode(), that calls this run() function to execute for all positions
	
    public void run() {
		if (isPos1==false && isPos2==false) throw Exception("Position must be 1 or 2");
		if (isBlue==false && isRed==false) throw Exception("Color must be specified");

		commonInit();
        waitForStart();

        if (opModeIsActive()) {
			
            telemetry.addData("Finished & Saw", "%s", LCR);
            telemetry.update();
			
			
			if (isBlue) runBlue();
			else runRed();
		}

		robot.robotStop();
		finished = true;
	}
	
	public void commonInit() {
        robot.init(hardwareMap, telemetry);

        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        while (!isStopRequested() && !robot.driveTrain.imu.isGyroCalibrated()) {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", robot.driveTrain.imu.getCalibrationStatus().toString());
        telemetry.update();

        robot.init(hardwareMap, telemetry);
        runtime.reset();
		
		// TODO: why isn't LCR being set?
	}

	public void RunBlue() {
		if (isPos1) runBlue1();
		else runBlue2();
	}

	public void RunRed() {
		if (isPos1) runRed1();
		else runRed2();
	}


	public void runBlue1() {

		runJewelArm();

		double inchesLCR[]={10, 17.5, 22};
		robot.driveTrain.goInches((inchesLCR[1+LCR] * 1.25), .25, 10);
		robot.driveTrain.turnDegreesLeft(.3,-75,5);
		robot.driveTrain.goInches(4, .25, 10);

		runDeliverGlyph();
    }
	
	public void runBlue2() {

		runJewelArm();

		robot.driveTrain.goInches(16.5, .3, 10);
		robot.driveTrain.goStrafe(-.5, 2500);

		double inchesLCR[]={40, 34, 28};
		robot.driveTrain.goStrafeInches(inchesLCR[1+LCR], 1, 5);
		robot.driveTrain.goInches(4, .5, 6);

		runDeliverGlyph();
	}
	
	public void runRed1() {

		runJewelArm();

		double inchesLCR[]={10, 17.5, 22};
		robot.driveTrain.goInches((inchesLCR[1+LCR] * 1.25), .25, 10);
		robot.driveTrain.turnDegreesRight(.3,75,5);
		robot.driveTrain.goInches(4, .25, 10);

		runDeliverGlyph();
	}

	public void runRed2() {
		runJewelArm();

		robot.driveTrain.goInches(16.5, .3, 10);
		robot.driveTrain.goStrafe(.5, 2500);

		double inchesLCR[]={-40, -34, -28};
		robot.driveTrain.goStrafeInches(inchesLCR[1+LCR], 1, 5);

		robot.driveTrain.goInches(4, .5, 6);

		runDeliverGlyph();
	}
	
	public void runJewelArm() {
		telemetry.addData("Jewel Arm", "now");
		telemetry.update();
		
		int sign;
		if (isBlue) sign=1;
		else sign= -1; // isRed
		
		int jewelDirection=sign*1;
		robot.jewelArm.moveJewelArmLeft(jewelDirection, 3000);
		sleep(250);

		if (isBlue && robot.jewelArm.sensorBlue.blue() < robot.jewelArm.sensorBlue.red())
			sign= -1;
		else if (isRed && robot.jewelArm.sensorBlue.blue() > robot.jewelArm.sensorBlue.red())
			sign= 1;
		
		robot.driveTrain.swivel(sign* -.25, 200);
		robot.jewelArm.moveJewelArmLeft(-jewelDirection, 2250);
		robot.driveTrain.swivel(sign* .25, 200);

	}		

	public void runDeliverGlyph() {
		telemetry.addData("Breaking Lock", "now");
		telemetry.update();
		sleep(1000);

		robot.glyphCollection.mtrHexFR.setPower(.5);
		sleep(1250);
		robot.glyphCollection.stop();

		telemetry.addData("Starting Flush", "now");

		robot.glyphCollection.collectionExpel(1);
		sleep(2500);

		telemetry.addData("Moving towards the cryptobox", "now");
		sleep(1000);

		robot.driveTrain.goInches(3, 0.25, 5);

		telemetry.addData("Moving out", "now");
		telemetry.update();

		robot.driveTrain.goInches(-3,.25,5);
		sleep(500);
		
		robot.driveTrain.goInches(-3,.25,5);
		robot.glyphCollection.stop();
		robot.driveTrain.stop();
	}
}
