package Client;

import Commands.Send;

/**
 * Created by goga on 16.12.15.
 */
public class Client extends Send {
    private int myPort;                   // порт для входящих сообщений (персонально данному клитенту)
    private String name;                  // имя клиента
    private int serverPort = 7777;        // порт для исходящих сообщений (порт сервера, по умолчанию 7777)

    public Client(String name, String host, int myPort) {
        super(host);
        this.name = name;
        this.myPort = myPort;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getPort() {
        return this.myPort;
    }

    public void setServerPort(int port) {
        this.serverPort = port;
    }

    public int getServerPort() {
        return this.serverPort;
    }
}
