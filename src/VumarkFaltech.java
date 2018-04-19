/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
//import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
//import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
//import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
/**
 * This OpMode illustrates the basics of using the Vuforia engine to determine
 * the identity of Vuforia VuMarks encountered on the field. The code is structured as
 * a LinearOpMode. It shares much structure with {@link ConceptVuforiaNavigation}; we do not here
 * duplicate the core Vuforia documentation found there, but rather instead focus on the
 * differences between the use of Vuforia for navigation vs VuMark identification.
 *
 * @see ConceptVuforiaNavigation
 * @see VuforiaLocalizer
 * @see VuforiaTrackableDefaultListener
 * see  ftc_app/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained in {@link ConceptVuforiaNavigation}.
 */

@Autonomous(name="Faltech b1 Vumark", group ="7079")
public class VumarkFaltech extends LinearOpMode {
    FaltechRobot robot = new FaltechRobot();
    ElapsedTime runtime = new ElapsedTime();
    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
//    VuforiaLocalizer vuforia;

    @Override public void runOpMode() {

        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
    //    int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
  //      VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        /*
         * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
         * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
         * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
         * web site at https://developer.vuforia.com/license-manager.
         *
         * Vuforia license keys are always 380 characters long, and look as if they contain mostly
         * random data. As an example, here is a example of a fragment of a valid key:
         *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
         * Once you've obtained a license key, copy the string from the Vuforia web site
         * and paste it in to your code onthe next line, between the double quotes.
         */
  //      parameters.vuforiaLicenseKey = "AbLmvmr/////AAAAGYJvpAWrd0nzlYNlPZ0i0VMSKm/XCzBgf89e3luaxNl4AJ7PPue20+ySqJ2ehq86y6+ksM1qLu2kIw+zZ7hsYU7M3ockxAGBIyoZQuivV24c2CZKOHYI9wprL3TseGiqbEYS3qUpvhVL1Hu3qObt/5J2mduIg3hqvAGA5sWclJ2967IPiZNNSoGKSVbBAvq/6sS0YEkYhxWXIGlVp+Fp4f0AmtL1bMEYmsyy3af2pupdcyuszAELYywk99AIEbLL12tNulk495py2AMBM+DBJRTQ9yiEf/b37p2n75beVbLp7IC+eScanU9iMpi1gmNmxWqPRYdg8IMBEhk/BMJUmOHW9iPdlwQh8/x7HbnksUoN";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
    //    parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
      //  this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
//        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
//        VuforiaTrackable relicTemplate = relicTrackables.get(0);
//        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        robot.init(hardwareMap, telemetry);
        while (!isStopRequested() && !robot.driveTrain.imu.isGyroCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", robot.driveTrain.imu.getCalibrationStatus().toString());
        telemetry.update();



        boolean finished = false;
        runtime.reset();
        int LCR = 0;
//        relicTrackables.activate();
        Orientation initialAngle;
        waitForStart();

        while ((opModeIsActive() && finished == true)) {
            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
//            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
//            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
//                /* Found an instance of the template. In the actual game, you will probably
//                 * loop until this condition occurs, then move on to act accordingly depending
//                 * on which VuMark was visible. */
//                telemetry.addData("VuMark", "%s visible", vuMark);
//                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
//                 * it is perhaps unlikely that you will actually need to act on this pose information, but
//                 * we illustrate it nevertheless, for completeness. */
//                //OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
////                telemetry.addData("Pose", format(pose));
////                telemetry.update();
//
//                telemetry.addData("saw",LCR);
//                telemetry.update();
//
//                if (vuMark == RelicRecoveryVuMark.LEFT){
//                    LCR = -1;
//                }
//                else if (vuMark == RelicRecoveryVuMark.RIGHT){
//                    LCR = 1;
//                }
//
//                /* We further illustrate how to decompose the pose into useful rotational and
//                 * translational components */
//                /*if (pose != null) {
//                    VectorF trans = pose.getTranslation();
//                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
//
//                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
//                    double tX = trans.get(0);
//                    double tY = trans.get(1);
//                    double tZ = trans.get(2);
//
//                    // Extract the rotational components of the target relative to the robot
//                    double rX = rot.firstAngle;
//                    double rY = rot.secondAngle;
//                    double rZ = rot.thirdAngle;
//                }*/
//
//                finished = true;
//            }
//            else {
//                telemetry.addData("VuMark", "not visible");
//            }
//            telemetry.update();
//        }
        initialAngle   = robot.driveTrain.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double startAngle = initialAngle.firstAngle;

        telemetry.addData("Finished & Saw", "%s", LCR);
        telemetry.update();

            robot.jewelArm.moveJewelArmRight(1, 3000);
            robot.jewelArm.stop();
            sleep(250);
            if (robot.jewelArm.sensorBlue.blue() > robot.jewelArm.sensorBlue.red()) {
                robot.driveTrain.swivel(.25, 200);
                robot.jewelArm.moveJewelArmLeft(1, 2250);
                robot.driveTrain.swivel(-.25, 200);
            } else {
                robot.driveTrain.swivel(-.25, 200);
                robot.jewelArm.moveJewelArmLeft(1, 2250);
                robot.driveTrain.swivel(.25, 200);
            }
        telemetry.addData("Finished Jewel","Going Off");
        telemetry.update();
        double currentAngle = initialAngle.firstAngle;

            robot.driveTrain.goInches((robot.glyphDistance[1 + LCR] * -1.3),.25,10);
//            if (LCR == -1){
//                robot.driveTrain.goInches(robot.glyphDistance[0],.25,10);
//            }
//            else if (LCR == 0){
//
//            }
//            else{
//                robot.driveTrain.goInches(robot.glyphDistance[2],.25,10);
//            }
        telemetry.addData("Finished Driving","Now Turning");
        telemetry.addData("Start Angle", startAngle);
        telemetry.addData("Current Angle", currentAngle);
        telemetry.update();

        robot.driveTrain.turnDegreesRight(.5, 90, 5);
//          robot.glyphColllection.elevatorUp(.8);
//          sleep(2000);

        robot.driveTrain.goInches(6,.25,10);

        telemetry.addData("Breaking Lock","now");
        telemetry.update();
        sleep(1000);

        robot.glyphColllection.mtrHexFR.setPower(.5);
        sleep(1250);
        robot.glyphColllection.stop();

        telemetry.addData("Starting Flush","now");
        sleep(1000);
        robot.glyphColllection.stop();

        robot.glyphColllection.collectionExpel(1);
        sleep(2500);

        telemetry.addData("Moving towards the cryptobox","now");
        sleep(1000);

        robot.driveTrain.goInches(3, 0.25, 5);
        robot.driveTrain.stop();


        telemetry.addData("Moving out","now");
        telemetry.update();

        robot.driveTrain.goInches(-6, 0.25, 5);
        robot.driveTrain.stop();
        sleep(500);
        robot.glyphColllection.stop();





//
//        telemetry.addData("Breaking Lock","now");
//        telemetry.update();
//        sleep(500);
//
//        robot.glyphColllection.mtrHexFR.setPower(.5);
//        sleep(1250);
//        robot.glyphColllection.stop();
//
//        telemetry.addData("Starting Flush","now");
//        sleep(500);
//
//        robot.glyphColllection.collectionExpel(1);
//        sleep(2500);
//        robot.glyphColllection.stop();
//
//        telemetry.addData("Moving towards the cryptobox","now");
//        sleep(500);
//
//        robot.driveTrain.goInches(3, 0.25, 5);
//        robot.driveTrain.stop();
//
//        telemetry.addData("Starting Flush","now");
//        sleep(500);
//
//        robot.glyphColllection.collectionExpel(1);
//
//        telemetry.addData("Moving out","now");
//        sleep(1500);
//
//        robot.driveTrain.goInches(-6, 0.25, 5);
//        robot.driveTrain.stop();
//        robot.glyphColllection.stop();


            robot.robotStop();
            telemetry.addData("Finished Auto","Switch to Tele-Op");
            telemetry.update();
            sleep(250);
            telemetry.addData("Testing","Code Building");
            telemetry.update();
           finished = true;

        }
    }
//    String format(OpenGLMatrix transformationMatrix) {
//        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
//    }
}