package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "AutoR2", group = "7079")
public class AutoR2 extends BaseLinearOp {

    @Override
    public void runOpMode() {
        setRed();
        setPosition(2);
        run();
    }
}
