/**
 * Created by goga on 12.11.15.
 */

// Класс Сообщение
public class Message {
    private String sense;
    private int type;
    public Message(String sense, int type){  // sense - сообщение типа String
        this.sense = sense;                 // type - ТИП СООБЩЕНИЯ (обычное 0, секретное 1)
        this.type = type;
    }
    public int getType() {
        return this.type;
    }
    public String getSense(){
        return sense;
    }
}

