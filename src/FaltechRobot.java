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
 * Created by Faltech Team on 11/24/2017.
 */

public class FaltechRobot {

    DriveTrain driveTrain = new DriveTrain();
    JewelArm jewelArm = new JewelArm();
    GlyphCollector glyphCollector = new GlyphCollector();
    Flipper flipper = new Flipper();
    RelicArm relicArm = new RelicArm();

    Telemetry telemetry = null;
    HardwareMap ahwMap = null;

    public void init(HardwareMap ahwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.ahwMap = ahwMap;

        telemetry.addData("Robot", "Init");
        telemetry.update();

        driveTrain.init(ahwMap, telemetry);
        jewelArm.init(ahwMap, telemetry);
        glyphCollector.init(ahwMap, telemetry);
        flipper.init(ahwMap, telemetry);
        relicArm.init(ahwMap, telemetry);

        telemetry.addData("Robot", "Finished_Init");
        telemetry.update();
    }

    public void robotStop() {
        telemetry.addData("Robot", "Stopping");
        telemetry.update();

        driveTrain.stop();
        jewelArm.stop();
        glyphCollector.stop();
        flipper.stop();
        relicArm.stop();

        telemetry.addData("Robot", "Stopped");
        telemetry.update();
    }
}

