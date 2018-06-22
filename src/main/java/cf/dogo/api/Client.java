package cf.dogo.api;

public class Client extends cf.dogo.server.bridge.Client {
    public Client(String ip, int localhost){
        super(ip, localhost,"Client");
        connect();
    }

    public boolean isAvailable(){
        return super.isAvailabe();
    }
}
