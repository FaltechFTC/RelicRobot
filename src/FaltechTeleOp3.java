package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Admin on 12/3/2017.
 *
 */
@TeleOp(name = "Faltech TeleOp 3", group = "7079")
public class FaltechTeleOp3 extends OpMode{
    FaltechRobot robot = new FaltechRobot();
    double flipStartPosition = 0;
    double deadzone_D = 0.1;
    int loop_count = 0;
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */

        robot.driveTrain.enableIMU=false;
        robot.init(hardwareMap,telemetry);
        //flipStartPosition = robot.flipper.mtrFlipper.getCurrentPosition();
        // // telemetry.addData("mtrFlipIntPos",flipStartPosition);    
        //telemetry.update();
        // Send telemetry message to signify robot waiting;
        // telemetry.addData("Say", "Hello Driver");   
        // telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }
    public double WeightAvg(double x, double y, double z) {
        double speed_D = 0;


        if ((Math.abs(x) + Math.abs(y) + Math.abs(z))  != 0.0) {
            speed_D = ((x * Math.abs(x)) + (y * Math.abs(y)) + (z * Math.abs(z)))
                    / (Math.abs(x) + Math.abs(y) + Math.abs(z));
        }
        return (speed_D);
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
        telemetry.addData("Right START ", gamepad2.right_stick_y);
        telemetry.addData("Loop couunt ", loop_count++);
        telemetry.update();
        boolean Reverse = false;
        double FwdBack_D = -gamepad1.right_stick_y;
        double Turn_D = gamepad1.left_stick_x;
        double Strafe_D = -gamepad1.right_stick_x;
        // double StrafeR_D = gamepad1.right_trigger;
        // double StrafeL_D = gamepad1.left_trigger;
        double FlipUp_D = gamepad2.right_trigger;
        double FlipDown_D = gamepad2.left_trigger;
        double Elevator_D = -gamepad2.left_stick_y;
        
        double flipCurrentPosition = robot.flipper.mtrFlipper.getCurrentPosition();
        
        boolean CollectorIn_B = gamepad2.dpad_up;
        boolean CollectorEject_B = gamepad2.dpad_down;

        robot.driveTrain.mtrLight.setPower(1);
        if (gamepad1.right_trigger > 0.3){
        robot.driveTrain.mtrLight.setPower(1 - gamepad1.right_trigger);
        }


        if (Math.abs(FwdBack_D) > deadzone_D){
            FwdBack_D = FwdBack_D;
        }
        else {
            FwdBack_D = 0;
        }
        if (Math.abs(Strafe_D) > deadzone_D ){
            Strafe_D = Strafe_D;
        }
        else {
            Strafe_D = 0;
        }
        
        if (Math.abs(Turn_D) > deadzone_D){
            Turn_D = Turn_D;
        }
        else{
            Turn_D = 0;
        }
        robot.driveTrain.mtrFR.setPower(WeightAvg(FwdBack_D,Strafe_D,-Turn_D));
        robot.driveTrain.mtrFL.setPower(WeightAvg(FwdBack_D,-Strafe_D,Turn_D));
        robot.driveTrain.mtrBR.setPower(WeightAvg(FwdBack_D,-Strafe_D,-Turn_D));
        robot.driveTrain.mtrBL.setPower(WeightAvg(FwdBack_D,Strafe_D,Turn_D));

        

        //Flipper
        double flipPos_D =  gamepad2.right_trigger;
        
//Stopping when at rest
        if(Math.abs(flipStartPosition - flipCurrentPosition) < 2 &&
            flipPos_D == 0){
            robot.flipper.stop();

        }
        else{
            double newPos = flipStartPosition + 175 * flipPos_D;
            double powerVar = (Math.abs(newPos - flipCurrentPosition)/100);
            robot.flipper.flipperUp(-newPos);
            
            robot.flipper.mtrFlipper.setPower(-(0.5 * powerVar) - 0.2);
        }
        //Jewel Arm
        if (gamepad1.dpad_left){
            robot.jewelArm.csrvJewel.setPower(-1);
        }
        else if (gamepad1.dpad_right){
            robot.jewelArm.csrvJewel.setPower(1);
        }
        else {
            robot.jewelArm.csrvJewel.setPower(0);
        }


        //Collector
        if (CollectorIn_B){
            robot.glyphColllection.collectionIntake(1);
        }

        else if (CollectorEject_B){
            robot.glyphColllection.collectionExpel(1);
        }
        else {
            robot.glyphColllection.mtrHexFL.setPower(0);
            robot.glyphColllection.mtrHexFR.setPower(0);
        }        if (Math.abs(-gamepad2.left_stick_x) > deadzone_D){
            robot.glyphColllection.collectionClockwise(gamepad2.left_stick_x);
        }
        
        
        if (gamepad2.right_bumper){
            telemetry.addData("Elevator status","Going Up");
            telemetry.update();
            robot.glyphColllection.csrvElevator.setPower(-0.8);
            robot.glyphColllection.csrvElevator2.setPower(0.8);
        } 
        else if (gamepad2.left_bumper){
            robot.glyphColllection.csrvElevator.setPower(0.8);
            robot.glyphColllection.csrvElevator2.setPower(-0.8);
        }
        else if (gamepad2.y){
            robot.glyphColllection.csrvElevator.setPower(-0.16);
            robot.glyphColllection.csrvElevator2.setPower(0.16);
        }
        else {
            robot.glyphColllection.csrvElevator.setPower(0);
            robot.glyphColllection.csrvElevator2.setPower(0);
        }
//Relic Arm
         if (gamepad2.x){
             robot.relicArm.srvClench();
         }
         else if (gamepad2.b){
             robot.relicArm.srvOpen();
         }
         else{
             robot.relicArm.srvRelicArm.setPosition(0);
         }
        
         if (gamepad2.dpad_left){
             robot.relicArm.srv1Clench();
         }
         else if (gamepad2.dpad_right){
             robot.relicArm.srv1Open();
         }
         else{
             robot.relicArm.srvRelicArm1.setPosition(0.5);
         }
        
//         boolean armMove = false;
//         if (gamepad1.a){
//             armMove = true;
//         }
//         else{
//             armMove = false;
//         }
        

//        telemetry.addData("Right Stick Y ", FwdBack_D);
//        telemetry.addData("Left Stick X", Turn_D);
//        telemetry.addData("Right Stick Y", gamepad2.right_stick_y);
//        //telemetry.addData("SrvRelicPosition",robot.relicArm.srvRelicArm.getPosition());
//        telemetry.addData("mtrFlipperCP", robot.flipper.mtrFlipper.getCurrentPosition() );
//        telemetry.update();
        
    }
}
