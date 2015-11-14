import java.util.Date;

/**
 * Created by goga on 12.11.15.
 */

public class Airplane{
    private Integer ID;
    private Integer free_places;
    private Integer busy_places;
    private Date Data;
    private Integer height = 10000;

    public Airplane(int id, Integer free, Integer busy, Date date)
    {
        this.ID = id;
        this.free_places = free;
        this.busy_places = busy;
        this.Data = date;
        baggage b = new baggage(30, 2, 1);              // допустимый багаж пассажира
    }

    public Integer getBusy() {return busy_places;}

    public Integer getHeight() {return height;}

    public String greet_passengers(){ return "Приветствуем Вас на рейсе";}

    public Boolean take_places(int n){  // метод для бронирования свободных мест в самолете
        for (int i = 0; i < n; i++) {
            if (free_places > 0) {
                busy_places++;
                free_places--;
            } else
                return false;
        }
        return true;
    }

// ВНУТРЕННИЙ КЛАСС//////////////////////////////////////////////////////////////////////
    class baggage{
        public int weight;              //вес багажа
        public int suitcases;           //кол - во чамоданов
        public int animals;             //количество перевозимых животных

        public baggage(int weights, int suitcasess, int animalss)
        {
            this.weight = weights;
            this.suitcases = suitcasess;
            this.animals = animalss;
        }
    }
}

