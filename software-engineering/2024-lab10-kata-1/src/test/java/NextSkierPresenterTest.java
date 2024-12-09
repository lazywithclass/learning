import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.presenters.NextSkierPresenter;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NextSkierPresenterTest {

    @Test
    void shouldSetNextSkierName() {
        NextSkierView viewMock = mock();
        Model modelMock = mock();
        NextSkierPresenter SUT = new NextSkierPresenter(viewMock, modelMock);

        SUT.update(null, null, List.of(new Skier("John Doe", 0)));

        verify(viewMock).set(0, "John Doe");
    }

    @Test
    void shouldReactUponUserAction() {
        NextSkierView viewMock = mock();
        Model modelMock = mock();
        NextSkierPresenter SUT = new NextSkierPresenter(viewMock, modelMock);

        SUT.action("John Doe", "500");

        verify(modelMock).addSecondMancheSkier(new Skier("John Doe", 500));
    }
}
