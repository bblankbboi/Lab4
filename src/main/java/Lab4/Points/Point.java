package Lab4.Points;

import Lab4.Exceptions.MyException;
import Lab4.File;
import Lab4.Manager;
import Lab4.Main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Point {
    protected Date creationDate;
    protected long size;

    protected static List<File> filterFiles(List<File> _files, List<String> _listID) {//delete chosen files
        List<File> files = new ArrayList<>();

        for (File f : _files) {
            if (!Main.inList(f.getID(), _listID)) {
                files.add(f);
            }
        }

        return files;
    }

    protected static <T> List<T> getDifference(List<T> first, List<T> second) {
        List<T> objects = new ArrayList<>();
        for (T t : first) {
            if (!Main.inList(t, second)) {
                objects.add(t);
            }
        }
        return objects;
    }//filter objects from first

    protected static List<String> toID(List<File> files) {
        List<String> filesID = new ArrayList<>();
        for (File f : files) {
            filesID.add(f.getID());
        }
        return filesID;
    }

    public static FullPoint createFull(List<File> files, Date _creationDate) {
        return new FullPoint(files, _creationDate);
    }

    public static DeltaPoint createDelta(List<Point> lastPointsAfterFull, List<File> files, Date _creationDate) {
        return new DeltaPoint(lastPointsAfterFull, files, _creationDate);
    }

    public long getSize() {
        return size;
    }

    //    abstract protected void setType();
    abstract protected void setSize(long x);

    abstract public String info() throws MyException;

    abstract public PointType getType();

    public Date getDate() {
        return creationDate;
    }

}
