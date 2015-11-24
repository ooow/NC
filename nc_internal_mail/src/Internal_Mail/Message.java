package Internal_Mail;

/**
 * Created by goga on 12.11.15.
 */

// Класс Сообщение
public class Message {
    private String sense;// сообщение может быть выдимиым только для тех кому оно предназначено
    public int type;    // a тип этого сообщения виден всем
    public Message(String sense, int type){  // sense - сообщение типа String
        this.sense = sense;                 // type - ТИП СООБЩЕНИЯ (обычное 0, секретное 1)
        this.type = type;
    }
    public String getSense(){
        return sense;
    }
}

