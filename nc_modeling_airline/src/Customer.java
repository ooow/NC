/**
 * Created by goga on 12.11.15.
 */

public class Customer implements Airport{
    private String Name;
    private Integer PasportID;
    private Airline.Ticket Bilet;
    private Integer Money;

    public Customer (String name, Integer pswID, Integer money)
    {
        this.Name = name;
        this.PasportID = pswID;
        this.Money = money;
    }

    public Integer getPasID() {return PasportID;}

    public Boolean haveTicket(){
        if (Bilet != null)
            return true;
        else
            return false;
    }

    @Override
    public void BuyTiket(Airline.Ticket t) {
        if (Money > t.getPrice())
        {
            this.Bilet = t;
            Money -= t.getPrice();
        }
    }

    @Override
    public void executeFlight(Flight f) {}
}

