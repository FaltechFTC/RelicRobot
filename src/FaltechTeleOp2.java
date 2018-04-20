package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**

  Controls Summary:
	Driving 
		Forward/Back = gamepad1.right_stick_y
		Turn = gamepad1.left_stick_x
		Strafe = gamepad1.right_stick_x

	Collector  In = gamepad2.dpad_up    Out= gamepad2.dpad_down   Rotate = gamepad2.left_stick_x
	Elevator  Up = gamepad2.right_bumper   Down = gamepad2.left_bumper;

	Flip Glyph = gamepad2.right_trigger

	Jewel Arm LEFT = gamepad1.dpad_left  RIGHT = gamepad1.dpad_right
		
 */
@TeleOp(name = "Faltech TeleOp2D", group = "7079")
public class FaltechTeleOp2 extends OpMode{

    static double JOYSTICK_DEADZONE_LIMIT = 0.1;
	static boolean ENABLE_LIGHTS = false;

    FaltechRobot robot = new FaltechRobot();
    int loopCount = 0;
	
	
	/* Initialize the hardware variables.
	 * The init() method of the hardware class does all the work here
	 */
    @Override
    public void init() {

		robot.driveTrain.enableIMU=false;
		robot.init(hardwareMap,telemetry);

		// Send telemetry message to signify robot waiting;
		telemetry.addData("Say", "Hello Driver");   
		telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {
		loopCount++;
        telemetry.addData("Loop #", loopCount);
        telemetry.update();

		doDriving();
		doFlipping();
		doCollector();
		doJewelArm();
		doRelicArm();

		if (ENABLE_LIGHTS)  doLights();

		if (false) {
			telemetry.addData("Right Stick Y ", FwdBack_D);
			telemetry.addData("Left Stick X", Turn_D);
			telemetry.addData("Right Stick Y", gamepad2.right_stick_y);
			telemetry.addData("SrvRelicPosition",robot.relicArm.srvRelicArm.getPosition());
			telemetry.addData("mtrFlipperCP", robot.flipper.mtrFlipper.getCurrentPosition() );
			telemetry.update();
		}
    }

	void doDriving() {
		double straight = deadzone(-gamepad1.right_stick_y);
		double strafe = deadzone(-gamepad1.right_stick_x);
		double turn = deadzone(gamepad1.left_stick_x);

		robot.driveTrain.drive(straight, strafe, turn);
	}


	void doFlipping() {
		robot.flipper.processInput(gamepad2.right_trigger);
	}
		
	void doCollector() {
        boolean intake = gamepad2.dpad_up;
        boolean eject = gamepad2.dpad_down;
		double rotate = deadzone(gamepad2.left_stick_x);
		
        if (intake)  robot.glyphCollection.collectionIntake(1);
        else if (eject) robot.glyphCollection.collectionExpel(1);
		else if (rotate>0) robot.glyphCollection.collectionRotate(rotate);
        else robot.glyphCollection.collectionIntake(0);
		
		boolean elevatorUp = gamepad2.right_bumper;
		boolean elevatorDown = gamepad2.left_bumper;

		if (elevatorUp) robot.glyphCollection.elevatorUp(0.8);
        else if (elevatorDown) robot.glyphCollection.elevatorUp(-0.8);
        else if (gamepad2.y) robot.glyphCollection.elevatorUp(0.16);
        else robot.glyphCollection.elevatorUp(0);
	}
		
	void doJewelArm() {
		boolean left= gamepad1.dpad_left;
		boolean right= gamepad1.dpad_right;
		
        if (left) robot.jewelArm.jewelArmLeft(1);
        else if (right) robot.jewelArm.jewelArmRight(1);
		else robot.jewelArm.stop();
	}

	void doRelicArm() {

		// Claw  (open/close)
		if (gamepad2.x) 
             robot.relicArm.srvClench();
        else if (gamepad2.b) 
             robot.relicArm.srvOpen();
        else
             robot.relicArm.srvRelicArm.setPosition(0);
        
		// Wrist (raise/lower)
        if (gamepad2.dpad_left)
             robot.relicArm.srv1Clench();
        else if (gamepad2.dpad_right)
             robot.relicArm.srv1Open();
        else
             robot.relicArm.srvRelicArm1.setPosition(0.5);
	}

	void doLights() {
		robot.driveTrain.mtrLight.setPower(1);   // TODO: why is this setting power twice every loop???  could be causing issues!
		if (gamepad1.right_trigger > 0.3)
			robot.driveTrain.mtrLight.setPower(1 - gamepad1.right_trigger);
	}
	
	
	/* take the input from the joystick, and zero it out if it is in the deadzone.
		otherwise, return the original value
	*/
	double deadzone(double input) {
		if (Math.abs(input) <= JOYSTICK_DEADZONE_LIMIT )
			input=0;
		return input;
	}
			
}
