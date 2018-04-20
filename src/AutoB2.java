package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "AutoB2", group = "7079")
public class AutoB2 extends BaseLinearOp {

    @Override
    public void runOpMode() {
        setBlue();
        setPosition(2);
        run();
    }
}
