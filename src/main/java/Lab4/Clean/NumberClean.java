package Lab4.Clean;

import Lab4.Points.Point;

import java.util.List;

public class NumberClean extends Clean {
    public static int MAX_SIZE;

    public NumberClean(List<Point> _points) {
        super.points = _points;
    }

    public boolean func() {
        if (super.points.size() > MAX_SIZE) {
            return true;
        }
        return false;
    }
}
