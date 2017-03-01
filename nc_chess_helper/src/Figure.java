/**
 * Created by goga on 04.12.15.
 */

public enum Figure {
    // Король
    KING() {
        @Override
        Point[] makeAmove(Point p) {
            Point[] area = new Point[9];
            int k = 0;
            for (int i = p.x - 1; i <= p.x + 1; i++) {
                for (int j = p.y - 1; j <= p.y + 1; j++) {
                    area[k] = new Point(i, j);
                    k++;
                }
            }
            return area;
        }
    },
    // Ферзь
    QUEEN() {
        @Override
        Point[] makeAmove(Point p) {
            Point[] area = new Point[32];
            Point[] area1 = Figure.valueOf("ROOK").makeAmove(p);
            Point[] area2 = Figure.valueOf("BISHOP").makeAmove(p);

            for (int i = 0; i < 32; i += 2) {
                area[i] = area1[i / 2];
                area[i + 1] = area2[i / 2];
            }
            return area;
        }
    },
    // Слон
    BISHOP() {
        @Override
        Point[] makeAmove(Point p) {
            Point[] area = new Point[16];
            int j = 0;
            Point a = new Point(p.x, p.y);
            while (a.x < 8 && a.y < 8) {
                area[j] = new Point(a.x, a.y);
                a.x++;
                a.y++;
                j++;
            }
            a = new Point(p.x, p.y);
            while (a.x > 0 && a.y > 0) {
                area[j] = new Point(a.x - 1, a.y - 1);
                a.x--;
                a.y--;
                j++;
            }
            a = new Point(p.x, p.y);
            while (a.x > 0 && a.y < 8) {
                area[j] = new Point(a.x - 1, a.y + 1);
                a.x--;
                a.y++;
                j++;
            }
            a = new Point(p.x, p.y);
            while (a.x < 8 && a.y > 0) {
                area[j] = new Point(a.x + 1, a.y - 1);
                a.x++;
                a.y--;
                j++;
            }
            return area;
        }
    },
    // Ладья
    ROOK() {
        @Override
        Point[] makeAmove(Point p) {
            Point[] area = new Point[16];
            for (int i = 0; i < 8; i++) {
                area[i] = new Point(p.x, i);
            }
            int k = 0;
            for (int i = 8; i < 16; i++) {
                area[i] = new Point(k, p.y);
                k++;
            }
            return area;
        }
    },
    // Конь
    KNIGHT() {
        @Override
        Point[] makeAmove(Point p) {
            Point[] area = new Point[9];
            area[0] = p;
            area[1] = new Point(p.x - 2, p.y - 1);
            area[2] = new Point(p.x - 2, p.y + 1);
            area[3] = new Point(p.x - 1, p.y - 2);
            area[4] = new Point(p.x - 1, p.y + 2);
            area[5] = new Point(p.x + 1, p.y - 2);
            area[6] = new Point(p.x + 1, p.y + 2);
            area[7] = new Point(p.x + 2, p.y - 1);
            area[8] = new Point(p.x + 2, p.y + 1);
            return area;
        }
    },
    // Пешка
    PAWN() {
        @Override
        Point[] makeAmove(Point p) {
            Point[] area = new Point[2];
            area[0] = p;
            area[1] = new Point(p.x, p.y - 1);
            return area;
        }
    };

    Point[] makeAmove(Point p) {
        return new Point[0];
    }
}