package org.firstinspires.ftc.teamcode.util;

public class Conversion {

    /**
     * Inches to mm
     * @param inches the inches you want to convert
     * @return millimeters
     */
    public double inchesToMillimeters(double inches) {
        return inches * 25.4;
    }

    /**
     * mm to Inches
     * @param mm the mm you want to convert
     * @return inches
     */
    public double millimetersToInches(double mm) {
        return mm / 25.4;
    }

}
