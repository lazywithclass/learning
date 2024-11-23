package chainOfResponsibility.support;

public class BasicSupportHandler implements SupportHandler {

    private SupportHandler next = new TechSupportHandler();

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.type().equals("Basic")) {
            System.out.println("BasicSupportHandler: Handling request...");
        } else {
            System.out.println("BasicSupportHandler: Passing request to next handler..");
            next.handleRequest(request);
        }
    }

    @Override
    public void setNext(SupportHandler next) {
        this.next = next;
    }
}
