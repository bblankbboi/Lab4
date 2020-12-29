package Lab4.Clean;

import Lab4.Points.Point;

import java.util.List;

public class SizeClean extends Clean {
    public static int MAX_SIZE;

    public SizeClean(List<Point> _points) {
        super.points = _points;
    }

    public boolean func() {
        int size = 0;

        for (Point p : points) {
            size += p.getSize();
        }

        if (size > MAX_SIZE) {
            return true;
        }

        return false;
    }
}
