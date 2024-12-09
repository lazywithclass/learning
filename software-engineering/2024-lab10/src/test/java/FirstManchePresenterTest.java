import it.unimi.di.sweng.slalom.Skier;
import it.unimi.di.sweng.slalom.model.ModelFirstManche;
import it.unimi.di.sweng.slalom.presenters.FirstManchePresenter;
import it.unimi.di.sweng.slalom.views.OutputView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class FirstManchePresenterTest {

    @Test
    void shouldUpdateViewWhenAChangeInTheModelOccurs() {
        OutputView viewMock = mock();
        ModelFirstManche modelMock = mock();
        FirstManchePresenter SUT = new FirstManchePresenter(viewMock, modelMock);

        List<Skier> firstMancheSkiers = List.of(new Skier("name1", 1.00), new Skier("name2", 2.00));
        SUT.update(null, firstMancheSkiers);

        verify(viewMock).set(0, "name1 1.00");
        verify(viewMock).set(1, "name2 2.00");
    }

    @Test
    void shouldUpdateViewInOrder() {
        OutputView viewMock = mock();
        ModelFirstManche modelMock = mock();
        FirstManchePresenter SUT = new FirstManchePresenter(viewMock, modelMock);

        List<Skier> firstMancheSkiers = List.of(
                new Skier("name1", 3.00),
                new Skier("name2", 1.00),
                new Skier("name3", 2.00),
                new Skier("name4", 2.00)

        );
        SUT.update(null, firstMancheSkiers);

        verify(viewMock).set(0, "name2 1.00");
        verify(viewMock).set(1, "name4 2.00");
        verify(viewMock).set(2, "name3 2.00");
        verify(viewMock).set(3, "name1 3.00");
    }

}
