package org.firstinspires.ftc.teamcode.kinematics;

public class Position {

    /**
     * Positions
     */
    private double servo1_pos;
    private double servo2_pos;
    private int turntable_pos;

    /**
     * A general position for each motor and servo
     * @param servo1_pos the position of the first servo (servo val)
     * @param servo2_pos the position of the second servo (servo val)
     * @param turntable_pos the position of the turntable (encoder ticks)
     */
    public Position(double servo1_pos, double servo2_pos, int turntable_pos) {
        this.servo1_pos = servo1_pos;
        this.servo2_pos = servo2_pos;
        this.turntable_pos = turntable_pos;
    }

    /**
     * Getter
     * @return the servo1 position
     */
    public double getServo1_pos() {
        return servo1_pos;
    }

    /**
     * Getter
     * @return the servo2 position
     */
    public double getServo2_pos() {
        return servo2_pos;
    }

    /**
     * Getter
     * @return the turntable position
     */
    public int getTurntable_pos() {
        return turntable_pos;
    }
}
