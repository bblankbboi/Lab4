package Lab4;

import Lab4.Clean.CleanType;
import Lab4.Clean.NumberClean;
import Lab4.Clean.SizeClean;
import Lab4.Exceptions.MyException;
import Lab4.Points.PointType;

import java.util.List;

public class Main {
    public static <T> boolean inList(T _o, List<T> _list) {
        for (T o : _list) {
            if (o.equals(_o)) {
                return true;
            }
        }
        return false;
    }//check object in List<>

    public static void main(String[] args) {
        Manager manager = Manager.getInstance();

        //test 1
        try {
            System.out.println("Test 1:\n");
            manager.createFile("First", 100);
            manager.createFile("Second", 200);
            manager.createPoint(PointType.FULL);
            System.out.println(manager.getPointInfo(0));
            manager.createPoint(PointType.DELTA);
            NumberClean.MAX_SIZE = 1;
            manager.clean(CleanType.NUMBER);
            System.out.println(manager.getPointsInfo());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Если в результате 1 точка, то тест пройден.");

        //deleting after test
        manager.deleteFile(0);
        manager.deleteFile(0);
        manager.deletePoint(manager.getPoints().get(0));
        try {
            System.out.println(manager.getPointsInfo());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        //test 2
        try {
            System.out.println("Test 2:\n");
            manager.createFile("First", 100);
            manager.createFile("Second", 100);
            manager.createPoint(PointType.FULL);
            manager.createPoint(PointType.DELTA);
            manager.getPointInfo(1);
            SizeClean.MAX_SIZE = 150;
            manager.clean(CleanType.SIZE);
            System.out.println(manager.getPointsInfo());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Если осталась 1 точка то тест пройден.");

        //
        manager.deleteFile(0);
        manager.deleteFile(0);
        manager.deletePoint(manager.getPoints().get(0));
    }
}
