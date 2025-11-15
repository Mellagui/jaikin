import java.util.ArrayList;
import java.util.List;

public class Chaikin {
    public static List<int[]> algo(List<int[]> points, int steps) {
        ArrayList<int[]> result = new ArrayList<>(points);

        for (int i = 1; i <= steps; i++) {
            if (result.size() <= 2) break;

            ArrayList<int[]> newPoints = new ArrayList<>();
            newPoints.add(points.getFirst());

            for (int j = 0; j < result.size() - 1; j++) {

                int[] p0 = result.get(j);
                int[] p1 = result.get(j + 1);

                int[] first = {(int) Math.round(0.75 * p0[0] + 0.25 * p1[0]), (int) Math.round(0.75 * p0[1] + 0.25 * p1[1])};
                int[] last = {(int) Math.round(0.25 * p0[0] + 0.75 * p1[0]), (int) Math.round(0.25 * p0[1] + 0.75 * p1[1])};

                newPoints.add(first);
                newPoints.add(last);
            }

            newPoints.add(points.getLast());
            result = newPoints;
        }

        return result;
    }
}
