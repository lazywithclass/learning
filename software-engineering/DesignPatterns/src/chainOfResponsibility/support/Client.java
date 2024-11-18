package chainOfResponsibility;

public class Client {

    // this does not need to change, in accordance with the Open/Closed Principle
    public void performClientStuff(SupportRequest request) {
        SupportHandler basicSupportHandler = new BasicSupportHandler();
        basicSupportHandler.handleRequest(request);
    }
}
