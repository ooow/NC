import java.util.ArrayList;
import java.util.Date;

/**
 * Created by goga on 12.11.15.
 */

public class Airline implements Airport{
    public String CompanyName;
    private ArrayList<Customer> customers = new ArrayList<>();          //список клиентов
    private static ArrayList<Flight> flights = new ArrayList<>();       //список рейсов
    private ArrayList<Airplane> airplanes = new ArrayList<>();          //список самолетов
    private ArrayList<Ticket> tickets = new ArrayList<>();              //список билетов
    private ArrayList<String> cities = new ArrayList<>();               //города исполняемых рейсов
    private Date d = new Date(2015,07,15);                              //день полетов

    public static void main(String[] args) {
        Airline j7 = new Airline ();
        j7.makeLists();
        for (int i = 0; i < flights.size(); i++) {
            j7.executeFlight(flights.get(i));
        }

// АНОНИМНЫЙ КЛАСС ////////////////////////////////////////////////////////////////////////////////////////////////////
        Airport Kennedy = new Airport() {
            @Override
            public void BuyTiket(Ticket t) {
                System.out.println("Билетов нет в наличии");
            }

            @Override
            public void executeFlight(Flight f) {
                System.out.println("Рейс отменяется по неисправности самолета");
            }
        };
    }

    private void makeLists(){                           //заполняем списки

        // Создаем клиентов
        for (Integer i = 0; i < 1201; i++) {
            Customer iva = new Customer("Ivan" + i.toString(), 10000 + i, (int) (Math.random() * 500 + 700));
            Ticket t = new Ticket(i, d, 399);           //создаем билет
            iva.BuyTiket(t);                                 //продаем
            t.sellTicket(iva);                               //клиенту
            tickets.add(t);                             //добавляем в список билет
            customers.add(iva);                         //и его владельца
        }

        // Создаем самолеты
        for (int i = 0; i < 10; i++) {
            airplanes.add(new Airplane(i, 120, 0, d));
        }

        //Заполняем города (20 городов)
            cities.add("Shanghai");
            cities.add("Karachi");
            cities.add("Lagos");
            cities.add("Delhi");
            cities.add("Istanbul");
            cities.add("Tokyo");
            cities.add("Mumbai");
            cities.add("Moscow");
            cities.add("São Paulo");
            cities.add("Beijing");
            cities.add("Seoul");
            cities.add("Lahore");
            cities.add("Jakarta");
            cities.add("Guangzhou");
            cities.add("Mexico City");
            cities.add("New York City");
            cities.add("Bengaluru");
            cities.add("London");
            cities.add("Bangkok");
            cities.add("Hong Kong");

        // Создаем рейсы
        int part = 0;
        int g = 0;
        for (int i = 0; i < 10; i++) {
            ArrayList<Customer> temp = new ArrayList<>();
            if (i > 0)
                part += 100;
            int passagers = (int)(Math.random()*70 + 50);
            for (int j = 0; j < passagers; j++) {
                temp.add(customers.get(part + j));                  // расскидываем пассажиров по рейсам
            }
            flights.add(new Flight(i + 1, d, cities.get(g), cities.get(g + 1), airplanes.get(i), temp));
            g += 2;
        }
    }

    @Override
    public void BuyTiket(Ticket t) {

    }

    @Override
    public void executeFlight(Flight f) {

// ЛОКАЛЬНЫЙ КЛАСС ////////////////////////////////////////////////////////////////////////////////////////////////////
        class finished{
            public String finalfly(){
                return "--------";
            }
        }
        System.out.println("Отправляется Рейс №" + f.getID() + " из " + f.getFrom() + " в " + f.getWhere());
        System.out.println("        " + f.getAirplane().greet_passengers() + " " + f.getFrom() + " " + f.getWhere());
        System.out.println("        " + f.status());
        System.out.println("        ----------------------------------------------");
        System.out.println("        ---------------------------------------");
        System.out.println("        --------------------------------");
        System.out.println("        ------------------------");
        System.out.println("        ---------------");
        System.out.println("        ------");
        System.out.println("        -");
        System.out.println(new finished().finalfly() + f.finished() + "\n");
    }
// ВЛОЖЕННЫЙ КЛАСС // КОТОРЫЙ ЯВЛЯЕТСЯ СТАТИЧЕСИКМ ////////////////////////////////////////////////////////////////////
    public static class Ticket{
        private Integer ID;
        private Date Data;
        private Integer Price;
        private Integer CustomerPasportID;

        public Ticket(Integer id, Date date, Integer price)
        {
            this.ID = id;
            this.Data = date;
            this.Price = price;
        }

        public Integer getPrice()
        { return  Price;}

        public void sellTicket(Customer c)
        {
            this.CustomerPasportID = c.getPasID();
        }
        static String tikhave(){                                        //Статический метод
            return "You Have Ticket!!!";
        }
    }
}

