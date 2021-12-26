package PointProcessor;

public class Parser {
    private static final String lineSeparator = ",";
    private static final int coordinatesCount = 2;
    private static final int xIndex = 0;
    private static final int yIndex = 1;

    public static boolean tryParsePoint(String line, ValueWrapper<Point> pointWrapper) {
        if (line == null)
            return false;

        String[] lineParts = line.split(lineSeparator);
        if (lineParts.length != coordinatesCount)
            return false;

        ValueWrapper<Double> xWrapper = new ValueWrapper<Double>();
        ValueWrapper<Double> yWrapper = new ValueWrapper<Double>();

        String xLine = String.valueOf(lineParts[xIndex]); //!!!
        String yLine = String.valueOf(lineParts[yIndex]); //!!!

        if (tryParseCoordinate(xLine, xWrapper) && tryParseCoordinate(yLine, yWrapper)) {
            double x = xWrapper.getValue();
            double y = yWrapper.getValue();

            pointWrapper.setValue(new Point(x, y));
            return true;
        }

        return false;
    }

    private static boolean tryParseCoordinate(String value, ValueWrapper<Double> coordinateWrapper) {
        try {
            double coordinate = Double.parseDouble(value);
            coordinateWrapper.setValue(coordinate);

            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
