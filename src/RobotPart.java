package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;


public abstract class RobotPart {

    // todo: write your code here
    protected Telemetry privateTelemetry;
    private ElapsedTime runtime;

    public void init(HardwareMap ahwMap, Telemetry myTelemetry) {
        privateTelemetry = myTelemetry;
        privateTelemetry.addData(this.getClass().getName(), "Init");
        privateTelemetry.update();

    }

    abstract public void stop();

    protected void mySleep(double timeOut) {
        double elapsedSleep = 0;
        while (elapsedSleep < timeOut) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            elapsedSleep += 10;
        }
    }
}
