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
}
