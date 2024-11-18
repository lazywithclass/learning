package chainOfResponsibility;

public class BasicSupportHandler implements SupportHandler {

    private final SupportHandler next = new TechSupportHandler();

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.type().equals("Basic")) {
            System.out.println("BasicSupportHandler: Handling request...");
        } else {
            System.out.println("BasicSupportHandler: Passing request to next handler..");
            next.handleRequest(request);
        }
    }
}
