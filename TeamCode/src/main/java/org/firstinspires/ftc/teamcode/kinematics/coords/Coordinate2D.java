package org.firstinspires.ftc.teamcode.kinematics.coords;

public class Coordinate2D {

    private double x;
    private double y;

    public Coordinate2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Subtract cords
     * @param coordinate2D
     * @return
     */
    public Coordinate2D subtract(Coordinate2D coordinate2D) {
        return new Coordinate2D(x - coordinate2D.getX(), y - coordinate2D.getY());
    }

    /**
     * Multiply cords
     * @param coordinate2D
     * @return
     */
    public Coordinate2D multiply(Coordinate2D coordinate2D) {
        return new Coordinate2D(x * coordinate2D.getX(), y * coordinate2D.getY());
    }

    /**
     * Add cords
     * @param coordinate2D
     * @return
     */
    public Coordinate2D add(Coordinate2D coordinate2D) {
        return new Coordinate2D(x + coordinate2D.getX(), y + coordinate2D.getY());
    }

    /**
     * Getter
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * Getter
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * Setter
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Setter
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

}
