package internalMail;

import people.Worker;

import java.util.ArrayList;

/**
 * Created by goga on 17.11.15.
 */
/////////////////////////// Внутренняя почта
public interface InternalMail {
    public void WORK();                                     // организация работы почтового отделения

    public void setNET(PostOffice... offices);             // перечень связных почтовых отделений данного почтового пункта

    public void receive_mail(ArrayList<Message> messages);  // принятие новых писам

    public void recruit(Worker man);                        // нанять нового работника в почтовом отделении

    public void dismiss(Worker man);                        // уволить работника в почтовом отделении
}
