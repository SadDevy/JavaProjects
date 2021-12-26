package PointProcessor;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class Formatter {
    public static String format(Point point) {
        if (point == null)
            return null;

        ValueWrapper<Double> fractionalXWrapper = new ValueWrapper<Double>();
        double integerX = getIntegerPart(point.getX(), fractionalXWrapper);

        ValueWrapper<Double> fractionalYWrapper = new ValueWrapper<Double>();
        double integerY = getIntegerPart(point.getY(), fractionalYWrapper);

        String formattedPoint = MessageFormat.format("X: {0, number,##0}{1, number,.0###} X: {2, number,##0}{3, number,.0###}",
                (int)integerX, fractionalXWrapper.getValue(),
                (int)integerY, fractionalYWrapper.getValue());

        return formattedPoint;
    }

    private static double getIntegerPart(double value, ValueWrapper<Double> fractionalPartWrapper) {
        double integerPart = Math.floor(value);
        double fractionalPart = value - integerPart;

        fractionalPartWrapper.setValue(fractionalPart);
        return integerPart;
    }
}