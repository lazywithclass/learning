package chainOfResponsibility.support;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        client.performClientStuff(new SupportRequest("Manager", "Manager request"));
    }
}