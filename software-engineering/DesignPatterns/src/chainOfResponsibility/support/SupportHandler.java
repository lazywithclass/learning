package chainOfResponsibility.support;

public interface SupportHandler {
    void handleRequest(SupportRequest request);
    void setNext(SupportHandler next);
}
