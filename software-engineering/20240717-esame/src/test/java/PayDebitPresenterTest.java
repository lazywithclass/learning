import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.presenter.PayDebitPresenter;
import it.unimi.di.sweng.esame.view.InputConcreteView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PayDebitPresenterTest {

    private Model modelMock;
    private PayDebitPresenter SUT;
    private InputConcreteView viewMock;

    @BeforeEach
    public void setup() {
        modelMock = mock();
        viewMock = mock();
        SUT = new PayDebitPresenter(modelMock, viewMock);
    }

    @Test
    void whenACustomerPaysUpdateModel() {
        SUT.action("John", "20");

        verify(modelMock).payDebit("John", 20.0f);
    }

    @Test
    void shouldShowErrorWhenNotANumberIsPassed() {
        SUT.action("Luigi", "Brera");
        verify(viewMock).showError("Invalid amount to pay.");
    }

    @Test
    void shouldShowErrorWhenExceedingDebt() {
        doThrow(IllegalArgumentException.class).when(modelMock).payDebit("Luigi", 24);
        SUT.action("Luigi", "24");
        verify(viewMock).showError("You can't pay more than you owed!");
    }

}
