import it.unimi.di.sweng.slalom.Skier;
import it.unimi.di.sweng.slalom.model.ModelFirstManche;
import it.unimi.di.sweng.slalom.model.ModelSecondManche;
import it.unimi.di.sweng.slalom.presenters.FirstManchePresenter;
import it.unimi.di.sweng.slalom.presenters.SecondManchePresenter;
import it.unimi.di.sweng.slalom.views.InputView;
import it.unimi.di.sweng.slalom.views.OutputView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SecondManchePresenterTest {

    @Test
    void shouldListenForNewSkierAndUpdateModel() {
        InputView viewMock = mock();
        OutputView outputViewMock = mock();
        ModelSecondManche modelMock = mock();

        SecondManchePresenter SUT = new SecondManchePresenter(viewMock, outputViewMock, modelMock);
        SUT.action("name1", "1.00");

        verify(modelMock).addSkier(new Skier("name1", 1.00));
    }

    @Test
    void shouldUpdateViewWhenModelChanges() {
        InputView inputViewMock = mock();
        OutputView outputViewMock = mock();
        ModelSecondManche modelMock = mock();
        SecondManchePresenter SUT = new SecondManchePresenter(inputViewMock, outputViewMock, modelMock);

        List<Skier> skiers = List.of(new Skier("name1", 1.00), new Skier("name2", 2.00));
        SUT.update(null, skiers);

        verify(outputViewMock).set(0, "name1 1.00");
        verify(outputViewMock).set(1, "name2 2.00");
    }

    @Test
    void name() {
    }
}
