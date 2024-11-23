package chainOfResponsibility.support;

public class TechSupportHandler implements SupportHandler {

    private final SupportHandler next = new ManagerSupportHandler();

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.type().equals("Tech")) {
            System.out.println("TechSupportHandler: Handling request..");
        } else {
            System.out.println("TechSupportHandler: Passing request to next handler..");
            next.handleRequest(request);
        }
    }

    @Override
    public void setNext(SupportHandler next) {
        this.next = next;
    }
}
