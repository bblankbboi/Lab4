package Lab4;

import Lab4.Clean.Clean;
import Lab4.Clean.CleanType;
import Lab4.Clean.ICleaner;
import Lab4.Exceptions.MyException;
import Lab4.Points.DeltaPoint;
import Lab4.Points.FullPoint;
import Lab4.Points.Point;
import Lab4.Points.PointType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Lab4.Points.PointType.FULL;
import static java.lang.String.format;

public class Manager {
    private final static List<Point> points = new ArrayList<>();
    private static Manager manager;
    private int SystemSize = 0;
    private List<File> files = new ArrayList<>();

    //
    private Manager() {
        SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
        try {
            TimeMachine.date = DateFor.parse("08.12.2020");
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        System.out.println("Backup was created");
    }

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }//singleton

    public static boolean haveFullWithoutFirst(List<Point> _points) {
        for (int i = 1; i < _points.size(); i++) {
            if (_points.get(i).getType() == FULL) {
                return true;
            }
        }
        return false;
    }

    public static boolean haveFullWithoutLast(List<Point> _points) {
        for (int i = _points.size() - 2; i >= 0; i--) {
            if (_points.get(i).getType() == FULL) {
                return true;
            }
        }
        return false;
    }

    //get and merge points from full to delta or to end of list
    private static int getLastFullPointIndex() {
        for (int index = points.size() - 1; index >= 0; index--) {
            if (points.get(index).getType() == FULL){
                return index;
            }
        }
        throw new RuntimeException();
    }

    public static List<Point> lastPointsFromFull() throws MyException {
        int indexOfFull = Manager.getLastFullPointIndex();
        List<Point> lastPoints = new ArrayList<>();
        for (int i = indexOfFull; i < points.size(); i++) {
            lastPoints.add(points.get(i));
        }
        if (lastPoints.size() == 0) {
            throw new MyException("Full points doesn't exist");
        }
        return lastPoints;
    }

    private static List<Point> lastPointsFromFull(int index) throws MyException {
        int indexOfFull = Manager.getLastFullPointIndex();
        List<Point> lastPoints = new ArrayList<>();
        for (int i = indexOfFull; i <= index; i++) {
            lastPoints.add(points.get(i));
        }//<=
        if (lastPoints.size() == 0) {
            throw new MyException("Full points doesn't exist");
        }
        return lastPoints;
    }

    //stuff
    public void changeTime(Date _date) {
        TimeMachine.date = _date;
    }

    public List<File> getFiles() {
        List<File> temp = new ArrayList<>(files);
        return temp;
    }

    //getter and setter for ArrayList
    public void setFiles(List<File> _files) {
        this.files = new ArrayList<>();
        this.files.addAll(_files);
    }

    public List<Point> getPoints() {
        List<Point> temp = new ArrayList<>(this.points);
        return temp;
    }

    //fields getters
    public Date getDate() {
        return TimeMachine.date;
    }

    public String getFileNamesInTheSystem() throws MyException {
        String line = "    Files in the system:\n";
        int count = 0;
        for (File f : files) {
            count++;
            line += format("        %d.%s\n", count, f.getName());
        }
        if (files.size() == 0) {
            throw new MyException("System doesn't have a files.");
        }
        return line;
    }

    public String getPointsInfo() throws MyException {
        String line = "    Points in the system:\n";
        int count = 0;
        for (Point p : points) {
            count++;
            line += format("        [%d] (%s) %s\n", count, p.getType(), p.getDate().toString());
        }
        if (points.size() == 0) {
            throw new MyException("System doesn't have a points.");
        }
        return line;
    }

    public String getPointInfo(int x) throws MyException {
        Point p = points.get(x);
        return p.info();
    }

    //create and delete files/points
    public void createFile(String fileName, int fileSize) {
        File file = new File(fileName, fileSize, TimeMachine.date);
        this.files.add(file);
        this.SystemSize += fileSize;
    }

    public void deleteFile(int index) {
        this.SystemSize -= files.get(index).getSize();
        files.remove(index);
    }

    public void createPoint(PointType type) {
        Point p = null;
        switch (type) {
            case FULL:
                p = Point.createFull(files, TimeMachine.date);
                break;
            case DELTA:
                try {
                    p = Point.createDelta(Manager.lastPointsFromFull(), files, TimeMachine.date);
                } catch (MyException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                throw new RuntimeException();
        }
        if (p == null) {
            throw new RuntimeException();
        }
        points.add(p);
        SystemSize += p.getSize();
    }

    private void deletePoint(int index) {
        this.SystemSize -= points.get(index).getSize();
        points.remove(index);
    }

    public void deletePoint(Point p) {
        this.SystemSize -= p.getSize();
        points.remove(p);
    }

    //backup
    public void doBackup(int pointIndex) {
        Point p = points.get(pointIndex);
        if (p.getType().equals("FULL")) {
            FullPoint fp = (FullPoint) p;
            this.setFiles(fp.getFiles());
        } else {
            DeltaPoint dp = (DeltaPoint) p;
            FullPoint mergedFullPoint = null;

            try {
                mergedFullPoint = dp.mergeAfterFull(Manager.lastPointsFromFull(pointIndex));
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }

            if (mergedFullPoint == null) {
                throw new RuntimeException();
            }
            this.setFiles(mergedFullPoint.getFiles());
        }
    }

    //clear points with some parameters
    public void clean(CleanType type) {
        ICleaner icleaner = ICleaner.getInstance();
        Clean clean = icleaner.createClean(type, this.points);
        icleaner.clean(clean);
    }

    public void clean(List<CleanType> types) {
        ICleaner icleaner = ICleaner.getInstance();
        List<Clean> cleans = new ArrayList<>();
        Clean clean;
        for (CleanType type : types) {
            clean = icleaner.createClean(type, this.points);
            cleans.add(clean);
        }
        icleaner.clean(cleans);
    }
}
