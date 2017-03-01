/**
 * Created by goga on 02.11.15.
 */

public class Batman extends Heroes{
    String name;
    String SuperName;
    String feature = power();
    protected String CallBatmobile() {
        return feature + "I have a SuperCar";
    }

    public String toString() {
        return "Batman{" +
                "name='" + name + '\'' +
                ", SuperName='" + SuperName + '\'' +
                ", feature='" + feature + '\'' +
                '}';
    }
}
