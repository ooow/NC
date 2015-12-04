import java.io.IOException;

/**
 * Created by goga on 04.12.15.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        ReadAndWrite justdo = new ReadAndWrite();
        if (justdo.read())
            justdo.write();
    }
}

