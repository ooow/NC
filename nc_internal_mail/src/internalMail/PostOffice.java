package internalMail;

import people.Worker;

import java.util.ArrayList;

/**
 * Created by goga on 17.11.15.
 */

public class PostOffice implements InternalMail {
    private String postName;
    private ArrayList<Message> base = new ArrayList<>();
    private ArrayList<Message> non_sort_base;
    private ArrayList<Worker> workers = new ArrayList<>();
    private ArrayList<PostOffice> offices = new ArrayList<>();
    private boolean functionality = false;

    public PostOffice(String name) {
        this.postName = name;
    }

    public void set_workers(ArrayList<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public void WORK() {
        for (int i = 0; i < non_sort_base.size(); i++) {
            if (non_sort_base.get(i).type == 0)
                this.base.add(non_sort_base.get(i));
        }
        if (offices.size() > 0)
            for (int j = 0; j < workers.size(); j++) {
                if ("Postman".equals(workers.get(j).getPost()))                             // У шкиона в поле должнолжность тоже указано: Postman
                    workers.get(j).cast(workers.get(j)).setConfigs(non_sort_base, offices); // так получается, что никто в отделении не знает кто шпион
                workers.get(j).to_do_work();
            }
    }

    @Override
    public void setNET(PostOffice... offices) {
        for (int i = 0; i < offices.length; i++) {
            this.offices.add(offices[i]);
        }
    }

    @Override
    public void receive_mail(ArrayList<Message> messages) {
        if (workers.size() < 5 && !functionality) {
            System.out.println("Почтовое отделение: " + this.postName + " не в состоянии работать из за нехватки кадров");
            System.out.println("Пожалуйста, найдите новых сотрудников");
            functionality = true;
        } else {
            functionality = true;
            non_sort_base = new ArrayList<>();
            non_sort_base = messages;
            WORK();
        }
    }

    @Override
    public void recruit(Worker man) {
        workers.add(man);
    }

    @Override
    public void dismiss(Worker man) {
        workers.remove(man);
    }

    public void show_base() {
        System.out.println("Почтовое отделение " + this.postName + " получила письма: ");
        for (int i = 0; i < base.size(); i++) {
            System.out.println(i + ". " + base.get(i).getSense());
        }
    }
}

