package Lab4.Points;

import Lab4.Exceptions.MyException;
import Lab4.File;
import Lab4.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Lab4.Points.PointType.DELTA;
import static Lab4.Points.PointType.FULL;
import static java.lang.String.format;

public class DeltaPoint extends Point {
    private final List<File> addedFiles;
    private final List<String> removedFilesID;
    private final PointType type = PointType.DELTA;

    public DeltaPoint(List<Point> lastPointsAfterFull, List<File> _files, Date _creationDate) {
        creationDate = _creationDate;
        FullPoint fp = DeltaPoint.mergeAfterFull(lastPointsAfterFull);
        addedFiles = Point.getDifference(_files, fp.getFiles());//now - was
        removedFilesID = Point.toID(Point.getDifference(fp.getFiles(), _files));//was - now
        this.setSize(addedFiles);
    }

    public static FullPoint mergeAfterFull(List<Point> lastPointsAfterFull) {
        if (lastPointsAfterFull.get(0).getType().equals("DELTA")) {
            throw new RuntimeException();
        }
        FullPoint fullPoint = new FullPoint((FullPoint) lastPointsAfterFull.get(0), Manager.getInstance().getDate());
        for (int i = 1; i < lastPointsAfterFull.size(); i++) {
            fullPoint = DeltaPoint.merge(fullPoint, (DeltaPoint) lastPointsAfterFull.get(i), Manager.getInstance().getDate());
        }

        return fullPoint;
    }

    private static FullPoint merge(FullPoint _fullPoint, DeltaPoint _deltaPoint, Date _creationDate) {
        List<File> files = new ArrayList<>();

        files.addAll(
                Point.filterFiles(_fullPoint.getFiles(), _deltaPoint.getRemovedFilesID())
        );
        files.addAll(_deltaPoint.getAddedFiles());

        return new FullPoint(files, _creationDate);
    }

    //    protected void setType(){ this.type = Type.DELTA; }
    public PointType getType() {
        switch (type) {
            case FULL:
                return FULL;
            case DELTA:
                return DELTA;
            default:
                throw new RuntimeException();
        }
    }

    protected void setSize(long x) {
        this.size = x;
    }

    protected void setSize(List<File> _files) {
        for (File f : _files) {
            this.size += f.getSize();
        }
    }

    public List<String> getRemovedFilesID() {
        return removedFilesID;
    }

    public List<File> getAddedFiles() {
        return addedFiles;
    }

    public String info() throws MyException {
        String line = "\n...";
        FullPoint fp = DeltaPoint.mergeAfterFull(Manager.lastPointsFromFull());
        for (int i = 0; i < fp.getFiles().size(); i++) {
            line += format("\n    %d.%s", i + 1, fp.getFiles().get(i).getName());
        }
        if (line.equals("\n...")) {
            throw new MyException("Point is empty.");
        }
        line += format("\nSize of point: %d.", fp.getSize());
        line += "\n...\n";
        return line;
    }
}
