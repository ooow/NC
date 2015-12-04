/**
 * Created by goga on 04.12.15.
 */

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Метод проверяющий принадлежит ли точка шахматной доске
    public static boolean inChess(Point a) {
        return a != null && a.x < 8 && a.x >= 0 && a.y < 8 && a.y >= 0;
    }
}

