import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.presenter.InputPresenter;
import it.unimi.di.sweng.esame.view.InputConcreteView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class InputPresenterTest {

    private Model modelMock;
    private InputPresenter SUT;
    private InputConcreteView viewMock;

    @BeforeEach
    public void setup() {
        modelMock = mock();
        viewMock = mock();
        SUT = new InputPresenter(modelMock, viewMock);
    }

    @Test
    void shouldAddABookingWhenActedUponView() {
        SUT.action("Luigi", "Brera");
        verify(modelMock).addBooking("Luigi", "Brera");
    }

    @Test
    void shouldShowErrorWhenModelThrows() {
        doThrow(new IllegalArgumentException("Invalid excursion name provided."))
                .when(modelMock).addBooking("Luigi", "Crema");

        SUT.action("Luigi", "Crema");

        verify(viewMock).showError("Invalid excursion name provided.");
    }

    @Test
    void shouldClearErrorOnValidInputAfterInvalidInput() {
        SUT.action("Luigi", "Brera");
        verify(viewMock).showSuccess();
    }

}
