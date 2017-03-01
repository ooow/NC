import java.util.ArrayList;
import java.util.Date;

/**
 * Created by goga on 12.11.15.
 */

public class Flight{
    private ArrayList<Customer> customers;
    private int unsold_tickets;
    private Integer ID;
    private String From;
    private String Where;
    private Date Data;
    private Airplane plane;

    public Flight (Integer id, Date date, String from, String where, Airplane a, ArrayList<Customer> customerss)
    {
        this.ID = id;
        this.Data = date;
        this.From = from;
        this.Where = where;
        this.customers = new ArrayList<>();
        this.plane = a;
        this.customers = customerss;
        this.unsold_tickets = customerss.size();
        this.plane.take_places(unsold_tickets);
    }

    public Integer getID() {return ID;}

    public String getFrom() {return From;}

    public String getWhere() {return Where;}

    public Airplane getAirplane() {return plane;}

    public String status() {
        String s = "Полет происходит на высоте " + plane.getHeight() + " метров\n";
        s += "        Количество пассажиров " + plane.getBusy() + "\n        Приятного полета!";
        return s;
    }

    public String finished() {return "Полет завершен, WELCOME To " + Where;}
}

