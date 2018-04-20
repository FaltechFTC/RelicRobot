package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Admin on 12/4/2017.
 */
public class RelicArm extends RobotPart {
    public Servo srvRelicArm = null;
    public Servo srvRelicArm1 = null;
    public DcMotor mtrRelicArm = null;

    public void init(HardwareMap ahwMap, Telemetry myTelemetry) {
        super.init(ahwMap, myTelemetry);

        srvRelicArm = ahwMap.servo.get("srvRelicArm");
        srvRelicArm1 = ahwMap.servo.get("srvRelicArm1");
    }

    public void srvClench() {
        srvRelicArm.setPosition(0.5);
    }

    public void srvOpen() {
        srvRelicArm.setPosition(1);
    }

    public void srv1Clench() {
        srvRelicArm1.setPosition(0);
    }

    public void srv1Open() {
        srvRelicArm1.setPosition(1);
    }

    public void stop() {
        srvRelicArm.setPosition(0);
        srvRelicArm1.setPosition(0.5);

    }


}
