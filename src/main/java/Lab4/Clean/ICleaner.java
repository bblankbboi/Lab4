package Lab4.Clean;

import Lab4.Manager;
import Lab4.Points.Point;
import Lab4.Points.PointType;

import java.util.ArrayList;
import java.util.List;

public class ICleaner {
    private static ICleaner iCleaner;
    private static List<Point> pointsToDel = new ArrayList<>();
    private static CleanStyle style;

    public static ICleaner getInstance() {
        if (iCleaner == null) {
            iCleaner = new ICleaner();
        }
        return iCleaner;
    }

    //singleton
    private void ICLeaner() {
    }

    //
    public Clean createClean(CleanType type, List<Point> points) {
        switch (type) {
            case NUMBER:
                return new NumberClean(points);
            case DATE:
                return new DateClean(points);
            case SIZE:
                return new SizeClean(points);
            default:
                throw new RuntimeException();
        }
    }

    //clean
    private List<Point> cleanStyle(List<Point> f, List<Point> s) {
        switch (this.style) {
            case MAX:
                if (f.size() > s.size()) return f;
                break;
            case MIN:
                if (f.size() < s.size()) return f;
                break;
            default:
                throw new RuntimeException();
        }
        return s;
    }

    private void deletePoints() {
        for (int i = 0; i < pointsToDel.size(); i++) {
            pointsToDel.remove(0);
        }
    }

    private boolean deltaRm(List<Point> _points, int index) {
        Point p = _points.get(index);

        if (p.getType() == PointType.DELTA) {
            pointsToDel.add(p);
            _points.remove(index);

            return true;
        }

        return false;
    }//if deleted delta - return true

    private void deltaClear(List<Point> _points) {
        while (deltaRm(_points, 0)) ;
    }

    private void paramClean(Clean clean) {
        List<Point> p = clean.points;

        for (int i = 0; i < p.size() && clean.func(); ) {
            if (!Manager.haveFullWithoutFirst(p)) {
                break;
            }//break
            pointsToDel.add(p.get(i));
            p.remove(i);
        }

        for (int i = p.size() - 1; i >= 0 && clean.func(); i--) {
            if (!Manager.haveFullWithoutLast(p)) {
                break;
            }//break
            pointsToDel.add(p.get(i));
            p.remove(i);
        }

        this.deltaClear(p);
    }

    public void clean(Clean clean) {
        paramClean(clean);
        this.deletePoints();
    }

    public void clean(List<Clean> cleans) {
        List<Point> temp = new ArrayList<>();
        for (Clean c : cleans) {
            this.paramClean(c);
            temp = this.cleanStyle(temp, this.pointsToDel);
        }
        this.pointsToDel = temp;
        this.deletePoints();
    }
}
