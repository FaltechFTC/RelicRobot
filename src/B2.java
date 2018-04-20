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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

@Autonomous(name="B2", group ="7079")
public class B2 extends LinearOpMode {
    FaltechRobot robot = new FaltechRobot();
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap, telemetry);

        while (!isStopRequested() && !robot.driveTrain.imu.isGyroCalibrated()) {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", robot.driveTrain.imu.getCalibrationStatus().toString());
        telemetry.update();

        robot.init(hardwareMap, telemetry);
        boolean finished = false;
        runtime.reset();
        int LCR = 0;
        waitForStart();

        if (opModeIsActive()) {
            telemetry.addData("Finished & Saw", "%s", LCR);
            telemetry.addData("testing", "code building");
            telemetry.update();

            robot.jewelArm.moveJewelArmLeft(1, 3000);
            robot.jewelArm.stop();
            sleep(250);

            if (robot.jewelArm.sensorBlue.blue() > robot.jewelArm.sensorBlue.red()) {
                robot.driveTrain.swivel(-.25, 200);
                robot.jewelArm.moveJewelArmLeft(-1, 2250);
                robot.driveTrain.swivel(.25, 200);

            } else {
                robot.driveTrain.swivel(.25, 200);
                robot.jewelArm.moveJewelArmLeft(-1, 2550);
                robot.driveTrain.swivel(-.25, 200);
            }

            robot.driveTrain.goInches(16.5, .3, 10);
            robot.driveTrain.goStrafe(-.5, 2500);

            if (LCR == -1) {
                robot.driveTrain.goStrafeInches(40, 1, 5);
            } else if (LCR == 0) {
                robot.driveTrain.goStrafeInches(34, 1, 5);
            } else {
                robot.driveTrain.goStrafeInches(28, 1, 5);
            }

//            robot.driveTrain.turnDegreesRight(.25,90,5);
//            robot.glyphCollection.elevatorUp(.8);
//            sleep(2000);

            robot.driveTrain.goInches(4, .5, 6);

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
            telemetry.addData("Testing Build", "now");
            telemetry.update();

            robot.driveTrain.goInches(-3,.25,5);
            //robot.driveTrain.goPulsate(12, 0.2, -1);
            sleep(500);
            robot.driveTrain.goInches(-3,.25,5);
            robot.glyphCollection.stop();
            robot.driveTrain.stop();
            finished = true;
        }
    }
}

