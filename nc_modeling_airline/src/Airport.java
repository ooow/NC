
/**
 * Created by goga on 12.11.15.
 */

public interface Airport{
    public void BuyTiket(Airline.Ticket t);                      // В аэропорту приобретают билет t
    public void executeFlight(Flight f);                         // В аэропорту отправляют(исполняют) рейс f
}

