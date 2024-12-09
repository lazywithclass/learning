import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.presenters.NextSkierPresenter;
import it.unimi.di.sweng.slalom.presenters.SecondManchePresenter;
import it.unimi.di.sweng.slalom.views.DisplayView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SecondManchePresenterTest {

    @Test
    void shouldAddSecondSkiersInView() {
        DisplayView viewMock = mock();
        SecondManchePresenter SUT = new SecondManchePresenter(viewMock);

        SUT.update(null, null, List.of(new Skier("John Doe", 100)));
        SUT.update(null, null, List.of(new Skier("Jane Doe", 200)));

        verify(viewMock).set(0, "John Doe 100");
        verify(viewMock).set(1, "Jane Doe 200");
    }
}
