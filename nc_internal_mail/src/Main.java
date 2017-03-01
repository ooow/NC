import internalMail.Message;
import internalMail.PostOffice;
import people.*;
import secretRoom.*;

import java.util.ArrayList;

/**
 * Created by goga on 17.11.15.
 */

public class Main {
    public static void main(String[] args) {

        SecretBase NYCHKA = new SecretBase();

        PostOffice usaPost = new PostOffice("USA");
        PostOffice rusPost = new PostOffice("RUSSIA");
        PostOffice gerPost = new PostOffice("GERMANY");
        PostOffice fraPost = new PostOffice("FRANCE");
        PostOffice itaPost = new PostOffice("ITALY");
        PostOffice polPost = new PostOffice("POLAND");

        // Связь отделений: США -> Россия -> Германия
        //                         Россия -> Франция -> Италия -> Польша
        usaPost.setNET(rusPost);
        rusPost.setNET(gerPost, fraPost);
        fraPost.setNET(itaPost);
        itaPost.setNET(polPost);


        // USA POST TEAM
        ArrayList<Worker> usateam = new ArrayList<>();
        usateam.add(new Postman("Sara"));
        usateam.add(new Assistant("Oliver"));
        usateam.add(new Assistant("Henry"));
        usateam.add(new Accountant("Ella"));
        usateam.add(new Cleaner("Jack"));
        Worker Luke = new Contact("Luke", NYCHKA);
        usateam.add(Luke);
        Spy Grece = new Spy("Grece");
        Grece.setContact(Luke);
        usateam.add(Grece);


        // RUSSIAN POST TEAM
        ArrayList<Worker> rusteam = new ArrayList<>();
        rusteam.add(new Postman("Ivan"));
        rusteam.add(new Assistant("Svetlana"));
        rusteam.add(new Accountant("Olga"));
        rusteam.add(new Cleaner("Zina"));
        Worker Roman = new Contact("Roman", NYCHKA);
        rusteam.add(Roman);
        Spy Kirill = new Spy("Kirill");
        Kirill.setContact(Roman);
        rusteam.add(Kirill);


        // GERMAN POST TEAM
        ArrayList<Worker> gerteam = new ArrayList<>();
        gerteam.add(new Postman("Ben"));
        gerteam.add(new Assistant("Anna"));
        gerteam.add(new Assistant("Lea"));
        gerteam.add(new Accountant("Lukas"));
        gerteam.add(new Cleaner("Emma"));
        gerteam.add(new Cleaner("Mia"));
        Worker Adolf = new Contact("Adolf", NYCHKA);
        gerteam.add(Adolf);
        Spy Sophia = new Spy("Sophia");
        Sophia.setContact(Adolf);
        gerteam.add(Sophia);


        // French POST TEAM
        ArrayList<Worker> frateam = new ArrayList<>();
        frateam.add(new Postman("Marie"));
        frateam.add(new Assistant("Pascal"));
        frateam.add(new Accountant("Gerard"));
        frateam.add(new Cleaner("Briggite"));
        frateam.add(new Cleaner("Sylvie"));
        Worker Jean = new Contact("Jean", NYCHKA);
        frateam.add(Jean);
        Spy Nikole = new Spy("Nikole");
        Nikole.setContact(Jean);
        frateam.add(Nikole);


        // Italian POST TEAM
        ArrayList<Worker> itateam = new ArrayList<>();
        itateam.add(new Postman("Matteo"));
        itateam.add(new Assistant("Tommaso"));
        itateam.add(new Assistant("Simone"));
        itateam.add(new Accountant("Pietro"));
        itateam.add(new Cleaner("Diego"));
        itateam.add(new Cleaner("Alice"));
        Worker Victoria = new Contact("Victoria", NYCHKA);
        itateam.add(Victoria);
        Spy Leonardo = new Spy("Leonardo");
        Leonardo.setContact(Victoria);
        itateam.add(Leonardo);

        // Poland POST TEAM
        ArrayList<Worker> polteam = new ArrayList<>();
        polteam.add(new Postman("Bartos"));
        polteam.add(new Assistant("Zakharias"));
        polteam.add(new Cleaner("Danuta"));

        // Отправляем работников в почтовые отделения
        usaPost.set_workers(usateam);
        rusPost.set_workers(rusteam);
        gerPost.set_workers(gerteam);
        fraPost.set_workers(frateam);
        itaPost.set_workers(itateam);
        polPost.set_workers(polteam);

        //Добавляем сообщения для отправки в отделения ( 0 - обычное сообщение, 1 - секретное)
        ArrayList<Message> mailUSA = new ArrayList<>();
        mailUSA.add(new Message("Check connection from USA", 0));
        mailUSA.add(new Message("Check SECRET communication from USA", 1));
        usaPost.receive_mail(mailUSA);


        ArrayList<Message> mailRUS = new ArrayList<>();
        mailRUS.add(new Message("Check SECRET communication from Russia", 1));
        mailRUS.add(new Message("Check connection from Russia", 0));
        rusPost.receive_mail(mailRUS);


        ArrayList<Message> mailFRA = new ArrayList<>();
        mailFRA.add(new Message("Check connection from France", 0));
        mailFRA.add(new Message("Check SECRET communication from France", 1));
        fraPost.receive_mail(mailFRA);


        //Проверяем результат работы почтовых отделов
        usaPost.show_base();
        rusPost.show_base();
        gerPost.show_base();
        fraPost.show_base();
        itaPost.show_base();
        polPost.show_base();

        //Проверяем результат работы шпионский служб
        NYCHKA.show_secret_info();
    }

}

