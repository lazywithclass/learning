package chainOfResponsibility;

public class ManagerSupportHandler implements SupportHandler {

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.type().equals("Manager")) {
            System.out.println("ManagerSupportHandler: Handling request..");
        } else {
            throw new IllegalArgumentException("Invalid request type");
        }
    }
}
