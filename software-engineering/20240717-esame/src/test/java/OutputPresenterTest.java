import it.unimi.di.sweng.esame.model.Excursion;
import it.unimi.di.sweng.esame.presenter.OutputPresenter;
import it.unimi.di.sweng.esame.view.DisplayView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OutputPresenterTest {

    @Test
    void shouldUpdateViewWhenUpdateIsCalled() {
        DisplayView viewMock = mock();

        List<String> excursionList = List.of(
                "Excursion2 40.00 20",
                "Excursion1 30.00 10"
        );

        OutputPresenter SUT = new OutputPresenter(viewMock, () -> excursionList);

        SUT.update(null, List.of());

        verify(viewMock).set(0, "Excursion2 40.00 20");
        verify(viewMock).set(1, "Excursion1 30.00 10");
    }
}
