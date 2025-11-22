import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Chaikin {

    public static List<Point> algo(List<Point> points, int steps) {

        ArrayList<Point> result = new ArrayList<>(points);

        for (int i = 1; i <= steps; i++) {

            if (result.size() <= 2) break;

            ArrayList<Point> newPoints = new ArrayList<>();

            newPoints.add(points.get(0));

            for (int j = 0; j < result.size() - 1; j++) {

                final Point p0 = result.get(j);
                final Point p1 = result.get(j + 1);

                final Point first = new Point(
                    (int) Math.round(0.75 * p0.getX() + 0.25 * p1.getX()),
                    (int) Math.round(0.75 * p0.getY() + 0.25 * p1.getY())
                );

                final Point last = new Point(
                    (int) Math.round(0.25 * p0.getX() + 0.75 * p1.getX()),
                    (int) Math.round(0.25 * p0.getY() + 0.75 * p1.getY())
                );

                newPoints.add(first);
                newPoints.add(last);
            }

            newPoints.add(points.get(points.size() - 1));

            result = newPoints;
        }

        return result;
    }
}
