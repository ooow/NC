/**
 * Created by goga on 02.11.15.
 */

public class Heroes {

    public static void main(String[] args) {

        CallHero T = new Flash();
        System.out.println(T.Superpower());

        CallHero T1 = new GreenLantern();
        System.out.println(T1.Superpower());

        Batman B = new Batman();
        B.name = "Bruce";
        B.SuperName = "Batman";
        System.out.println(B.CallBatmobile());

    }

    protected String power() {
        return "I'm a very powerful ! ";
    }

    private String onlyforSuperMan() {
        return "I'm a SuperMan";
    }

}
