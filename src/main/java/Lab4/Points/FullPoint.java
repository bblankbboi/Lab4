package Lab4.Points;

import Lab4.Exceptions.MyException;
import Lab4.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Lab4.Points.PointType.DELTA;
import static Lab4.Points.PointType.FULL;
import static java.lang.String.format;

public class FullPoint extends Point {
    private final List<File> files;
    private final PointType type = FULL;

    public FullPoint(List<File> _files, Date _creationDate) {
        files = new ArrayList<>();
        files.addAll(_files);
        creationDate = _creationDate;
        this.setSize(this.files);
    }

    public FullPoint(FullPoint _fullPoint, Date _creationDate) {
        files = _fullPoint.getFiles();
        creationDate = _creationDate;
    }

    //    protected void setType(){ this.type = Type.FULL; }
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

    public String info() throws MyException {
        String line = "\n...";
        for (int i = 0; i < files.size(); i++) {
            line += format("\n    %d.%s"
                    , i + 1, files.get(i).getName());
        }
        if (line.equals("\n...")) {
            throw new MyException("Point is empty.");
        }
        line += format("\nSize of point: %d.", size);
        line += "\n...\n";

        return line;
    }

    public List<File> getFiles() {
        List<File> _files = new ArrayList<>();
        _files.addAll(files);
        return _files;
    }
}
