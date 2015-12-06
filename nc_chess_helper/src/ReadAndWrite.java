/**
 * Created by goga on 04.12.15.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ReadAndWrite {
    private BufferedReader in;
    private PrintWriter out;
    private StringTokenizer st;
    private Point p;        // расположение фигуры
    private Figure f;       // тип фигуры

    public boolean read() throws IOException {
        in = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));
        this.f = Figure.valueOf(next());
        int i = 0, j = 0;
        int a;
        while (true) {
            try {                   // небольшая проверка коректности входных данных
                a = nextInt();
            } catch (NullPointerException e) {
                out.print("ERROR!!! Укажите расположение фигуры");
                out.close();
                a = -1;
            }
            if (a != 0) {
                break;
            }
            if (i < 7)
                i++;
            else {
                j++;
                i = 0;
            }
        }
        if (a != -1) {
            this.p = new Point(i, j);
            return true;
        }
        return false;
    }

    public void write() throws FileNotFoundException {
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));
        Point[] mp = f.makeAmove(p);
        int[][] ans = new int[8][8];
        for (int i = 0; i < mp.length; i++) {
            if (mp[i] != null)
                if (mp[i].inChess())
                    ans[mp[i].y][mp[i].x] = 1;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == p.x && i == p.y)
                    out.print("F ");
                else out.print(ans[i][j] + " ");
            }
            out.println();
        }
        out.close();
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine());
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}