package Lab4.Clean;

import Lab4.Points.Point;

import java.util.Date;
import java.util.List;

public class DateClean extends Clean {
    private static Date date;

    public DateClean(List<Point> _points) {
        super.points = _points;
    }

    public boolean func() {
        if (super.points.get(0).getDate().after(date)) {
            return false;
        }
        return true;
    }
}
