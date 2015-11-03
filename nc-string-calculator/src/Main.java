import java.io.*;
import java.util.Scanner;

/**
 * Created by goga on 02.11.15.
 */

public class Main {
    Scanner t = new Scanner(System.in);
    String S;

    void read() throws IOException {
        System.out.println("Введите выражение: ");
        S = t.next();
    }

/*    String Search(String s){
        int start = s.indexOf("(");
        int stop = s.indexOf("(", start + 1);
        int end = s.indexOf(")");
        if ( stop == -1)
            stop = end + end;
        if (start != -1 && end != -1 && stop > end)
            return s.substring(start + 1, end);
        else
            return s;
    }*/

    StringBuffer calculateU(StringBuffer s){ // Упрощение выражения до элементарных операций суммирования +
        int u = s.indexOf("*");
        if ( u != -1)
        {
            int end = s.indexOf("+", u);
            if (end == -1)
                end = s.indexOf("*", u + 1);
            if (end == -1)
                end = s.length();

            int start = s.lastIndexOf("+", u) + 1;
            if (start == -1)
                start = 0;

            int a = Integer.valueOf(s.substring(start, u));
            int b = Integer.valueOf(s.substring(u + 1, end));
            int res = a * b;
            String str = Integer.toString(res);
            s.replace(start, end, str);
            return calculateU(s);
        }
        return s;
    }

    StringBuffer calculateSimple(StringBuffer s){  // Вычисление вырожения содержащее только +
        int u = s.indexOf("+");
        if ( u != -1)
        {
            int end = s.indexOf("+", u + 1);
            if (end == -1)
                end = s.length();

            int start = 0;

            int a = Integer.valueOf(s.substring(start, u));
            int b = Integer.valueOf(s.substring(u + 1, end));
            int res = a + b;
            String str = Integer.toString(res);
            s.replace(start, end, str);
            return calculateSimple(s);
        }
        return s;
    }

    void recap() throws Exception
    {
        StringBuffer st = new StringBuffer(S);
        calculateU(st);                             // Запуск упрощения выражения
        System.out.println(calculateSimple(st));   // Вычисление выражения и вывод ответа
    }

    public static void main(String[] args) throws Exception {
        Main sol = new Main ();
        sol.read();
        sol.recap();
    }
}