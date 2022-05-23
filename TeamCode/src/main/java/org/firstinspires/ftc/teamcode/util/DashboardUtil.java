package org.firstinspires.ftc.teamcode.util;


import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.config.ArmConfiguration;
import org.firstinspires.ftc.teamcode.kinematics.Position;
import org.firstinspires.ftc.teamcode.kinematics.PositionFinder;

import java.util.List;

/**
 * Set of helper functions for drawing Road Runner paths and trajectories on dashboard canvases.
 */
@Config
public class DashboardUtil {
    private static final double DEFAULT_RESOLUTION = 2.0; // distance units; presumed inches
    public static int triangleX = 0;
    public static int triangleY = 0;


    public static void drawArmTriangle(Canvas canvas, Position pos) {
        // Bottom of triangle
        canvas.strokeLine(triangleX, triangleY, triangleX - ArmConfiguration.armBeamLength, 0);

        // Calculate the height + angle
        double internalAngle = PositionFinder.calcServoOneAngle(pos.getServo1_pos());


        canvas.strokeLine(triangleX - ArmConfiguration.armBeamLength, 0, 1, 1);



        // canvas.strokeLine(x1, y1, x2, y2);
    }
}