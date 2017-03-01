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
    public boolean inChess() {
        return x < 8 && x >= 0 && y < 8 && y >= 0;
    }
}