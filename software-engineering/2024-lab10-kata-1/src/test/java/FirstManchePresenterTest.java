import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.presenters.FirstManchePresenter;
import it.unimi.di.sweng.slalom.views.DisplayView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FirstManchePresenterTest {

    @Test
    void shouldUpdateViewDetails() {
        DisplayView displayViewMock = mock();
        FirstManchePresenter SUT = new FirstManchePresenter(displayViewMock);

        List<Skier> skiers = List.of(new Skier("name", 100), new Skier("name2", 200));
        SUT.update(null, skiers, null);

        verify(displayViewMock).set(0, "name 100.00");
        verify(displayViewMock).set(1, "name2 200.00");
    }

}
