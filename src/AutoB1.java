package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutoB1", group ="7079")
public class AutoB1 extends BaseLinearOp {

    @Override
    public void runOpMode() {
		setBlue();
		setPosition(1);
		run();
    }
}
